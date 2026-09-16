package datetime;

import liquidjava.specification.Refinement;
import liquidjava.specification.RefinementAlias;


record Time(    //> Time::constructor p=(3,3,6/6) r=none
    @Refinement("0 <= _ && _ <= 23") int hours,
    @Refinement("0 <= _ && _ <= 59") int minutes,
    @Refinement("0 <= _ && _ <= 59") int seconds
) {

    public Time deltaTime(int h, int m, int s) {    //> Time::deltaTime p=(3,0,0/0) r=(0,0/0)
        int sSum = seconds() + s;
        int seconds = (sSum % 60 + 60) % 60;
        int mSum = minutes() + m + (sSum - seconds) / 60;
        int minutes = (mSum % 60 + 60) % 60;
        int hSum = hours() + h + (mSum - minutes) / 60;
        int hours = (hSum % 24 + 24) % 24;
        return new Time(hours, minutes, seconds);
    }

    public Time deltaHours(int h) {     //> Time::deltaHours p=(1,0,0/0) r=(0,0/0)
        return deltaTime(h, 0, 0);
    }

    public Time deltaMinutes(int m) {   //> Time::deltaMinutes p=(1,0,0/0) r=(0,0/0)
        return deltaTime(0, m, 0);
    }

    public Time deltaSeconds(int s) {   //> Time::deltaSeconds p=(1,0,0/0) r=(0,0/0)
        return deltaTime(0, 0, s);
    }

}
