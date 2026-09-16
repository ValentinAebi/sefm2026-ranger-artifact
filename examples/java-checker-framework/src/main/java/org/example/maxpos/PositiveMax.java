package org.example.maxpos;

import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;

public class PositiveMax {
    
    public static @NonNegative int maxPos_correct(int[] a) {        //> PositiveMax::maxPos_correct p=(1,0,0/0) r=(1,1/1) loc=12
        var k = 0;
        var max = 0;
        while (k < a.length) {
            var v = a[k];
            if (v > 0 && v > max) {
                max = v;
            };
            k += 1;
        };
        return max;
    }

    public static @NonNegative int maxPos_buggy(int[] a) {          //> PositiveMax::maxPos_buggy p=(1,0,0/0) r=(1,1/1) BUG REPORTED loc=12
        var k = 0;
        var max = 0;
        // ERROR: should be <
        while (k <= a.length) {
            var v = a[k];
            if (v > 0 && v > max) {
                max = v;
            };
            k += 1;
        };
        return max;
    }

    public static @NonNegative int maxPos_moreComplex_correct(int[] a, @IndexFor("#1") int start, @Positive int incEven, @Positive int incOdd) {    //> PositiveMax::maxPos_moreComplex_correct p=(4,3,4/4) r=(1,1/1) loc=16
        var k = 0;
        var max = start;
        while (k < a.length) {
            var v = a[k];
            if (v > 0 && v > max) {
                max = v;
            };
            if (k % 2 == 0) {
                k += incEven;
            } else {
                k += incOdd;
            }
        };
        return max;
    }

    public static @NonNegative int maxPos_moreComplex_buggy(int[] a, @IndexFor("#1") int start, @Positive int incEven, int incOdd) {            //> PositiveMax::maxPos_moreComplex_buggy p=(4,2,3/3) r=(1,1/1) BUG REPORTED loc=16
        var k = 0;
        var max = start;
        while (k < a.length) {
            var v = a[k];
            if (v > 0 && v > max) {
                max = v;
            };
            if (k % 2 == 0) {
                k += incEven;
            } else {
                k += incOdd;  // ERROR: incOdd may be negative
            }
        };
        return max;
    }

}
