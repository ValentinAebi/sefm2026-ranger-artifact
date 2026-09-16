package org.example.ic9;

import org.checkerframework.checker.index.qual.SameLen;
import org.checkerframework.checker.index.qual.IndexFor;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.LengthOf;


/** ArrayWrapper is a fixed-size generic collection. */
public class ArrayWrapper<T> {
    private final Object @SameLen("this") [] delegate;

    @SuppressWarnings("index") // constructor creates object of size @SameLen(this) by definition
    ArrayWrapper(@NonNegative int size) {                   //> ArrayWrapper::constructor p=(1,1,1/1) r=none loc=3
        delegate = new Object[size];
    }

    public @LengthOf("this") @NonNegative int size() {     //> ArrayWrapper::size p=(0,0,0/0) r=(2,1/1) loc=3  --  @LengthOf("this") does not express a constraint, it merely informs the Checker Framework that this method should be treated as the size of this collection
        return delegate.length;
    }

    public void set(@IndexFor("this") int index, T obj) {   //> ArrayWrapper::set p=(2,1,2/2) r=none loc=3
        delegate[index] = obj;
    }

    @SuppressWarnings("unchecked") // required for normal Java compilation due to unchecked cast
    public T get(@IndexFor("this") int index) {             //> ArrayWrapper::get p=(1,1,2/2) r=(0,0/0) loc=3
        return (T) delegate[index];
    }
}
