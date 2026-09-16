package org.example.arraymap;

import org.checkerframework.checker.index.qual.IndexOrLow;

public class ArrayUtils {
    
    public static <T> @IndexOrLow("#1") int indexOf(T[] array, T elem) {    //> Array::indexOf p=(1,0,0/0) r=(1,2/2) loc=8  --  we ignore the array parameter since the corresponding method in Licorne is an instance method
        for (int i = 0; i < array.length; i++) {
            if (array[i] == elem) {
                return i;
            }
        }
        return -1;
    }

}
