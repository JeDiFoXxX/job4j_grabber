package ru.job4j.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class IntervalMerge {
    public int[][] merge(int[][] intervals) {
        int[][] rsl;
        Arrays.sort(intervals, Comparator.comparingInt(array -> array[0]));
        List<int[]> list = new ArrayList<>();
        int[] head = null;
        for (int index = 0; index < intervals.length; index++) {
            if (head == null) {
                head = intervals[index];
            }
            if (index + 1 >= intervals.length) {
                list.add(head);
                break;
            }
            int[] next = intervals[index + 1];
            if (head[1] >= next[0]) {
                head[1] = Math.max(head[1], next[1]);
            } else {
                list.add(head);
                head = next;
            }
        }
        rsl = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            rsl[i] = list.get(i);
        }
        return rsl;
    }
}

