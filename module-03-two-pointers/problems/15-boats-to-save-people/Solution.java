import java.util.*;

public class Solution {

    // Approach 1: Brute Force -> O(n^2) time, O(n) space (repeated linear scans, no sort)
    public int bruteForce(int[] people, int limit) {
        List<Integer> remaining = new ArrayList<>();
        for (int p : people) remaining.add(p);
        int boats = 0;

        while (!remaining.isEmpty()) {
            int maxIdx = 0, minIdx = 0;
            for (int i = 1; i < remaining.size(); i++) {
                if (remaining.get(i) > remaining.get(maxIdx)) maxIdx = i;
                if (remaining.get(i) < remaining.get(minIdx)) minIdx = i;
            }

            if (maxIdx == minIdx) {
                remaining.remove(maxIdx);
                boats++;
                continue;
            }

            int heaviest = remaining.get(maxIdx);
            int lightest = remaining.get(minIdx);

            if (lightest + heaviest <= limit) {
                remaining.remove(Math.max(maxIdx, minIdx));
                remaining.remove(Math.min(maxIdx, minIdx));
            } else {
                remaining.remove(maxIdx);
            }
            boats++;
        }
        return boats;
    }

    // Approach 2: Optimized -> O(n log n) time, O(1) extra space (sort + two pointers)
    public int optimized(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0, j = people.length - 1;
        int boats = 0;

        while (i <= j) {
            if (people[i] + people[j] <= limit) {
                i++;
            }
            j--;
            boats++;
        }
        return boats;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] people = {3, 2, 2, 1};
        int limit = 3;

        System.out.println("Brute Force -> " + sol.bruteForce(people.clone(), limit));
        System.out.println("Optimized   -> " + sol.optimized(people.clone(), limit));
    }
}
