import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n*m) time, O(1) space
    public boolean bruteForce(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) return false;

        int[] s1Freq = new int[26];
        for (char c : s1.toCharArray()) s1Freq[c - 'a']++;

        for (int i = 0; i + len1 <= len2; i++) {
            int[] windowFreq = new int[26];
            for (int j = i; j < i + len1; j++) windowFreq[s2.charAt(j) - 'a']++;
            if (Arrays.equals(s1Freq, windowFreq)) return true;
        }
        return false;
    }

    // Approach 2: Optimized -> O(n) time, O(1) space (incremental sliding window)
    public boolean optimized(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) return false;

        int[] s1Freq = new int[26];
        int[] windowFreq = new int[26];
        for (char c : s1.toCharArray()) s1Freq[c - 'a']++;

        for (int i = 0; i < len1; i++) {
            windowFreq[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(s1Freq, windowFreq)) return true;

        for (int i = len1; i < len2; i++) {
            windowFreq[s2.charAt(i) - 'a']++;
            windowFreq[s2.charAt(i - len1) - 'a']--;
            if (Arrays.equals(s1Freq, windowFreq)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s1 = "ab", s2 = "eidbaooo";

        System.out.println("Brute Force -> " + sol.bruteForce(s1, s2));
        System.out.println("Optimized   -> " + sol.optimized(s1, s2));
    }
}
