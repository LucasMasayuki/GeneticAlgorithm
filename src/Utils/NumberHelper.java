package Utils;

public class NumberHelper {
    public static int binaryToDecimal(int[] binary) {
        int decimal = 0;

        for (int i = 0; i < binary.length; i++) {
            if (binary[i] == 1) {
                decimal += Math.pow(2, i);
            }
        }

        return decimal;
    }

    public static double realX(int decimal, double min, double max, int numOfBits) {
        double xReal = (decimal * (max - min) / (Math.pow(2, numOfBits) - 1)) + min;
        return xReal;
    }
}
