package lj4;

import liquidjava.specification.Refinement;

@SuppressWarnings("unused")
public class Test {

    @Refinement("_ > 2020")
    public static int getYear() {
        return 2024;
    }

    public static void test_correct() {     //> Test::test_correct p=(0,0,0/0) r=none
        int a = 1998;
        Car c = new Car();
        c.setYear(a);

        @Refinement("_ > 1800")
        int j = c.getYear();        // we ignore these annotations, they are needed only for testing

        @Refinement("_ > 2020")
        int k = getYear();
    }

    public static void test_buggy() {   //> Test::test_buggy p=(0,0,0/0) r=none BUG REPORTED
        int a = 998;
        Car c = new Car();
        c.setYear(a);

        @Refinement("_ > 1800")
        int j = c.getYear();        // we ignore these annotations, they are needed only for testing

        @Refinement("_ > 2020")
        int k = getYear();
    }

}
