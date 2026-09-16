package filterLess


def filterLessThan_adHoc_correct(a: Int, ls: List[Int]): List[Int] = ls match {   //> FilterLess::filterLessThan_adHoc_correct loc=5
  case head :: next if head < a => head :: filterLessThan_adHoc_correct(a, next)
  case head :: next => filterLessThan_adHoc_correct(a, next)
  case Nil => Nil
}

def filterLessThan_adHoc_buggy(a: Int, ls: List[Int]): List[Int] = ls match {    //> FilterLess::filterLessThan_adHoc_buggy loc=5
  // ERROR: should be head < a
  case head :: next if head <= a => head :: filterLessThan_adHoc_buggy(a, next)
  case head :: next => filterLessThan_adHoc_buggy(a, next)
  case Nil => Nil
}

def filterLessThan_functional_correct(a: Int, ls: List[Int]): List[Int] =       //> FilterLess::filterLessThan_functional_correct loc=2
  ls.filter(_ < a)

def filterLessThan_functional_buggy(a: Int, ls: List[Int]): List[Int] =         //> FilterLess::filterLessThan_functional_buggy loc=2
  ls.filter(_ <= a) // ERROR: should be <
