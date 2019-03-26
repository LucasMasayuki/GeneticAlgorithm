package Utils;

public class MutationHelper {
    public static int [] mutation(int[] child, double pm, int typeOfMutation) {
        int maxSize = child.length;

        for (int i = 0; i < maxSize; i++) {
            double random = RandomHelper.randomDoubleInInterval(0, 1);

            if (random < pm) {
                // Flip
                child[i] = child[i] == 1 ? 0 : 1;
            }
        }

        return child;
    }
}
