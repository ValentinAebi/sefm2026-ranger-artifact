package ic9;

import liquidjava.specification.Refinement;
import liquidjava.specification.RefinementAlias;


/** ArrayWrapper is a fixed-size generic collection. */
public class ArrayWrapper<T> {
    private final @Refinement("_ >= 0") int size;
    private final Object[] delegate;

    ArrayWrapper(@Refinement("_ >= 0") int size) {  //> ArrayWrapper::constructor p=(1,1,1/1) r=none
        this.size = size;
        this.delegate = new Object[size];
    }

    public @Refinement("_ >= 0") int size() {     //> ArrayWrapper::size p=(0,0,0/0) r=(1,1/1)
        return size;
    }

    public void set(@Refinement("0 <= _ && _ < this.size()") int index, T obj) {    //> ArrayWrapper::set p=(2,1,2/2) r=none
        delegate[index] = obj;
    }

    @SuppressWarnings("unchecked") // required for normal Java compilation due to unchecked cast
    public T get(@Refinement("0 <= _ && _ < this.size()") int index) {              //> ArrayWrapper::get p=(1,1,2/2) r=(0,0/0)
        return (T) delegate[index];
    }
}
