import Algorithm.GeneticAlgorithm;
import Model.Chromosome;

public class Main {
    public static final int bits = 5;

    private static Chromosome[] generateInitialPopulation(int n) {
        Chromosome[] population = new Chromosome[n];
        for (int i = 0; i < n; i++) {
            population[i] = new Chromosome(bits);
        }

        return population;
    }

    public static void main(String[] args) {
        double crossingProbability = 1;
        double mutationProbability = 0.04;
        int n = 10;

        Chromosome[] initialPopulation = generateInitialPopulation(n);

        // Initialize genetic algorithm and get the best fitness
        Chromosome best = GeneticAlgorithm.initialize(
            initialPopulation,
            n,
            crossingProbability,
            mutationProbability
        );
    }
}
