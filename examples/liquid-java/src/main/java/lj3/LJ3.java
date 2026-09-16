package lj3;

import liquidjava.specification.Refinement;
import liquidjava.specification.RefinementAlias;

public class LJ3 {

    static @Refinement("x > 0") // x must be greater than 0
    int x;

    static @Refinement("0 <= _ && _ <= 100") // y must be between 0 and 100
    int y;

    static @Refinement(value = "z % 2 == 0 ? z >= 0 : z < 0", msg = "z must be positive if even, negative if odd") int z;   //> LJ3::ternary-ref p=(0,0,0/0) r=(1,1/1)

    public static @Refinement("_ >= 0") int absDiv_correct(int a,
            @Refinement(value = "b != 0", msg = "cannot divide by zero") int b) {   //> LJ3::absDiv_correct p=(2,1,1/1) r=(1,1/1)
        int res = a / b;
        return res >= 0 ? res : -res;
    }

    public static @Refinement("_ >= 0") int absDiv_buggy(int a, int b) {            //> LJ3::absDiv_buggy p=(2,0,0/0) r=(1,1/1) BUG
        int res = a / b;
        return res >= 0 ? res : -res;
    }

}
