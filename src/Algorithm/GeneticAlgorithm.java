package Algorithm;

import Model.Child;
import Model.Chromosome;

public class GeneticAlgorithm {
    public static Chromosome initialize(
            Chromosome[] generation,
            int n,
            double pc,
            double pm
    ) {
        do {
            fillProporcionalPercentOfTotalFitness(generation);
            Roulette roulette = new Roulette(generation);
            Chromosome[] newGeneration = new Chromosome[n];

            for (int i = 0; i < n; i+=2) {
                Chromosome parentOne = roulette.spin();
                Chromosome parentTwo = roulette.spin();
                Child childOne = new Child(parentOne, parentTwo, pc, pm);
                Child childTwo = new Child(parentTwo, parentOne, pc, pm);

                newGeneration[i] = childOne;
                newGeneration[i + 1] = childTwo;
            }

            generation = newGeneration;

        } while (!objective); // The stop condition, don't implemented

        return theBestFitness(generation);
    }

    private static Chromosome theBestFitness(Chromosome[] generation) {
        double bestFitness = 0;
        int bestIdx = 0;

        for (int i = 0; i < generation.length; i++) {
            if (bestFitness < generation[i].getFitness()) {
                bestFitness = generation[i].getFitness();
                bestIdx = i;
            }
        }

        return generation[bestIdx];
    }

    private static void fillProporcionalPercentOfTotalFitness(Chromosome[] population) {
        double summationFitness = summationFitness(population);
        int sizeOfPopulation = population.length;
        double percent = 0;

        for (int i = 0; i < sizeOfPopulation; i++) {
            percent += population[i].getFitness() / summationFitness;
            population[i].setPercentOfTotalFitness(percent);
        }
    }



    private static double summationFitness(Chromosome[] population) {
        int sizeOfPopulation = population.length;
        double totalFitness = 0;

        for (int i = 0; i < sizeOfPopulation; i++) {
            double fitness = population[i].getFitness();
            totalFitness +=  fitness;
        }

        return totalFitness;
    }
}
