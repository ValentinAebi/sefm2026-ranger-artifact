package lj1;

import liquidjava.specification.Refinement;
import liquidjava.specification.RefinementAlias;

@RefinementAlias("Nat(int x) {x >= 0}")
@RefinementAlias("GreaterEqualThan(int x, int y) {x >= y}")
public class Fibonacci {

    @Refinement( "_ >= 0 && GreaterEqualThan(_, n)")
    public static int fib_original(@Refinement("Nat(n)") int n){        //> Fibonacci::fib_original p=(1,1,1/1) r=(1,2/2) BUG REPORTED
        if(n <= 1)
            return 0;
        else
            return fib_original(n-1) + fib_original(n-2);
    }

    @Refinement( "_ >= 0 && GreaterEqualThan(_, n)")
    public static int fib_base_case_fixed(@Refinement("Nat(n)") int n){     //> Fibonacci::fib_base_case_fixed p=(1,1,1/1) r=(1,2/2) BUG
        if(n <= 1)
            return n;
        else
            return fib_base_case_fixed(n-1) + fib_base_case_fixed(n-2);
    }
	
}
