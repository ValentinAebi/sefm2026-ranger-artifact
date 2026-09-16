package arraymap

import scala.reflect.ClassTag
import scala.util.boundary

class ArrayMap[K: ClassTag, V: ClassTag] private( //> ArrayMap::constructor loc=5
                                                  private val keys: Array[Option[K]],
                                                  private val values: Array[Option[V]],
                                                  private var _currSize: Int
                                                ) {

  def this(capacity: Int) = this(new Array(capacity), new Array(capacity), 0) //> ArrayMap::aux-constructor loc=1

  export keys.length as capacity //> ArrayMap::capacity loc=1

  def currentSize: Int = _currSize //> ArrayMap::currentSize loc=1

  def get(k: K): Option[V] = { //> ArrayMap::get loc=6
    val idx = keys.indexOf(Some(k))
    Option.when(idx != -1) {
      values(idx)
    }.flatten
  }

  def put(k: K, v: V): Boolean = { //> ArrayMap::put loc=17
    val preSize = currentSize
    val canAdd = preSize < capacity
    if (canAdd) {
      boundary {
        // ERROR: should be until instead of to (or better: for i <- keys.indices)
        for (i <- 0 to keys.length) {
          if (keys(i) == null) {
            keys(i) = Some(k)
            values(i) = Some(v)
          }
        }
      }
      _currSize = preSize + 1
    }
    canAdd
  }

  def remove(k: K): Boolean = { //> ArrayMap::remove loc=13
    val preSize = currentSize
    if (preSize > 0) {
      val idx = keys.indexOf(Some(k))
      if idx == -1 then false
      else {
        keys(idx) = None
        values(idx) = None
        _currSize = preSize - 1
        true
      }
    } else false
  }

}

extension [T](a: Array[T]) def indexOf(elem: T): Int = {    //> Array::indexOf loc=10
  var i = 0
  while (i < a.length) {
    if (a(i) == elem) {
      return i
    }
    i += 1
  }
  -1
}
