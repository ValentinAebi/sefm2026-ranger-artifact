package sorting

import scala.reflect.ClassTag
import java.lang.System


def sort[T: ClassTag](array: Array[T], lessThan: (T, T) => Boolean): Unit = {   //> MergeSort::sort loc=4
  val tmpArray = new Array[T](array.length)
  doSort(array, tmpArray, lessThan, 0, array.length - 1)
}

private def doSort[T](array: Array[T], tmpArray: Array[T],
                      lt: (T, T) => Boolean, left: Int, right: Int): Unit = {   //> MergeSort::doSort loc=9
  if (left < right) {
    val mid = (left + right) / 2;
    doSort(array, tmpArray, lt, left, mid);
    doSort(array, tmpArray, lt, mid + 1, right);
    merge(array, tmpArray, lt, left, mid, right);
  }
}

private def merge[T](array: Array[T], tmpArray: Array[T], lt: (T, T) => Boolean,  //> MergeSort::merge loc=22
                     left: Int, mid: Int, right: Int): Unit = {
  require(left < right)   // excluded from l.o.c. count

  var i = left
  var j = mid + 1

  System.arraycopy(array, left, tmpArray, left, right + 1 - left)

  for (k <- left to right) {
    if (j > right) {
      array(k) = tmpArray(i)
      i += 1
    } else if (i > mid) {
      array(k) = tmpArray(j)
      j -= 1  // ERROR: should be j += 1 here
    } else if (lt(tmpArray(j), tmpArray(i))) {
      array(k) = tmpArray(j)
      j += 1
    } else {
      array(k) = tmpArray(i)
      i += 1
    }
  }
}
