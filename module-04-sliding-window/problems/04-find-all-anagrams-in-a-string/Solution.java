import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n*m) time, O(1) extra space
    public List<Integer> bruteForce(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int lenP = p.length(), lenS = s.length();
        if (lenP > lenS) return result;

        int[] pFreq = new int[26];
        for (char c : p.toCharArray()) pFreq[c - 'a']++;

        for (int i = 0; i + lenP <= lenS; i++) {
            int[] windowFreq = new int[26];
            for (int j = i; j < i + lenP; j++) windowFreq[s.charAt(j) - 'a']++;
            if (Arrays.equals(pFreq, windowFreq)) result.add(i);
        }
        return result;
    }

    // Approach 2: Optimized -> O(n) time, O(1) extra space (incremental sliding window)
    public List<Integer> optimized(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int lenP = p.length(), lenS = s.length();
        if (lenP > lenS) return result;

        int[] pFreq = new int[26];
        int[] windowFreq = new int[26];
        for (char c : p.toCharArray()) pFreq[c - 'a']++;

        for (int i = 0; i < lenP; i++) windowFreq[s.charAt(i) - 'a']++;
        if (Arrays.equals(pFreq, windowFreq)) result.add(0);

        for (int i = lenP; i < lenS; i++) {
            windowFreq[s.charAt(i) - 'a']++;
            windowFreq[s.charAt(i - lenP) - 'a']--;
            if (Arrays.equals(pFreq, windowFreq)) result.add(i - lenP + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "cbaebabacd", p = "abc";

        System.out.println("Brute Force -> " + sol.bruteForce(s, p));
        System.out.println("Optimized   -> " + sol.optimized(s, p));
    }
}
