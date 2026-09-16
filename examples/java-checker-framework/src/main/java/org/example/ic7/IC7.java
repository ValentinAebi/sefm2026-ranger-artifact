package org.example.ic7;


public class IC7 {
    
    public static String removeSubstring_correct_1(String original, String removed) {                   //> IC7::removeSubstring_correct_1 p=(2,0,0/0) r=(0,0/0) loc=7
        int i = original.indexOf(removed);
        if (i != -1) {  // checking using !=
            return original.substring(0, i) + original.substring(i + removed.length());
        }
        return original;
    }

    public static String removeSubstring_correct_2(String original, String removed) {                   //> IC7::removeSubstring_correct_2 p=(2,0,0/0) r=(0,0/0) loc=7
        int i = original.indexOf(removed);
        if (i > -1) {   // <- checking using >
            return original.substring(0, i) + original.substring(i + removed.length());
        }
        return original;
    }

    public static String removeSubstring_buggy(String original, String removed) {                       //> IC7::removeSubstring_buggy p=(2,0,0/0) r=(0,0/0) BUG REPORTED loc=4
        int i = original.indexOf(removed);
        // ERROR: missing check for i == -1
        return original.substring(0, i) + original.substring(i + removed.length());
    }

}
