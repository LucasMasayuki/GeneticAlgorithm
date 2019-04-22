package Utils;

public class MutationHelper {
    public static int [] mutation(int[] child, double pm, int typeOfMutation) {
        double random = RandomHelper.randomDoubleInInterval(0, 1);
        switch (typeOfMutation) {
            case 1: {
                if (random < pm) {
                    child = mutationOnOneGene(child);
                }
                break;
            }

            case 2: {
                child = mutationOnRandomAnyGenes(pm, child);
                break;
            }

            case 3: {
                if (random < pm) {
                    child = mutationOnNGenes(child);
                }
            }
        }

        return child;
    }

    private static int[] mutationOnOneGene(int[] child) {
        int random = RandomHelper.randomIntegerInInterval(child.length - 1);
        // System.out.println("Ponto da mutação " + random);
        // System.out.println("ra " + random);
        for (int i = 0; i < child.length; i++) {
            // Flip
            if (i == random) {
                if (child[i] == 1) {
                    child[i] = 0;
                } else {
                    child[i] = 1;
                }

                return child;
            }
        }

        return child;
    }

    private static int[] mutationOnRandomAnyGenes(double pm, int[] child) {
        for (int i = 0; i < child.length; i++) {
            double random = RandomHelper.randomDoubleInInterval(0, 1);
            // Flip

            if (random < pm) {
                // System.out.println("Ponto da mutação " + i);
                // System.out.println("ra " + random);
                if (child[i] == 1) {
                    child[i] = 0;
                } else {
                    child[i] = 1;
                }
            }
        }

        return child;
    }

    private static int[] mutationOnNGenes(int[] child) {
        int random = RandomHelper.randomIntegerInInterval(child.length);
        for (int i = 0; i < random; i++) {
            int mutation = RandomHelper.randomIntegerInInterval(child.length - 1);
            if (child[mutation] == 1) {
                child[mutation] = 0;
            } else {
                child[mutation] = 1;
            }

        }

        return child;
    }
}
