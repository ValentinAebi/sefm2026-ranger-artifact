package org.example.lj4;

import org.checkerframework.common.value.qual.IntRange;


@SuppressWarnings("unused")
public class Test {

    public static @IntRange(from = 2021, to = Integer.MAX_VALUE) int getYear() {
        return 2024;
    }

    public static void test_correct() {     //> Test::test_correct p=(0,0,0/0) r=none loc=9
        int a = 1998;
        Car c = new Car();
        c.setYear(a);

        @IntRange(from = 1801)
        int j = c.getYear();      // we ignore these annotations, they are needed only for testing

        @IntRange(from = 2021)
        int k = getYear();
    }

    public static void test_buggy() {       //> Test::test_buggy p=(0,0,0/0) r=none BUG REPORTED loc=9
        int a = 998;
        Car c = new Car();
        c.setYear(a);               // ERROR: bad value

        @IntRange(from = 1801)
        int j = c.getYear();      // we ignore these annotations, they are needed only for testing

        @IntRange(from = 2021)
        int k = getYear();
    }

}
