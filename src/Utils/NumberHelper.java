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

    public static int[] decimalToBinary(int decimal) {
        String binaryString = Integer.toBinaryString(decimal);
        int[] binary = new int[binaryString.length()];
        int j = 0;

        for (int i = binaryString.length() - 1; i > 0; i--) {
            binary[i] = binaryString.charAt(j);
            j++;
        }

        return binary;
    }

    public static int getMaxNumberWiwhNBits(int numOfBits) {
        int[] binary = new int[numOfBits];

        for (int i = 0; i < numOfBits; i++) {
            binary[i] = 1;
        }

        return binaryToDecimal(binary);
    }

    public static double getQ(double min, double max, int numOfBits) {
        double q = (max - min) / Math.pow(2, numOfBits) - 1;
        return 1 / q;
    }

    public static double realX(int decimal, double min, double max, int numOfBits) {
        double q = min + (max - min) * decimal / (Math.pow(2, numOfBits) - 1);
        return q;
    }

    public static double maxError(int q) {
        return q/2;
    }

    public static double mapped(int min, int number, double q) {
        return min + number * q;
    }
}
