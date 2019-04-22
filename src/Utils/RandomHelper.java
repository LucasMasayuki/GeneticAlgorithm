package Utils;

import java.util.Random;

public class RandomHelper {
    public static Random random = new Random();
    public static int randomIntegerInInterval(int max) {
        return random.nextInt(max);
    }

    public static double randomDoubleInInterval(double min, double max) {
        double diff = max - min;
        return  min + (Math.random() * diff);
    }
}
