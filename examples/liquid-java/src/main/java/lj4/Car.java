// From https://github.com/liquid-java/liquidjava/blob/main/liquidjava-example/src/main/java/testSuite/classes/car_correct/
package lj4;

import liquidjava.specification.Refinement;
import liquidjava.specification.RefinementAlias;

@RefinementAlias("Positive(int x) { x > 0}")
@RefinementAlias("CarAcceptableYears(int x) { x > 1800 && x < 2050}")
@RefinementAlias("GreaterThan(int x, int y) {x > y}")
public class Car {      //> Car::constructor p=(2,2,3/3) r=none

    @Refinement("CarAcceptableYears(year)")
    private int year;

    @Refinement("Positive(_)")
    private int seats;

    public void setYear(@Refinement("CarAcceptableYears(_)") int year) {    //> Car::setYear p=(1,1,2/2) r=none
        this.year = year;
    }

    @Refinement("CarAcceptableYears(_)")
    public int getYear() {  //> Car::getYear p=(0,0,0/0) r=(1,2/2)
        return year;
    }

    //	@Refinement("_ == GreaterThan(year, y)")
    //	public boolean isOlderThan(int y) {
    //		return this.year > y;
    //	}

}
