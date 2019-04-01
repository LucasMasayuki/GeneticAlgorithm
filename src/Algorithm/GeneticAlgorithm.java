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

        double fittest = getMinFittest(initialPopulation);
        System.out.println("Generation: " + 0 + " Fittest " + fittest);
        int countGeneration = 0;

        do {
            fillProportionalPercentOfTotalFitness(generation);
            Chromosome[] newGeneration = new Chromosome[n];

            Chromosome[] populationOne = PopulationHelper.generatePopulation(generation.clone(), n, pc, pm, 1, 1, false, 1);
            Chromosome[] populationTwo = PopulationHelper.generatePopulation(generation.clone(), n, pc, pm, 1, 2, false, 2);
            Chromosome[] populationThree = PopulationHelper.generatePopulation(generation.clone(), n, pc, pm, 1, 1, true, 3);
            Chromosome[] populationFour = PopulationHelper.generatePopulation(generation.clone(), n, pc, pm, 1, 2, true, 4);
            Chromosome[] populationFive = PopulationHelper.generatePopulation(generation.clone(), n, pc, pm, 2, 1, false, 5);
            Chromosome[] populationSix = PopulationHelper.generatePopulation(generation.clone(), n, pc, pm, 2, 2, false, 6);
            Chromosome[] populationSeven = PopulationHelper.generatePopulation(generation.clone(), n, pc, pm, 2, 1, true, 7);
            Chromosome[] populationEight = PopulationHelper.generatePopulation(generation.clone(), n, pc, pm, 2, 2, true, 8);
            Chromosome[] populationNone = PopulationHelper.generatePopulation(generation.clone(), n, pc, pm, 3, 1, false, 9);
            Chromosome[] populationTen = PopulationHelper.generatePopulation(generation.clone(), n, pc, pm, 3, 2, false, 10);
            Chromosome[] populationEleven = PopulationHelper.generatePopulation(generation.clone(), n, pc, pm, 3, 1, true, 11);
            Chromosome[] populationTwenty = PopulationHelper.generatePopulation(generation.clone(), n, pc, pm, 3, 2, true, 12);

            Chromosome[] merged = combine(populationOne, populationTwo);
            merged = combine(merged, populationThree);
            merged = combine(merged, populationFour);
            merged = combine(merged, populationFive);
            merged = combine(merged, populationSix);
            merged = combine(merged, populationSeven);
            merged = combine(merged, populationEight);
            merged = combine(merged, populationNone);
            merged = combine(merged, populationTen);
            merged = combine(merged, populationEleven);
            merged = combine(merged, populationTwenty);

            MergeSort.sort(merged, 120);

            for (int j = 0; j < newGeneration.length; j++) {
                newGeneration[j] = merged[j];
            }

            generation = newGeneration;
            printGeneration(generation);
//            printGeneration(generation);
            countGeneration++;

        } while (countGeneration != 100); // The stop condition, don't implemented

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

    private static double getMinFittest(Chromosome[] generation) {
        double minFit = Double.MAX_VALUE;
        for (int i = 0; i < generation.length; i++) {
            if (minFit < generation[i].getFitness()) {
                minFit = generation[i].getFitness();
            }
        }
        return minFit;
    }

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
