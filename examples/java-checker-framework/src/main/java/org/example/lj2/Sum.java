package org.example.lj2;

import org.checkerframework.checker.index.qual.NonNegative;


public class Sum {

    public static @NonNegative int sum_original(int n) {        //> Sum::sum_original p=(1,0,0/0) r=(1,1/2) loc=8  --  the Index Checker cannot express @GreaterOrEq("n"), so we don't consider this unit as bug
        if (n <= 1)
            return 0;   // ERROR: this error cannot be detected by the Checker Framework since the correctness condition sum(n) >= n cannot be expressed
        else {
            int t1 = sum_original(n - 1);
            return n + t1;
        }
    }

    public static @NonNegative int sum_fixed(int n) {           //> Sum::sum_fixed p=(1,0,0/0) r=(1,1/2) loc=8  --  the Index Checker cannot express @GreaterOrEq("n")
        if (n <= 1)
            return 0;
        else {
            int t1 = sum_fixed(n - 1);
            return n + t1;
        }
    }

}
