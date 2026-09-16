package ic7;

import liquidjava.specification.Refinement;
import liquidjava.specification.RefinementAlias;


public class IC7 {
    
    public static String removeSubstring_correct_1(String original, String removed) {     //> IC7::removeSubstring_correct_1 p=(2,0,0/0) r=(0,0/0) FAIL
        int i = original.indexOf(removed);
        if (i != -1) {  // <- checking using >
            // crashes LiquidJava
            //return original.substring(0, i) + original.substring(i + removed.length());
        }
        return original;
    }

    public static String removeSubstring_correct_2(String original, String removed) {     //> IC7::removeSubstring_correct_2 p=(2,0,0/0) r=(0,0/0) FAIL
        int i = original.indexOf(removed);
        if (i != -1) {  // <- checking using >
            // crashes LiquidJava
            //return original.substring(0, i) + original.substring(i + removed.length());
        }
        return original;
    }

    public static String removeSubstring_buggy(String original, String removed) {     //> IC7::removeSubstring_buggy p=(2,0,0/0) r=(0,0/0) BUG FAIL
        int i = original.indexOf(removed);
        //return original.substring(0, i) + original.substring(i + removed.length());   // crashes LiquidJava
        return null;
    }

}
