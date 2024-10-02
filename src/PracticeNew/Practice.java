//package PracticeNew;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@FunctionalInterface
//interface Calculator{
//    void operate(int a,int b);
//}
//public class Practice {
//    public static void main(String[] args) {
//        Calculator calculator = (int a, int b) -> {
//            System.out.println(a + b);
//        };
////        calculator.operate(5,6);
//
//        int[] prices = {655, 866, 576, 884, 455, 418, 414, 818, 167, 495, 218, 992, 29, 457, 162, 834, 769};
////                {100, 200, 300}; // Example prices
//        int m = 87; // Example number of coupons
//        int minCost = minimumCost(prices, m);
////        System.out.println("Minimum cost to purchase all items: " + minCost);
//
//        List<String> a = new ArrayList<>(Arrays.asList("a good   example".split(" ")));
//        a= a.stream().filter(v -> !v.isBlank() && v.chars().count()>0).map(v -> v.trim()).collect(Collectors.toList());
//        Collections.reverse(a);
//
//        System.out.println(a);
//    }
//    public static int minimumCost(int[] price, int m) {
//        int n = price.length;
//        int totalCost = 0;
//        List<Integer> savings = new ArrayList<>();
//        // Calculate total original price
//        for (int p : price) {
//            totalCost += p;
//        }
//        // Calculate possible savings for each item using 0 to m coupons
//        for (int i = 0; i < n; i++) {
//            int originalPrice = price[i];
//            for (int x = 0; x <= m; x++) {
//                int discountedPrice = originalPrice / (1 << x); // equivalent to originalPrice / (2^x)
//                int currentSavings = originalPrice - discountedPrice;
//                savings.add(currentSavings);
//            }
//        }
//        // Sort savings in descending order
//        Collections.sort(savings, Collections.reverseOrder());
//
//        // Calculate the total savings by using the best m savings
//        int totalSavings = 0;
//        for (int i = 0; i < Math.min(m, savings.size()); i++) {
//            totalSavings += savings.get(i);
//        }
//
//        // Calculate the minimum cost
//        return totalCost - totalSavings;
//    }
//
//    public static long findMinimumPrice(List<Integer> price, int m) {
//        long minSum = 0;
//        if (m == 0) {
//            minSum = price.stream().reduce(0, (a, b) -> a + b);
//            return minSum;
//        }
//        Collections.sort(price, (a, b) -> b.compareTo(a));
//        for (int i = 0; i < price.size(); i++) {
//            if (m != 0 && price.get(i) == 1) {
//                price.set(i, 0);
//                m = m - 1;
//            } else {
//                int couponToBeUsed = (int) Math.floor(Math.log(price.get(i)) / Math.log(2));
//                if (m > couponToBeUsed) {
//                    price.set(i, 1);
//                    m -= couponToBeUsed;
//                } else {
//                    if (m != 0) {
//                        int itemPriceLeft = (int) Math.floor(price.get(i) / Math.pow(2, m));
//                        price.set(i, itemPriceLeft);
//                        m = 0;
//                        break;
//                    }
//                }
//                minSum = price.stream().reduce(0, (a, b) -> a + b);
//                return minSum;
//            }
//        }
//    }
//}
//
