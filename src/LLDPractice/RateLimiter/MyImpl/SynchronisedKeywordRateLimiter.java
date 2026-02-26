package LLDPractice.RateLimiter.MyImpl;

import java.time.LocalTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.time.LocalTime.now;

class UserState {
    int userId;
    LocalTime timerStartTime;
    int counter;
    int allowedRateOfRequestInSec;

    public UserState(int userId, LocalTime timerStartTime, int counter, int allowedRateOfRequestInSec) {
        this.userId = userId;
        this.timerStartTime = timerStartTime;
        this.counter = counter;
        this.allowedRateOfRequestInSec = allowedRateOfRequestInSec;
    }

    @Override
    public String toString() {
        return "UserState{" +
          "userId=" + userId +
          ", timerStartTime=" + timerStartTime +
          ", now=" + now() +
          ", counter=" + counter +
          ", allowedRateOfRequestInSec=" + allowedRateOfRequestInSec +
          '}';
    }
}
class RateLimiter {
    static ConcurrentHashMap<Integer, UserState> userConfig = new ConcurrentHashMap<>();
    static {
        userConfig.put(1, new UserState(1, now(), 1,5));
        // 10 concurrent requests/secs are allowed
        userConfig.put(2, new UserState(2, now(), 2,5));
    }
    // allowed 10, counter 5, timeElapsed > 1min,
    static synchronized boolean checkIfRequestWithinLimit(int userId) {

        UserState userState = userConfig.get(userId);

        if(userState== null || userState.timerStartTime == null){
            UserState insertingUserState = new UserState(userId, now(), 0,5);
            userConfig.put(userId, insertingUserState);
            System.out.println("InsertingUserState user state: "+ insertingUserState.toString() + " "+ Thread.currentThread().getName());
        } else {
            System.out.println("Current user state: "+ userState.toString() + " "+Thread.currentThread().getName());
            int secondsElapsed = now().getSecond() - userState.timerStartTime.getSecond();
            if(userState.counter<= userState.allowedRateOfRequestInSec) {
                if (secondsElapsed <= 10) { // 10 requests in 10 seconds
                    userState.counter++;
                } else {
                    userState.counter = 1;
                }
                return true;
            }
            userState.timerStartTime = now();
            userState.counter = 1;
            System.out.println("False Current user state: "+ userState.toString() + " "+Thread.currentThread().getName());
            return false;
        }
        return true;
    }
}
public class SynchronisedKeywordRateLimiter {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 22; i++) {
            es.execute(() -> {
                try {
                    Thread.sleep(1000);
                    boolean isRateLimitingApplied = RateLimiter.checkIfRequestWithinLimit(1);
                    if (!isRateLimitingApplied) {
                        System.out.println("------Returning 429 --- Retry after 10secs---");
                        Thread.sleep(10000);
                    }else {
                        System.out.println("Proceed with Requests");
                    }

                }
                 catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        es.shutdown();

    }
}
