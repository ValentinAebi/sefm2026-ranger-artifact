// From https://github.com/liquid-java/liquidjava/blob/main/liquidjava-example/src/main/java/testSuite/classes/car_correct/
package org.example.lj4;

import org.checkerframework.checker.index.qual.Positive;
import org.checkerframework.common.value.qual.IntRange;


public class Car {      //> Car::constructor p=(2,2,3/3) r=none loc=3

    private @IntRange(from = 1801, to = 2049) int year;

    private @Positive int seats;

    public void setYear(@IntRange(from = 1801, to = 2049) int year) {   //> Car::setYear p=(1,1,2/2) r=none loc=3
        this.year = year;
    }

    public @IntRange(from = 1801, to = 2049) int getYear() {            //> Car::getYear p=(0,0,0/0) r=(1,2/2) loc=3
        return year;
    }

    //	@Refinement("_ == GreaterThan(year, y)")
    //	public boolean isOlderThan(int y) {
    //		return this.year > y;
    //	}

}
