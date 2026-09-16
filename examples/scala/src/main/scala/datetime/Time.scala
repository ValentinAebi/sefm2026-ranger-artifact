package datetime

type Hours = Int
type Minutes = Int
type Seconds = Int


final case class Time(hours: Hours, minutes: Minutes, seconds: Seconds) {   //> Time::constructor loc=1

  def deltaTime(h: Int, min: Int, sec: Int): Time = {   //> Time::deltaTime loc=9
    val sSum = this.seconds + sec
    val seconds = (sSum % 60 + 60) % 60
    val mSum = this.minutes + min + (sSum - seconds) / 60
    val minutes = (mSum % 60 + 60) % 60
    val hSum = this.hours + h + (mSum - minutes) / 60
    val hours = (hSum % 24 + 24) % 24
    Time(hours, minutes, seconds)
  }

  def deltaHours(h: Int): Time = deltaTime(h, 0, 0)   //> Time::deltaHours loc=1

  def deltaMinutes(m: Int): Time = deltaTime(0, m, 0) //> Time::deltaMinutes loc=1

  def deltaSeconds(s: Int): Time = deltaTime(0, 0, s) //> Time::deltaSeconds loc=1

}


class MutTime(      //> MutTime::constructor loc=5
               private var hours: Hours,
               private var minutes: Minutes,
               private var seconds: Seconds
             ) {

  def moveBy(h: Int, m: Int, s: Int): Unit = {    //> MutTime::moveBy loc=8
    val sSum = this.seconds + s
    this.seconds = (sSum % 60 + 60) % 60
    val mSum = this.minutes + m + (sSum / 60)
    this.minutes = (mSum % 60 + 60) % 60
    val hSum = this.hours + h + (mSum / 60)
    // ERROR: should be normalized to 24, not 60
    this.hours = (hSum % 60 + 60) % 60;
  }

  def moveByHours(h: Int): Unit = {             //> MutTime::moveByHours loc=3
    moveBy(h, 0, 0)
  }

  def moveByMinutes(m: Int): Unit = {           //> MutTime::moveByMinutes loc=3
    moveBy(0, m, 0)
  }

  def moveBySeconds(s: Int): Unit = {           //> MutTime::moveBySeconds loc=3
    moveBy(0, 0, s)
  }

}
