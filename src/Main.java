import Algorithm.GeneticAlgorithm;
import Model.Chromosome;

import java.util.Scanner;

public class Main {
    public static final int bits = 10;

    private static Chromosome[] generateInitialPopulation(int n) {
        Chromosome[] population = new Chromosome[n];
        for (int i = 0; i < n; i++) {
            population[i] = new Chromosome(bits);
        }

        return population;
    }

    private static double getMaxFittest(Chromosome[] initialPopulation) {
        double maxFit = Double.MIN_VALUE;
        for (int i = 0; i < initialPopulation.length; i++) {
            if (maxFit <= initialPopulation[i].getFitness()) {
                maxFit = initialPopulation[i].getFitness();
            }
        }
        return maxFit;
    }

    public static void main(String[] args) {
        double crossingProbability = 0.6;
        double mutationProbability = 0.04;
        int n = 10;

        Chromosome[] initialPopulation = generateInitialPopulation(n);
        System.out.println("Generation: " + 1 + " Fittest " + getMaxFittest(initialPopulation));

        // Initialize genetic algorithm and get the best fitness
        Chromosome best = GeneticAlgorithm.initialize(
            initialPopulation,
            n,
            crossingProbability,
            mutationProbability
        );

        System.out.print("Fittest " + best.getFitness());
    }
}
