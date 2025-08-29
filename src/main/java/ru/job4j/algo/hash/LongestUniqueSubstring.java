package ru.job4j.algo.hash;

import java.util.HashMap;
import java.util.Map;

public class LongestUniqueSubstring {
    public static String longestUniqueSubstring(String str) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        while (end < str.length()) {
            if (map.containsKey(str.charAt(end))) {
                start = map.get(str.charAt(end)) + 1;
                map.put(str.charAt(end), end);
            } else {
                map.put(str.charAt(end), end);
            }
            end++;
        }
        return str.substring(start, end);
    }
}