package Algorithm;

import Model.Chromosome;
import Utils.MergeSort;
import Utils.PopulationHelper;

import java.util.Arrays;

public class GeneticAlgorithm {
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
        int countGeneration = 0;

        do {
            fillProportionalPercentOfTotalFitness(generation);
            Chromosome[] newGeneration = new Chromosome[n];

            Chromosome[] populationOne = PopulationHelper.generatePopulationOne(generation.clone(), n, pc, pm);
            Chromosome[] populationTwo = PopulationHelper.generatePopulationTwo(generation.clone(), n, pc, pm);
            Chromosome[] populationThree = PopulationHelper.generatePopulationThree(generation.clone(), n, pc, pm);
            Chromosome[] populationFour = PopulationHelper.generatePopulationFour(generation.clone(), n, pc, pm);
            Chromosome[] populationFive = PopulationHelper.generatePopulationFive(generation.clone(), n, pc, pm);
            Chromosome[] populationSix = PopulationHelper.generatePopulationSix(generation.clone(), n, pc, pm);

            Chromosome[] merged = combine(populationOne, populationTwo);
            merged = combine(merged, populationThree);
            merged = combine(merged, populationFour);
            merged = combine(merged, populationFive);
            merged = combine(merged, populationSix);

            MergeSort.sort(merged, 60);

            for (int j = 0; j < newGeneration.length; j++) {
                newGeneration[j] = merged[j];
            }

            generation = newGeneration;
            countGeneration++;
            fittest = getMaxFittest(generation);
            System.out.println("Generation: " + countGeneration + " Fittest " + fittest);

        } while (countGeneration < 1000); // The stop condition, don't implemented

        System.out.println("Generation: " + countGeneration);
        return theBestFitness(generation);
    }

    public static Chromosome[] combine(Chromosome[] a, Chromosome[] b){
        int length = a.length + b.length;
        Chromosome[] result = new Chromosome[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    private static Chromosome[] generateInitialPopulation(int n) {
        Chromosome[] population = new Chromosome[n];

        for (int i = 0; i < n; i++) {
            population[i] = new Chromosome(bits);
        }

        MergeSort.sort(population, n);

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
            if (bestFitness > generation[i].getFitness()) {
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

    private static void printGeneration(Chromosome[] population) {
        System.out.println("Attention|||");
        for (int i = 0; i < population.length; i++) {
            System.out.println("proportional: " + population[i].getPercentOfTotalFitness() + " fitness " + population[i].getFitness());
        }
        System.out.println("finish|||");
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
