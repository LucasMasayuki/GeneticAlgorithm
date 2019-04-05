package Utils;

public class CrossoverHelper {
    public static int[] crossover(
        int[] parentOne,
        int[] parentTwo,
        double pc,
        int typeOfCross,
        int start,
        int end
    ) {
        int[] child = new int[parentOne.length];

        switch (typeOfCross) {
            case 1: {
                child = singleCross(parentOne, parentTwo, pc);
                break;
            }

            case 2: {
                child = doubleCross(parentOne, parentTwo, pc, start, end);
                break;
            }

            case 3: {
                child = uniformCross(parentOne, parentTwo, pc);
                break;
            }
        }

        return child;
    }

    public static int[] invertBrother(
        int[] chromosome,
        int[] parentTwo,
        double pc
    ) {
        double random = RandomHelper.randomDoubleInInterval(0, 1);

        if (random < pc) {
            int maxSize = chromosome.length;
            int[] brother = new int[maxSize];
            for (int i = 0; i < maxSize; i++) {
                if (chromosome[i] == 1) {
                    brother[i] = 0;
                } else {
                    brother[i] = 1;
                }
            }

            return brother;
        }

        return parentTwo;
    }

    public static int[] reverseProcessDoubleCross(
            int[] parentOne,
            int[] parentTwo,
            double pc,
            int start,
            int end
    ) {
        double random = RandomHelper.randomDoubleInInterval(0, 1);

        if (random < pc) {
            int maxSize = parentOne.length;
            int[] brother = new int[maxSize];
            for (int i = 0; i < maxSize; i++) {
                if (i < start || i > end) {
                    brother[i] = parentTwo[i];
                } else {
                    brother[i] = parentOne[i];
                }
            }

            return brother;
        }

        return parentTwo;
    }

    private static int[] singleCross(
        int[] parentOne,
        int[] parentTwo,
        double pc
    ) {
        double random = RandomHelper.randomDoubleInInterval(0, 1);

        if (random < pc) {
            int maxSize = parentOne.length;
            int[] child = new int[maxSize];
            int positionToStop = RandomHelper.randomIntegerInInterval(maxSize);

            for (int i = 0; i < maxSize; i++) {
                if (i < positionToStop) {
                    child[i] = parentOne[i];
                } else {
                    child[i] = parentTwo[i];
                }
            }
            return child;
        }

        return parentOne;
    }

    private static int[] doubleCross(
        int[] parentOne,
        int[] parentTwo,
        double pc,
        int start,
        int end
    ) {
        double random = RandomHelper.randomDoubleInInterval(0, 1);

        if (random < pc) {
            int maxSize = parentOne.length;
            int[] child = new int[maxSize];

            for (int i = 0; i < maxSize; i++) {
                if (i < start || i > end) {
                    child[i] = parentOne[i];
                } else {
                    child[i] = parentTwo[i];
                }
            }
            return child;
        }

        return parentOne;
    }

    private static int[] uniformCross(
        int[] parentOne,
        int[] parentTwo,
        double pc
    ) {
        double random = RandomHelper.randomDoubleInInterval(0, 1);

        if (random < pc) {
            int maxSize = parentOne.length;

            int[] mask = new int[maxSize];
            int[] child = new int[maxSize];

            for (int i = 0; i < maxSize; i++) {
                int intRandom = RandomHelper.randomIntegerInInterval(1);
                mask[i] = intRandom;
            }

            for (int i = 0; i < maxSize; i++) {
                if (mask[i] == 1) {
                    child[i] = parentOne[i];
                } else {
                    child[i] = parentTwo[i];
                }
            }

            return child;
        }

        return parentOne;
    }
}
