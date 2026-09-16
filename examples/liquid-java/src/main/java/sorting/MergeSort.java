// Adapted from https://github.com/TheAlgorithms/Java/blob/master/src/main/java/com/thealgorithms/sorts/MergeSort.java
package sorting;

import liquidjava.specification.Refinement;
import liquidjava.specification.RefinementAlias;

import static sorting.SortUtils.less;

import java.util.Arrays;
import java.util.function.BiFunction;

/*
 * After encountering crashes when trying to verify the original version of this code using LiquidJava,
 * we simplified it by avoiding the use of generics.
 */


@RefinementAlias("IndexFor(int idx, int[] arr) { 0 <= idx && idx < length(arr) }")
@SuppressWarnings("rawtypes")
class MergeSort {

    public static void sort(int[] unsorted, BiFunction<Integer, Integer, Boolean> lessThan) {     //> MergeSort::sort p=(2,0,0/0) r=none
        if (unsorted.length <= 0) {
            return;
        }
        int[] tempArray = arrayCopy(unsorted);
        doSort(unsorted, tempArray, lessThan, 0, unsorted.length - 1);
    }

    private static void doSort(
        int[] arr,
        int[] tempArray,
        BiFunction<Integer, Integer, Boolean> lessThan,
        @Refinement("IndexFor(_, arr) && IndexFor(_, tempArray)") int left,
        @Refinement("IndexFor(_, arr) && IndexFor(_, tempArray)") int right
    ) {  //> MergeSort::doSort p=(5,2,8/8) r=none
        if (left < right) {
            int mid = (left + right) / 2;
            doSort(arr, tempArray, lessThan, left, mid);
            doSort(arr, tempArray, lessThan, mid + 1, right);
            merge(arr, tempArray, lessThan, left, mid, right);
        }
    }

    @SuppressWarnings("unchecked")  //> MergeSort::merge p=(6,3,13/13) r=none BUG
    private static void merge(
        int[] arr,
        int[] tempArray,
        BiFunction<Integer, Integer, Boolean> lessThan,
        @Refinement("IndexFor(_, arr) && IndexFor(_, tempArray)") int left,
        @Refinement("IndexFor(_, arr) && IndexFor(_, tempArray)") int mid,
        @Refinement("IndexFor(_, arr) && IndexFor(_, tempArray) && left < _") int right
    ) {
        int i = left;
        int j = mid + 1;
        System.arraycopy(arr, left, tempArray, left, right + 1 - left);

        for (int k = left; k <= right; k++) {
            if (j > right) {
                arr[k] = tempArray[i++];
            } else if (i > mid) {
                arr[k] = tempArray[j--];    // ERROR: should be j-- here
            } else if (lessThan.apply(tempArray[j], tempArray[i])) {
                arr[k] = tempArray[j++];
            } else {
                arr[k] = tempArray[i++];
            }
        }
    }

    private static @Refinement("length(_) == length(input)") int[] arrayCopy(int[] input) {
        int[] copy = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            copy[i] = input[i];
        }
        return copy;
    }

}
