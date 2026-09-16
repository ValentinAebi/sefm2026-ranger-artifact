package lj4

import scala.compiletime.uninitialized


class Car {   //> Car::constructor loc=3
  private var _year: Int = uninitialized
  private var _seats: Int = uninitialized

  def year_=(y: Int): Unit = {    //> Car::setYear loc=4
    require(1800 < y && y < 2050)
    this._year = y
  }

  def year: Int = _year     //> Car::getYear loc=1

}
