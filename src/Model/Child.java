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
        boolean cross,
        double pm,
        int typeOfCross,
        int typeOfMutation
    ) {
        this.parentOne = parentOne;
        this.parentTwo = parentTwo;
        int[] child = born(cross, pm, typeOfCross, typeOfMutation);

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

    private int[] born(boolean cross, double pm, int typeOfCross, int typeOfMutation) {
        if (typeOfCross == 2) {
            int size = parentOne.getChromosome().length;
            start = RandomHelper.randomIntegerInInterval(size);
            end = RandomHelper.randomIntegerInInterval(size);

            if (start > end) {
                int temp = end;
                end = start;
                start = temp;
            }

            //System.out.println("pontos de corte comeco " + start + " fim " + end);
        }

        int[] child = CrossoverHelper.crossover(
            this.parentOne.getChromosome(),
            this.parentTwo.getChromosome(),
            cross,
            typeOfCross,
            start,
            end
        );

        return MutationHelper.mutation(child, pm, typeOfMutation);
    }

    public Child generateBrotherUniform(boolean cross, double pm, int typeOfMutation) {
        int[] chromosome = this.getChromosome();
        int[] brother = CrossoverHelper.invertBrother(
            chromosome,
            parentTwo.getChromosome(),
            cross
        );

        return new Child(brother, pm, typeOfMutation);
    }

    public Child generateBrotherDoubleCut(boolean cross, double pm, int typeOfMutation) {
        int[] brother = CrossoverHelper.reverseProcessDoubleCross(
                parentOne.getChromosome(),
                parentTwo.getChromosome(),
                cross,
                start,
                end
        );

        return new Child(brother, pm, typeOfMutation);
    }
}
