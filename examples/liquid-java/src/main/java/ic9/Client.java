package ic9;

import liquidjava.specification.Refinement;
import liquidjava.specification.RefinementAlias;


public class Client {

    // fails in LiquidJava
    /* public static void clearIndex1(ArrayWrapper<? extends Object> a, @Refinement("0 <= _ && _ < a.size()") int i) {  //> Client::clearIndex1 p=(2,1,2/2) r=none FAIL
        a.set(i , null);
    } */

    public static void clearIndex2(ArrayWrapper<? extends Object> a, int i) {   //> Client::clearIndex2 p=(2,0,0/0) r=none REPORTED
        if (0 <= i && i < a.size()) {
            a.set(i, null);
        }
    }
    
}
