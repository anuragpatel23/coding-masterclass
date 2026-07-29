import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(1) space (re-derive mapping by rescanning)
    public boolean bruteForce(String s, String t) {
        if (s.length() != t.length()) return false;

        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);

            for (int j = 0; j < i; j++) {
                char prevS = s.charAt(j);
                char prevT = t.charAt(j);
                if ((prevS == sc) != (prevT == tc)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Approach 2: Optimized -> O(n) time, O(k) space (two hashmaps, single pass)
    public boolean optimized(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> sToT = new HashMap<>();
        Map<Character, Character> tToS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);

            if (sToT.containsKey(sc) && sToT.get(sc) != tc) return false;
            if (tToS.containsKey(tc) && tToS.get(tc) != sc) return false;

            sToT.put(sc, tc);
            tToS.put(tc, sc);
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s1 = "egg", t1 = "add";
        String s2 = "foo", t2 = "bar";

        System.out.println("Brute Force (egg/add) -> " + sol.bruteForce(s1, t1));
        System.out.println("Optimized   (egg/add) -> " + sol.optimized(s1, t1));
        System.out.println("Brute Force (foo/bar) -> " + sol.bruteForce(s2, t2));
        System.out.println("Optimized   (foo/bar) -> " + sol.optimized(s2, t2));
    }
}
