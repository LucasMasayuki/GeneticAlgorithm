import Algorithm.GeneticAlgorithm;
import Model.Chromosome;

public class Main {
    public static void main(String[] args) {
        double crossingProbability = 0.9;
        double mutationProbability = 0.2;
        int n = 10;
        int bits = 20;

        // Initialize genetic algorithm and get the best fitness
        Chromosome best = GeneticAlgorithm.initialize(
            bits,
            n,
            crossingProbability,
            mutationProbability
        );

        System.out.print("Best Fittest " + best.getFitness());
    }
}
