package org.example.lj3;

import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.common.value.qual.IntRange;


public class LJ3 {
    
    @NonNegative int x;

    @IntRange(from = 0, to = 100) int y;

    // not expressible using the Checker Framework: @Refinement(value="z % 2 == 0 ? z >= 0 : z < 0", msg="z must be positive if even, negative if odd")
    int z;  //> LJ3::ternary-ref p=(0,0,0/0) r=(1,0/1) loc=1

    @NonNegative int absDiv_correct(int a, int b) {  //> LJ3::absDiv_correct p=(2,0,0/1) r=(1,1/1) loc=4  --  cannot express b != 0
        int res = a / b;
        // return res >= 0 ? res : -res;   // does not work with the Checker Framework
        return Math.abs(res);
    }

    @NonNegative int absDiv_buggy(int a, int b) {  //> LJ3::absDiv_buggy p=(2,0,0/0) r=(1,1/1) loc=4  --  cannot express b != 0, so we don't consider this unit as bug
        int res = a / b;
        // return res >= 0 ? res : -res;   // does not work with the Checker Framework
        return Math.abs(res);
    }

}
