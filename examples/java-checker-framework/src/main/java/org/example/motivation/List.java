package org.example.motivation;

import java.util.function.Function;
import java.util.function.Predicate;

public sealed interface List<T> permits Cons, Nil {

    public static <T> List<T> arrayToList(T[] array) {      //> Array::toList p=(0,0,0/0) r=(0,0/0) aux-annot=(1,0,0) loc=7  --  we ignore the parameter here as the corresponding method in the Licorne implementation is an instance method
        List<T> ls = new Nil<>();
        for (var i = array.length-1; i >= 0; i--) {
            ls = new Cons<>(array[i], ls);
        }
        return ls;
    }

    public static <T, U> List<U> map(List<T> ls, Function<T, U> f) {    //> List::map p=(2,0,0/0) r=(0,0/0) aux-annot=(1,0,0) loc=8
        List<U> rev = new Nil<>();
        while (ls instanceof Cons<T> lsc) {
            rev = new Cons<>(f.apply(lsc.head()), rev);
            ls = lsc.tail();
        }
        return reverse(rev);
    }

    public static <T> List<T> filter(List<T> ls, Predicate<T> pred) {   //> List::filter p=(2,0,0/0) r=(0,0/1) aux-annot=(1,0,0) loc=10  --  the non-expressible constraint corresponds to the predicate (return type should ideally be List<T with pred>)
        List<T> rev = new Nil<>();
        while (ls instanceof Cons<T> lsc) {
            if (pred.test(lsc.head())) {
                rev = new Cons<>(lsc.head(), rev);
            }
            ls = lsc.tail();
        }
        return reverse(rev);
    }

    private static <T> List<T> reverse(List<T> ls) {                    //> List::reverse p=(1,0,0/0) r=(0,0/0) aux-annot=(1,0,0) loc=7
        List<T> result = new Nil<>();
        while (ls instanceof Cons<T> lsc) {
            result = new Cons<T>(lsc.head(), result);
        }
        return result;
    }

}
