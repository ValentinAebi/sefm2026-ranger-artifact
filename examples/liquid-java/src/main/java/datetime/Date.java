package datetime;

import liquidjava.specification.Refinement;
import liquidjava.specification.RefinementAlias;


record Date(int year, @Refinement("1 <= _ && _ <= 12") int month, @Refinement("1 <= _ && _ <= nDaysInMonth(year, month)") int day) {   //> Date::constructor p=(3,2,4/4) r=none

    public Date nextDay() {     //> Date::nextDay p=(0,0,0/0) r=(0,0/0)
        if (this.day() < nDaysInMonth(this.year(), this.month())) {
            return new Date(this.year(), this.month(), this.day() + 1);
        } else if (this.month() < 12) {
            return new Date(this.year(), this.month() + 1, 1);
        } else {
            return new Date(this.year() + 1, 1, 1);
        }
    }

    public Date previousDay() {     //> Date::previousDay p=(0,0,0/0) r=(0,0/0) BUG
        if (this.day() > 1) {
            return new Date(this.year(), this.month(), this.day() - 1);
        } else if (this.month() > 1) {
            // ERROR: the programmer forgot to update `this.month() + 1` to `this.month() - 1` after copy-pasting from nextDay
            return new Date(this.year(), this.month() + 1, nDaysInMonth(this.year(), this.month() - 1));
        } else {
            return new Date(this.year() - 1, 12, nDaysInMonth(this.year() - 1, 12));
        }
    }

    public static @Refinement("28 <= _ && _ <= 31") int nDaysInMonth(int year, @Refinement("1 <= _ && _ <= 31") int month) {     //> Date::nDaysInMonth p=(2,1,2/2) r=(1,2/2)
        if (month == 2) {
            // slightly simplified...
            return year % 4 != 0 ? 28
                    : year % 100 != 0 ? 29
                            : year % 400 != 0 ? 28
                                    : 29;
        }
        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12:
                return 31;
            default:
                return 30;
        }
    }

}
