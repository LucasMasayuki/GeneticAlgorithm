package Utils;

public class MutationHelper {
    public static int [] mutation(int[] child, double pm, int typeOfMutation) {
        double random = RandomHelper.randomDoubleInInterval(0, 1);
        if (random < pm) {
            switch (typeOfMutation) {
                case 1: {
                    child = mutationOnOneGene(child);
                    break;
                }

                case 2: {
                    child = mutationOnRandomAnyGenes(child);
                    break;
                }
            }
        }

        return child;
    }

    private static int[] mutationOnOneGene(int[] child) {
        double random = RandomHelper.randomDoubleInInterval(0, child.length);
        for (int i = 0; i < child.length; i++) {
            // Flip
            if (i == random) {
                child[i] = child[i] == 1 ? 0 : 1;

                return child;
            }
        }

        return child;
    }

    private static int[] mutationOnRandomAnyGenes(int[] child) {
        for (int i = 0; i < child.length; i++) {
            // Flip
            child[i] = child[i] == 1 ? 0 : 1;
        }

        return child;
    }
}
