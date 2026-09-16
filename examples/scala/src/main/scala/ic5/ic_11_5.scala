package ic5

import scala.util.boundary


def lessThan_correct(arr1: Array[Int], arr2: Array[Int]): Boolean = boundary {  //> IC5::lessThan_correct loc=10
  for (i <- arr1.indices) {
    if (arr1(i) < arr2(i)) {
      boundary.break(true);
    } else if (arr1(i) > arr2(i)) {
      boundary.break(false)
    }
  }
  false
}

def lessThan_buggy(arr1: Array[Int], arr2: Array[Int]): Boolean = {   //> IC5::lessThan_buggy loc=10
  var i = 0
  while (i < arr1.length) {
    if (arr1(i) < arr2(i)) {
      return true
    } else if (arr1(i) > arr2(i)) {
      return false
    }
    i -= 1    // ERROR
  }
  false
}
