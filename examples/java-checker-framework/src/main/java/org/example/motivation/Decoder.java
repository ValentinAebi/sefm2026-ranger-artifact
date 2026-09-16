package org.example.motivation;

import static org.example.motivation.List.*;

import org.checkerframework.checker.index.qual.LessThan;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.index.qual.Positive;


public class Decoder {
    
    public static List<Point<@NonNegative @LessThan("#2") Integer, @NonNegative @LessThan("#3") Integer>> decodeAll(    //> Decoder::decodeAll p=(3,2,2/2) r=(4,4/4) bypass=1 REPORTED loc=9
        Integer[] data, @Positive int xSize, @Positive int ySize
    ) {
        var validData = filter(arrayToList(data), (d) -> d >= 0);
        return map(validData, (d) -> {
            // for an unknown reason, this assertion does not help
            assert d >= 0 : "@AssumeAssertion(index) : semantics of filter";
            return decode(d, xSize, ySize);
        });
    }

    private static Point<@NonNegative @LessThan("#2") Integer, @NonNegative @LessThan("#3") Integer> decode(            //> Decoder::decode p=(3,3,3/3) r=(4,4/4) bypass=4 loc=12
        @NonNegative int datapoint, @Positive int xSize, @Positive int ySize
    ) {
        var zero = 0;
        assert zero < xSize - 1 + 1 : "@AssumeAssertion(index) : xSize is @Positive";
        var x = clamp(zero, xSize - 1, datapoint >> 8);
        assert x < xSize : "@AssumeAssertion(index) : spec of clamp tells us that x < xSize - 1 + 1";
        assert x >= 0 : "@AssumeAssertion(index) : semantics of clamp";
        var y = datapoint % ySize;
        assert y < ySize : "@AssumeAssertion(index) : @NonNegative % @Positive";
        return new Point<>(x, y);
    }

    
    private static @LessThan("#2 + 1") int clamp(@LessThan("#2 + 1") int min, int max, int x) {         //> Decoder::clamp p=(3,1,1/1) r=(1,1/2) loc=9  --  no way of expressing the lower bound on the result
        if (x <= min) {
            return min;
        } else if (x <= max) {
            return x;
        } else {
            return max;
        }
    }

}
