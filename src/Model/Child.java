package Model;

import Utils.RandomHelper;

public class Child extends Chromosome {
    private Chromosome parentOne;
    private Chromosome parentTwo;

    public Child() {
    }

    public Child(Chromosome parentOne, Chromosome parentTwo, double pc, double pm) {
        this.parentOne = parentOne;
        this.parentTwo = parentTwo;
        int[] child = born(pc, pm);

        super.setChromosome(child);
        super.setFitness(super.calculateFitness());
    }

    private int[] born(double pc, double pm) {
        int[] child = crossover(pc);
        return mutation(child, pm);
    }

    private int[] crossover(double pc) {
        double random = RandomHelper.randomDoubleInInterval(0, 1);

        if (random < pc) {
            int maxSize = this.parentOne.getChromosome().length;
            int[] child = new int[maxSize];
            int randomInteger = RandomHelper.randomIntegerInInterval(maxSize);

            for (int i = 0; i < maxSize; i++) {
                if (i < randomInteger) {
                    child[i] = this.parentOne.getChromosome()[i];
                } else {
                    child[i] = this.parentTwo.getChromosome()[i];
                }
            }
            return child;
        }

        return parentOne.getChromosome();
    }

    private int [] mutation(int[] child, double pm) {
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
