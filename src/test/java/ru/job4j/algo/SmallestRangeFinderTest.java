package ru.job4j.algo;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SmallestRangeFinderTest {
    @Test
    public void whenFindSmallestRangeUniqueElementsThenReturnsExpectedRange() {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] expectedRange = {0, 2};
        assertThat(SmallestRangeFinder.findSmallestRange(nums, k)).isEqualTo(expectedRange);
    }

    @Test
    public void whenFindSmallestRangeRepeatedElementsThenReturnsExpectedRange() {
        int[] nums = {1, 2, 3, 3, 5, 6, 7};
        int k = 4;
        int[] expectedRange = {3, 6};
        assertThat(SmallestRangeFinder.findSmallestRange(nums, k)).isEqualTo(expectedRange);
    }

    @Test
    public void whenNotFound() {
        int[] nums = {1, 2, 3, 3, 3};
        int k = 4;
        int[] expectedRange = null;
        assertThat(SmallestRangeFinder.findSmallestRange(nums, k)).isNull();
    }

    @Test
    public void whenAllElementsSameThenNotFound() {
        int[] nums = {5, 5, 5, 5};
        int k = 2;
        int[] expectedRange = null;
        assertThat(SmallestRangeFinder.findSmallestRange(nums, k)).isNull();
    }

    @Test
    public void whenKIsOneThenReturnFirstElement() {
        int[] nums = {10, 20, 30};
        int k = 1;
        int[] expectedRange = {0, 0};
        assertThat(SmallestRangeFinder.findSmallestRange(nums, k)).isEqualTo(expectedRange);
    }

    @Test
    public void whenKEqualsArrayLengthThenReturnWholeArray() {
        int[] nums = {1, 2, 3, 4};
        int k = 4;
        int[] expectedRange = {0, 3};
        assertThat(SmallestRangeFinder.findSmallestRange(nums, k)).isEqualTo(expectedRange);
    }

    @Test
    public void whenDuplicatesAtEdges() {
        int[] nums = {1, 1, 2, 3, 4, 4};
        int k = 4;
        int[] expectedRange = {1, 4};
        assertThat(SmallestRangeFinder.findSmallestRange(nums, k)).isEqualTo(expectedRange);
    }

    @Test
    public void whenMinimalRangeLongerThanKThenNotFoundByCurrentMethod() {
        int[] nums = {1, 2, 2, 2, 3, 4};
        int k = 3;
        int[] expectedRange = {3, 5};
        assertThat(SmallestRangeFinder.findSmallestRange(nums, k)).isEqualTo(expectedRange);
    }

    public void whenKIsZeroThenReturnNull() {
        int[] nums = {1, 2, 3, 4};
        int k = 0;
        int[] expectedRange = null;
        assertThat(SmallestRangeFinder.findSmallestRange(nums, k)).isNull();
    }
}