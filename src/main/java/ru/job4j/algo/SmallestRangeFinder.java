package ru.job4j.algo;

import java.util.Arrays;

public class SmallestRangeFinder {
    public static int[] findSmallestRange(int[] nums, int k) {
        int[] rsl = null;
        int[] freq = new int[10001];
        int count = 0;
        for (int index = 0; index < nums.length; index++) {
            if (++freq[nums[index]] > 1) {
                count = 1;
            } else {
                count++;
            }
            if (count == k) {
                rsl = new int[]{index - k + 1, index};
                break;
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}