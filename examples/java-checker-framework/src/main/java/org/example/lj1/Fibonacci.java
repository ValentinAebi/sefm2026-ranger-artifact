package org.example.lj1;

import org.checkerframework.checker.index.qual.NonNegative;


public class Fibonacci {

    public static @NonNegative int fib_original(@NonNegative int n) {       //> Fibonacci::fib_original p=(1,1,1/1) r=(1,1/2) loc=6  --  the Index Checker cannot express @GreaterOrEq("n")
        if (n <= 1)
            return 0;
        else
            return fib_original(n - 1) + fib_original(n - 2);
    }

    public static @NonNegative int fib_base_case_fixed(@NonNegative int n) {    //> Fibonacci::fib_base_case_fixed p=(1,1,1/1) r=(1,1/2) loc=6  --  the Index Checker cannot express @GreaterOrEq("n")
        if (n <= 1)
            return n;
        else
            return fib_original(n - 1) + fib_original(n - 2);
    }

}
