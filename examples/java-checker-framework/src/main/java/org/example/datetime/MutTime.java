package org.example.datetime;

import org.checkerframework.common.value.qual.IntRange;

public class MutTime {                                  //> MutTime::constructor p=(3,3,6/6) r=none loc=4
    private @IntRange(from = 0, to = 23) int hours;
    private @IntRange(from = 0, to = 59) int minutes;
    private @IntRange(from = 0, to = 59) int seconds;

    public MutTime(                                     // ignored since it has no equivalent in Licorne
        @IntRange(from = 0, to = 23) int hours,
        @IntRange(from = 0, to = 59) int minutes,
        @IntRange(from = 0, to = 59) int seconds
    ) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public void moveBy(int h, int m, int s) {           //> MutTime::moveBy p=(3,0,0/0) r=none BUG REPORTED loc=8
        var sSum = this.seconds + s;
        this.seconds = (sSum % 60 + 60) % 60;
        var mSum = this.minutes + m + (sSum / 60);
        this.minutes = (mSum % 60 + 60) % 60;
        var hSum = this.hours + h + (mSum / 60);
        // ERROR: should be normalized to 24, not 60
        this.hours = (hSum % 60 + 60) % 60;
    }

    public void moveByHours(int h) {        //> MutTime::moveByHours p=(1,0,0/0) r=none loc=3
        moveBy(h, 0, 0);
    }

    public void moveByMinutes(int m) {      //> MutTime::moveByMinutes p=(1,0,0/0) r=none loc=3
        moveBy(0, m, 0);
    }

    public void moveBySeconds(int s) {      //> MutTime::moveBySeconds p=(1,0,0/0) r=none loc=3
        moveBy(0, 0, s);
    }

}
