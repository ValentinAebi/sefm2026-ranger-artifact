package ic9

import scala.reflect.ClassTag


class ArrayWrapper[T: ClassTag] private(private val delegate: Array[T]) {

  def this(size: Int) =
    this(new Array[T](size))    //> ArrayWrapper::constructor aux-annot=1 loc=2

  export delegate.length as size  //> ArrayWrapper::size loc=1

  export delegate.update as set   //> ArrayWrapper::set loc=1
  
  export delegate.apply as get    //> ArrayWrapper::get loc=1

}
