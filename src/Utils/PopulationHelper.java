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
            int[] chromosome1 = parentOne.getChromosome();
            int[] chromosome2 = parentTwo.getChromosome();

            String parent1 = "";
            String parent2 = "";

            for (int j = chromosome1.length - 1; j > 0; j--) {
                parent1 += Integer.toString(chromosome1[j]);
                parent2 += Integer.toString(chromosome2[j]);
            }

            //System.out.println("Population "+ populationNumber + " : Parent 1 " + parent1 + " Fittest1 " + parentOne.getFitness() + " Parent 2 " + parent2 + " Fittest2 " + parentTwo.getFitness());
            System.out.println("Population "+ populationNumber + " : Parent 1 " + parent1  + " Parent 2 " + parent2);
            Child childOne = new Child(parentOne, parentTwo, pc, pm, typeOfCross, typeOfMutation);
            Child childTwo = new Child(parentTwo, parentOne, pc, pm, typeOfCross, typeOfMutation);
            int[] chromosome12 = childOne.getChromosome();
            int[] chromosome22 = childTwo.getChromosome();

            String child1 = "";
            String child2 = "";

            for (int j = chromosome12.length - 1; j > 0; j--) {
                child1 += Integer.toString(chromosome12[j]);
                child2 += Integer.toString(chromosome22[j]);
            }

            //System.out.println("Population "+ populationNumber + " : Child 1  " + child1 + " Fittest1 " + childOne.getFitness() + " Child 2  " + child2 + " Fittest2 " + childTwo.getFitness());
            System.out.println("Population "+ populationNumber + " : Child 1  " + child1 + " Child 2  " + child2);
            population[i] = childOne;
            population[i + 1] = childTwo;
        }

        MergeSort.sort(population, n);
        return population;
    }
}
