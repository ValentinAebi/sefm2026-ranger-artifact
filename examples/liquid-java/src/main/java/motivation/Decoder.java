package motivation;

import java.util.Arrays;
import java.util.List;

import liquidjava.specification.Refinement;
import liquidjava.specification.RefinementAlias;


@RefinementAlias("NonZeroLength(int l) {l > 0}")
@RefinementAlias("Coord(int c, int axisSize) { 0 <= c && c < axisSize}")
public class Decoder {
    
    public static List<Point<@Refinement("Coord(_, xSize)") Integer, @Refinement("Coord(_, xSize)") Integer>> decodeAll(    //> Decoder::decodeAll p=(3,2,2/2) r=(2,4/4)
        int[] data, @Refinement("NonZeroLength(_)") int xSize, @Refinement("NonZeroLength(_)") int ySize
    ) {
        var validData = Arrays.stream(data).filter(d -> d >= 0);
        return validData.mapToObj(d -> decode(d, xSize, ySize)).toList();
    }

    private static Point<@Refinement("Coord(_, xSize)") Integer, @Refinement("Coord(_, xSize)") Integer> decode(            //> Decoder::decode p=(3,3,3/3) r=(2,4/4)
        @Refinement("_ >= 0") int datapoint, @Refinement("NonZeroLength(_)") int xSize, @Refinement("NonZeroLength(_)") int ySize
    ) {
        var x = clamp(0, xSize - 1, datapoint / 256);
        var y = datapoint % ySize;
        return new Point<>(x, y);
    }

    private static @Refinement("min <= _ && _ <= max") int clamp(int min, @Refinement("_ >= min") int max, int x) {         //> Decoder::clamp p=(3,1,1/1) r=(1,2/2)
        if (x <= min) {
            return min;
        } else if (x <= max) {
            return x;
        } else {
            return max;
        }
    }

}
