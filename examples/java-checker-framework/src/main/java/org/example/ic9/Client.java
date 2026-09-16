package org.example.ic9;

import org.checkerframework.checker.index.qual.IndexFor;


public class Client {

    public static void clearIndex1(ArrayWrapper<? extends Object> a, @IndexFor("#1") int i) {   //> Client::clearIndex1 p=(2,1,2/2) r=none loc=3
        a.set(i, null);
    }

    public static void clearIndex2(ArrayWrapper<? extends Object> a, int i) {                   //> Client::clearIndex2 p=(2,0,0/0) r=none loc=5
        if (0 <= i && i < a.size()) {
            a.set(i, null);
        }
    }
    
}
