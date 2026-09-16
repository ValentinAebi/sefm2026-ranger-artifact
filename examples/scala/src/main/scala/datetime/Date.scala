package datetime

import datetime.Date.nDaysInMonth

type Year = Int
type Month = Int
type DayOfMonth = Int

final case class Date(year: Year, month: Month, day: DayOfMonth) {    //> Date::constructor loc=1

  def nextDay(): Date =                 //> Date::nextDay loc=8
    if (this.day < nDaysInMonth(this.year, this.month)) {
      new Date(year = this.year, month = this.month, day = this.day + 1);
    } else if (this.month < 12) {
      new Date(year = this.year, month = this.month + 1, day = 1);
    } else {
      new Date(year = this.year + 1, month = 1, day = 1);
    }

  def previousDay(): Date =           //> Date::previousDay loc=8
    if (this.day > 1) {
      new Date(year = this.year, month = this.month, day = this.day - 1);
    } else if (this.month > 1) {
      // ERROR: the programmer forgot to update `this.month() + 1` to `this.month() - 1` after copy-pasting from nextDay
      new Date(year = this.year, month = this.month + 1, day = nDaysInMonth(this.year, this.month - 1));
    } else {
      new Date(year = this.year - 1, month = 12, day = 31);
    }
}

object Date {

  def nDaysInMonth(year: Year, month: Month): Int = { //> Date::nDaysInMonth loc=9
    if (month == 2) {
      // slightly simplified...
      return if year % 4 != 0 then 28
      else if year % 100 != 0 then 29
      else if year % 400 != 0 then 28
      else 29
    }
    if month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 then 31 else 30
  }

}
