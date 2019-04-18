package Utils;

import Algorithm.Roulette;
import Model.Child;
import Model.Chromosome;

public class PopulationHelper {
    private static Chromosome theBestFitness(Chromosome[] generation) {
        double bestFitness = Double.MAX_VALUE;
        int bestIdx = 0;

        for (int i = 0; i < generation.length; i++) {
            if (bestFitness < generation[i].getFitness()) {
                bestFitness = generation[i].getFitness();
                bestIdx = i;
            }
        }

        return generation[bestIdx];
    }

    public static Chromosome[] generatePopulation(
            Chromosome[] currentPopulation,
            int n,
            double pc,
            double pm,
            int typeOfCross,
            int typeOfMutation,
            boolean crossWithBest,
            int populationNumber
    ) {
        Roulette roulette = new Roulette(currentPopulation);
        Chromosome[] population = new Chromosome[n];

        for (int i = 0; i < n; i += 2) {
            Chromosome parentOne;

            if (crossWithBest) {
                parentOne = theBestFitness(currentPopulation);
            } else {
                parentOne = roulette.spin();
            }

            Chromosome parentTwo = roulette.spin();

            print(populationNumber, parentOne, parentTwo, false);

            double random = RandomHelper.randomDoubleInInterval(0, 1);
            boolean cross = random < pc;

            Child childOne = new Child(parentOne, parentTwo, cross, pm, typeOfCross, typeOfMutation);
            Child childTwo;
            if (typeOfCross == 1) {
                childTwo = new Child(parentTwo, parentOne, cross, pm, typeOfCross, typeOfMutation);
            } else if (typeOfCross == 2) {
                childTwo = childOne.generateBrotherDoubleCut(cross, pm, typeOfMutation);
            } else {
                childTwo = childOne.generateBrotherUniform(cross, pm, typeOfMutation);
            }

            print(populationNumber, childOne, childTwo, true);

            population[i] = childOne;
            population[i + 1] = childTwo;
        }

        MergeSort.sort(population, n);
        return population;
    }

    private static void print(int populationNumber, Chromosome chromosomeOne, Chromosome chromosomeTwo, boolean isChild) {
        int[] chromosome12 = chromosomeOne.getChromosome();
        int[] chromosome22 = chromosomeTwo.getChromosome();

        String chromosome1 = "";
        String chromosome2 = "";

        for (int j = chromosome12.length - 1; j > 0; j--) {
            chromosome1 += Integer.toString(chromosome12[j]);
            chromosome2 += Integer.toString(chromosome22[j]);
        }

        if (isChild) {
            //System.out.println("Population "+ populationNumber + " : Child 1  " + child1 + " Fittest1 " + childOne.getFitness() + " Child 2  " + child2 + " Fittest2 " + childTwo.getFitness());
            System.out.println("Population "+ populationNumber + " : Child 1  " + chromosome1 + " Child 2  " + chromosome2);
        } else {
            //System.out.println("Population "+ populationNumber + " : Parent 1 " + parent1 + " Fittest1 " + parentOne.getFitness() + " Parent 2 " + parent2 + " Fittest2 " + parentTwo.getFitness());
            System.out.println("Population "+ populationNumber + " : Parent 1 " + chromosome1  + " Parent 2 " + chromosome2);
        }
    }
}
