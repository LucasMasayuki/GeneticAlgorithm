package Model;

import Utils.CrossoverHelper;
import Utils.MutationHelper;
import Utils.RandomHelper;

public class Child extends Chromosome {
    private Chromosome parentOne;
    private Chromosome parentTwo;
    private int start = 0;
    private int end = 0;

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

    public Child(
            int[] brother,
            double pm,
            int typeOfMutation
    ) {
        brother = MutationHelper.mutation(brother, pm, typeOfMutation);
        super.setChromosome(brother);
        super.setFitness(super.calculateFitness());
    }

    private int[] born(double pc, double pm, int typeOfCross, int typeOfMutation) {
        if (typeOfCross == 2) {
            int size = parentOne.getChromosome().length;
            start = RandomHelper.randomIntegerInInterval(size);
            end = RandomHelper.randomIntegerInInterval(size);

            if (start > end) {
                int temp = end;
                end = start;
                start = temp;
            }
        }

        int[] child = CrossoverHelper.crossover(
            this.parentOne.getChromosome(),
            this.parentTwo.getChromosome(),
            pc,
            typeOfCross,
            start,
            end
        );

        return MutationHelper.mutation(child, pm, typeOfMutation);
    }

    public Child generateBrotherUniform(double pc, double pm, int typeOfMutation) {
        int[] chromosome = this.getChromosome();
        int[] brother = CrossoverHelper.invertBrother(
            chromosome,
            parentTwo.getChromosome(),
            pc
        );

        return new Child(brother, pm, typeOfMutation);
    }

    public Child generateBrotherDoubleCut(double pc, double pm, int typeOfMutation) {
        int[] chromosome = this.getChromosome();
        int[] brother = CrossoverHelper.reverseProcessDoubleCross(
                parentOne.getChromosome(),
                parentTwo.getChromosome(),
                pc,
                start,
                end
        );

        return new Child(brother, pm, typeOfMutation);
    }
}
