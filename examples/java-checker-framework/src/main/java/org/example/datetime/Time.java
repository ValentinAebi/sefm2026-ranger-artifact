package org.example.datetime;

import org.checkerframework.common.value.qual.IntRange;

record Time(                                        //> Time::constructor p=(3,3,6/6) r=none loc=5
    @IntRange(from = 0, to = 23) int hours,
    @IntRange(from = 0, to = 59) int minutes,
    @IntRange(from = 0, to = 59) int seconds
) {

    public Time deltaTime(int h, int m, int s) {    //> Time::deltaTime p=(3,0,0/0) r=(0,0/0) loc=9
        var sSum = seconds() + s;
        var seconds = (sSum % 60 + 60) % 60;
        var mSum = minutes() + m + (sSum - seconds) / 60;
        var minutes = (mSum % 60 + 60) % 60;
        var hSum = hours() + h + (mSum - minutes) / 60;
        var hours = (hSum % 24 + 24) % 24;
        return new Time(hours, minutes, seconds);
    }

    public Time deltaHours(int h) {                 //> Time::deltaHours p=(1,0,0/0) r=(0,0/0) loc=3
        return deltaTime(h, 0, 0);
    }

    public Time deltaMinutes(int m) {               //> Time::deltaMinutes p=(1,0,0/0) r=(0,0/0) loc=3
        return deltaTime(0, m, 0);
    }

    public Time deltaSeconds(int s) {               //> Time::deltaSeconds p=(1,0,0/0) r=(0,0/0) loc=3
        return deltaTime(0, 0, s);
    }

}
