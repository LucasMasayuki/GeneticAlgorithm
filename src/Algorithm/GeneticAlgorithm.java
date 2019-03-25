package Algorithm;

import Model.Child;
import Model.Chromosome;

public class GeneticAlgorithm {
    private int min = 0;
    private int max = 0;
    private static int bits;

    public static Chromosome initialize(
            int length,
            int n,
            double pc,
            double pm
    ) {
        bits = length;
        Chromosome[] initialPopulation = generateInitialPopulation(n);
        Chromosome[] generation = initialPopulation;

        double fittest = getMaxFittest(initialPopulation);
        System.out.println("Generation: " + 1 + " Fittest " + fittest);
        int countGeneration = 1;

        do {
            fillProportionalPercentOfTotalFitness(generation);
            Roulette roulette = new Roulette(generation);
            Chromosome[] newGeneration = new Chromosome[n];

            for (int i = 0; i < n; i+=2) {
                Chromosome parentOne = roulette.spin();
                Chromosome parentTwo = roulette.spin();
                int[] chromossome1 = parentOne.getChromosome();
                int[] chromossome2 = parentTwo.getChromosome();

                String parent1 = "";
                String parent2 = "";

                for (int j = chromossome1.length - 1; j > 0; j--) {
                    parent1 += Integer.toString(chromossome1[j]);
                    parent2 += Integer.toString(chromossome2[j]);
                }

                System.out.println("Generation: " + countGeneration + " Parent 1 " + parent1 + " Fittest1 " + parentOne.getFitness() + " Parent 2 " + parent2 + " Fittest2 " + parentTwo.getFitness());
                Child childOne = new Child(parentOne, parentTwo, pc, pm);
                Child childTwo = new Child(parentTwo, parentOne, pc, pm);
                int[] chromossome12 = childOne.getChromosome();
                int[] chromossome22 = childTwo.getChromosome();

                String child1 = "";
                String child2 = "";

                for (int j = chromossome1.length - 1; j > 0; j--) {
                    child1 += Integer.toString(chromossome12[j]);
                    child2 += Integer.toString(chromossome22[j]);
                }

                System.out.println("Generation: " + countGeneration + " Child 1 " + child1 + " Fittest1 " + childOne.getFitness() + " Child 2 " + child2 + " Fittest2 " + childTwo.getFitness());
                newGeneration[i] = childOne;
                newGeneration[i + 1] = childTwo;
            }

            generation = newGeneration;
            countGeneration++;
            fittest = getMaxFittest(generation);
            System.out.println("Generation: " + countGeneration + " Fittest " + fittest);

        } while (countGeneration != 15); // The stop condition, don't implemented

        System.out.println("Generation: " + countGeneration);
        return theBestFitness(generation);
    }

    private static Chromosome[] generateInitialPopulation(int n) {
        Chromosome[] population = new Chromosome[n];
        for (int i = 0; i < n; i++) {
            population[i] = new Chromosome(bits);
        }

        return population;
    }

    private static double getMaxFittest(Chromosome[] initialPopulation) {
        double maxFit = 0;
        for (int i = 0; i < initialPopulation.length; i++) {
            if (maxFit <= initialPopulation[i].getFitness()) {
                maxFit = initialPopulation[i].getFitness();
            }
        }
        return maxFit;
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

    private static void fillProportionalPercentOfTotalFitness(Chromosome[] population) {
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
