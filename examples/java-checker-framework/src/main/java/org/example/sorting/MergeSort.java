// Original code taken from https://github.com/TheAlgorithms/Java/blob/master/src/main/java/com/thealgorithms/sorts/MergeSort.java
// Most of the Checker Framework annotations, as well as some modifications to make the code compiant to it, were implemented by Arnaud Fauconnet as part of an assignment for the Software analysis course (USI Università della Svizerra Italiana, 2024).
// Additional modifications were added by the repository's author, including the removal of the private static field to make the code closer to the Licorne implementation of it (which also makes it thread-safe).

package org.example.sorting;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.LessThan;
import org.checkerframework.checker.index.qual.IndexFor;

import java.util.Arrays;
import java.util.function.BiFunction;


public class MergeSort {
    
    public <T extends Comparable<T>> void sort(T[] unsorted, BiFunction<T, T, Boolean> lessThan) {      //> MergeSort::sort p=(2,0,0/0) r=none bypass=1 loc=8
        if (unsorted.length == 0) {
            return;
        }
        var aux = Arrays.copyOf(unsorted, unsorted.length);
        assert aux.length == unsorted.length : "@AssumeAssertion(index): semantics of copyOf";
        doSort(unsorted, aux, lessThan, 0, unsorted.length - 1);
    }

    private static <T extends Comparable<T>> void doSort(T[] arr, T[] aux, BiFunction<T, T, Boolean> lt, @IndexFor({"#1", "#2"}) int left, @IndexFor({"#1", "#2"}) int right) {       //> MergeSort::doSort p=(5,2,8/8) r=none bypass=1 loc=9
        if (left < right) {
            var mid = (left + right) >>> 1;
            doSort(arr, aux, lt, left, mid);
            if (mid + 1 < right)
                doSort(arr, aux, lt, mid + 1, right);
            assert left < right + 1 : "@AssumeAssertion(index): left < right --> left < right + 1";
            merge(arr, aux, lt, left, mid, right);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] arr, T[] aux, BiFunction<T, T, Boolean> lessThan, @IndexFor({"#1", "#2"}) @LessThan("#6 + 1") int left,   //> MergeSort::merge p=(6,4,13/13) r=none BUG REPORTED aux-annot=(1,1,2) bypass=1 loc=17
                                                        @IndexFor({"#1", "#2"}) int mid, @IndexFor({"#1", "#2"}) int right) {
        @IndexOrHigh("aux") int i = left, j = mid + 1;
        
        System.arraycopy(arr, left, aux, left, right + 1 - left);

        for (var k = left; k <= right; k++) {
            if (j > right) {
                assert i < aux.length: "@AssumeAssertion(index): i is always less than the length of aux";
                arr[k] = (T) aux[i++];
            } else if (i > mid) {
                arr[k] = (T) aux[j--];  // ERROR: should be j++ here
            } else if (lessThan.apply(aux[j], aux[i])) {
                arr[k] = (T) aux[j++];
            } else {
                arr[k] = (T) aux[i++];
            }
        }
    }
}
