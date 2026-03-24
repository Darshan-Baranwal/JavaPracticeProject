package LLDPractice.RateLimiter.RateLimiter;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ============================================================
 * RATE LIMITER DEMO (Token Bucket Algorithm)
 * ============================================================
 *
 * Features:
 * - Thread-safe
 * - Per-user rate limiting
 * - Uses Token Bucket algorithm
 * - Uses ConcurrentHashMap for scalability
 *
 * Real-world usage:
 * - API Gateway rate limiting
 * - Login attempt limiting
 * - Payment request throttling
 *
 * ============================================================
 */
public class RateLimiterDemo {

  /**
   * ============================================================
   * TOKEN BUCKET CLASS
   * ============================================================
   *
   * Each user will have one TokenBucket instance.
   *
   * Concept:
   * - Tokens are added at a fixed rate (refillRatePerSecond)
   * - Each request consumes 1 token
   * - If no tokens → request is rejected
   *
   * Thread Safety:
   * - synchronized method ensures no race conditions
   */
  static class TokenBucket {

    // Maximum tokens bucket can hold
    private final long capacity;

    // Tokens added per second
    private final long refillRatePerSecond;

    // Current available tokens
    private long tokens;

    // Last time tokens were refilled (epoch seconds)
    private long lastRefillTimestamp;

    /**
     * Constructor initializes bucket with full capacity
     */
    public TokenBucket(long capacity, long refillRatePerSecond) {
      this.capacity = capacity;
      this.refillRatePerSecond = refillRatePerSecond;

      // Initially bucket is full
      this.tokens = capacity;

      // Store current time
      this.lastRefillTimestamp = Instant.now().getEpochSecond();
    }

    /**
     * ============================================================
     * Main API: Check if request is allowed
     * ============================================================
     *
     * Steps:
     * 1. Refill tokens based on time passed
     * 2. If tokens available → consume 1 and allow request
     * 3. Else → reject request
     *
     * synchronized → ensures only one thread modifies tokens at a time
     */
    public synchronized boolean allowRequest() {

      // Step 1: Refill tokens if time has passed
      refill();

      // Step 2: Check if token available
      if (tokens > 0) {
        tokens--;  // consume one token
        return true;
      }

      // Step 3: Reject if no tokens
      return false;
    }

    /**
     * ============================================================
     * Refill Logic
     * ============================================================
     *
     * Example:
     * - refillRate = 2 tokens/sec
     * - 3 seconds passed → add 6 tokens
     *
     * Also ensures tokens never exceed capacity
     */
    private void refill() {

      long now = Instant.now().getEpochSecond();

      // Calculate how much time has passed
      long secondsPassed = now - lastRefillTimestamp;

      if (secondsPassed > 0) {

        // Tokens to add based on time passed
        long tokensToAdd = secondsPassed * refillRatePerSecond;

        // Add tokens but do not exceed capacity
        tokens = Math.min(capacity, tokens + tokensToAdd);

        // Update last refill time
        lastRefillTimestamp = now;
      }
    }
  }

  /**
   * ============================================================
   * RATE LIMITER SERVICE
   * ============================================================
   *
   * Maintains mapping:
   *
   * userId → TokenBucket
   *
   * Example:
   * user1 → bucket
   * user2 → bucket
   *
   * Thread Safety:
   * - ConcurrentHashMap handles concurrent access
   * - computeIfAbsent ensures atomic bucket creation
   */
  static class RateLimiter {

    // Stores bucket per user
    private final ConcurrentHashMap<String, TokenBucket> userBuckets =
      new ConcurrentHashMap<>();

    private final long capacity;
    private final long refillRate;

    public RateLimiter(long capacity, long refillRate) {
      this.capacity = capacity;
      this.refillRate = refillRate;
    }

    /**
     * ============================================================
     * Public API: Check if request is allowed for a user
     * ============================================================
     *
     * Flow:
     * 1. Fetch bucket for user
     * 2. If not present → create it atomically
     * 3. Call allowRequest() on bucket
     *
     * computeIfAbsent:
     * - Ensures only ONE bucket is created even with multiple threads
     */
    public boolean allowRequest(String userId) {

      // Get existing bucket OR create new one atomically
      TokenBucket bucket = userBuckets.computeIfAbsent(
        userId,
        id -> new TokenBucket(capacity, refillRate)
      );

      // Delegate request decision to bucket
      return bucket.allowRequest();
    }
  }

  /**
   * ============================================================
   * MAIN METHOD (MULTI-THREAD TEST)
   * ============================================================
   *
   * Simulates multiple concurrent requests from same user
   */
  public static void main(String[] args) {

    // Create rate limiter:
    // capacity = 5 tokens
    // refill = 2 tokens per second
    RateLimiter limiter = new RateLimiter(5, 2);

    // Thread pool with 10 threads
    ExecutorService executor = Executors.newFixedThreadPool(10);

    // Simulate 20 requests
    for (int i = 0; i < 20; i++) {

      int requestId = i;

      executor.submit(() -> {

        String userId = "user1"; // same user → shared bucket

        boolean allowed = limiter.allowRequest(userId);

        System.out.println(
          Thread.currentThread().getName()
            + " | Req-" + requestId
            + " | Allowed: " + allowed
        );
      });
    }

    executor.shutdown();
  }
}
