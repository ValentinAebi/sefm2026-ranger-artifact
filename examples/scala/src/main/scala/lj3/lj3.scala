package lj3


val x: Int = ???

val y: Int = ???

val z: Int = ???    //> LJ3::ternary-ref loc=1

def absDiv(a: Int, b: Int): Int = { //> LJ3::absDiv_correct loc=4
  val res = a / b                   //> LJ3::absDiv_buggy loc=4
  if res >= 0 then res else -res
}
