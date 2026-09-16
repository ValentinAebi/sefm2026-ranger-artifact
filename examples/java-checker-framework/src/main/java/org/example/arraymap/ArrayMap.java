package org.example.arraymap;

import org.checkerframework.checker.index.qual.IndexOrHigh;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.SameLen;
import org.checkerframework.dataflow.qual.Pure;

public class ArrayMap<K, V> {       //> ArrayMap::constructor p=(3,2,3/3) r=none loc=4
    private final K[] keys;
    private final V @SameLen("this.keys")[] values;
    private @IndexOrHigh("this.keys") int currSize;

    @SuppressWarnings("unchecked")
    public ArrayMap(@NonNegative int capacity) {    //> ArrayMap::aux-constructor p=(1,1,1/1) r=none loc=5
        this.keys = (K[]) new Object[capacity];
        this.values = (V[]) new Object[this.keys.length];
        this.currSize = 0;
    }

    public @Pure @NonNegative int capacity(){       //> ArrayMap::capacity p=(0,0,0/0) r=(1,1/1) loc=3  --  we don't count the pure annotation
        return this.keys.length;
    }

    public @IndexOrHigh("this.keys") int currentSize() {    //> ArrayMap::currentSize p=(0,0,0/0) r=(1,2/2) loc=3
        return this.currSize;
    }

    public V get(K k) {                             //> ArrayMap::get p=(1,0,0/0) r=(0,0/0) loc=8
        var idx = ArrayUtils.indexOf(keys, k);
        if (idx == -1) {
            return null;
        } else {
            return this.values[idx];
        }
    }

    public boolean put(K k, V v) {                  //> ArrayMap::put p=(2,0,0/0) r=(0,0/0) BUG REPORTED loc=18
        if (k == null || v == null) {
            throw new IllegalArgumentException();
        }
        var preSize = currentSize();
        if (preSize < this.keys.length) {
            // ERROR: should be < instead of <=
            for (int i = 0; i <= this.keys.length; i++) {
                if (this.keys[i] == null) {
                    this.keys[i] = k;
                    this.values[i] = v;
                    break;
                }
            }
            this.currSize = preSize + 1;
            return true;
        }
        return false;
    }

    public boolean remove(K k) {                //> ArrayMap::remove p=(1,0,0/0) r=(0,0/0) loc=14
        var preSize = currentSize();
        if (preSize > 0) {
            var idx = ArrayUtils.indexOf(this.keys, k);
            if (idx == -1) {
                return false;
            }
            this.keys[idx] = null;
            this.values[idx] = null;
            this.currSize = preSize - 1;
            return true;
        }
        return false;
    }

}
