package Utils;

public class NumberHelper {
    public static int binaryToDecimal(int[] binary) {
        int decimal = 0;

        for (int i = 0; i < binary.length; i++) {
            if (binary[i] == 1) {
                decimal += 2^i;
            }
        }

        return decimal;
    }

    public static int getQ(int min, int max, int numOfBits) {
        int q = (max - min) / (2^numOfBits - 1);
        return q;
    }

    public static int maxError(int q) {
        return q/2;
    }

    public static int mapped(int number, int q) {
        return 1 + number * q;
    }
}
