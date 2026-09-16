package lj2


def sum_original(n: Int): Int = {   //> Sum::sum_original loc=8
  if (n <= 1) {
    0
  } else {
    val t1 = sum_original(n-1)
    n + t1
  }
}

def sum_fixed(n: Int): Int = {    //> Sum::sum_fixed loc=10
  if (n < 0) {
    0
  } else if (n <= 1) {
    n
  } else {
    val t1 = sum_original(n-1)
    n + t1
  }
}
