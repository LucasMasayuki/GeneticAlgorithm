package Model;

import Utils.CrossoverHelper;
import Utils.MutationHelper;

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
        int[] child = CrossoverHelper.crossover(
            this.parentOne.getChromosome(),
            this.parentTwo.getChromosome(),
            pc,
            typeOfCross
        );

        return MutationHelper.mutation(child, pm, typeOfMutation);
    }

    public Child generateBrother(double pc, double pm, int typeOfMutation) {
        int[] chromosome = this.getChromosome();
        int[] brother = CrossoverHelper.invertBrother(
            chromosome,
            parentTwo.getChromosome(),
            pc
        );

        return new Child(brother, pm, typeOfMutation);
    }
}
