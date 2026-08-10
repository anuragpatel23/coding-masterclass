import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) extra space
    public List<String> bruteForce(String s) {
        Set<String> result = new LinkedHashSet<>();
        int n = s.length();

        for (int i = 0; i + 10 <= n; i++) {
            String seq = s.substring(i, i + 10);
            for (int j = i + 1; j + 10 <= n; j++) {
                if (s.substring(j, j + 10).equals(seq)) {
                    result.add(seq);
                    break;
                }
            }
        }
        return new ArrayList<>(result);
    }

    // Approach 2: Optimized -> O(n) time, O(n) space (sliding window + hashmap counting)
    public List<String> optimized(String s) {
        Map<String, Integer> counts = new HashMap<>();
        List<String> result = new ArrayList<>();
        int n = s.length();

        for (int i = 0; i + 10 <= n; i++) {
            String seq = s.substring(i, i + 10);
            int count = counts.getOrDefault(seq, 0) + 1;
            counts.put(seq, count);
            if (count == 2) result.add(seq);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";

        System.out.println("Brute Force -> " + sol.bruteForce(s));
        System.out.println("Optimized   -> " + sol.optimized(s));
    }
}
