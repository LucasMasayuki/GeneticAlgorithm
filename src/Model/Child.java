package Model;

import Utils.RandomHelper;

public class Child extends Chromosome {
    private Chromosome parentOne;
    private Chromosome parentTwo;

    public Child(
        Chromosome parentOne,
        Chromosome parentTwo,
        double pc,
        double pm,
        int typeOfCross,
        int typeOfMutation
    ) {
        this.parentOne = parentOne;
        this.parentTwo = parentTwo;
        int[] child = born(pc, pm, typeOfCross, typeOfMutation);

        super.setChromosome(child);
        super.setFitness(super.calculateFitness());
    }

    private int[] born(double pc, double pm, int typeOfCross, int typeOfMutation) {
        int[] child = crossover(pc, typeOfCross);

        return mutation(child, pm, typeOfMutation);
    }

    private int[] crossover(double pc, int typeOfCross) {
        double random = RandomHelper.randomDoubleInInterval(0, 1);

        if (random < pc) {
            int maxSize = this.parentOne.getChromosome().length;
            int[] child = new int[maxSize];
            int positionToStop = RandomHelper.randomIntegerInInterval(maxSize);

            for (int i = 0; i < maxSize; i++) {
                if (i < positionToStop) {
                    child[i] = this.parentOne.getChromosome()[i];
                } else {
                    child[i] = this.parentTwo.getChromosome()[i];
                }
            }
            return child;
        }

        return parentOne.getChromosome();
    }

    private int [] mutation(int[] child, double pm, int typeOfMutation) {
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
