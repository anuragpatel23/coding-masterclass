import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n) time per call, O(n) space (full price history)
    static class StockSpannerBruteForce {
        private List<Integer> prices = new ArrayList<>();

        public int next(int price) {
            prices.add(price);
            int span = 1;
            for (int i = prices.size() - 2; i >= 0; i--) {
                if (prices.get(i) <= price) span++;
                else break;
            }
            return span;
        }
    }

    // Approach 2: Optimized -> O(1) amortized time per call, O(n) space
    // (monotonic stack of (price, span) pairs)
    static class StockSpannerOptimized {
        private Deque<int[]> stack = new ArrayDeque<>(); // [price, span]

        public int next(int price) {
            int span = 1;
            while (!stack.isEmpty() && stack.peek()[0] <= price) {
                span += stack.pop()[1];
            }
            stack.push(new int[]{price, span});
            return span;
        }
    }

    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};

        StockSpannerBruteForce bf = new StockSpannerBruteForce();
        StringBuilder bfResult = new StringBuilder();
        for (int p : prices) bfResult.append(bf.next(p)).append(" ");
        System.out.println("Brute Force -> " + bfResult.toString().trim());

        StockSpannerOptimized opt = new StockSpannerOptimized();
        StringBuilder optResult = new StringBuilder();
        for (int p : prices) optResult.append(opt.next(p)).append(" ");
        System.out.println("Optimized   -> " + optResult.toString().trim());
    }
}
