package Model;

import Algorithm.Function;
import Utils.NumberHelper;
import Utils.RandomHelper;

public class Chromosome {
    private int[] chromosome;
    private double fitness;
    private double percentOfTotalFitness = 0;

    public Chromosome() {
    }

    public Chromosome(int size) {
        this.chromosome = new int[size];

        // Initialize chromosome
        for (int i = 0; i < chromosome.length; i++) {
            this.chromosome[i] = RandomHelper.randomIntegerInInterval(2) == 1 ? 1 : 0;
        }

        this.fitness = calculateFitness();
    }

    public int[] getChromosome() {
        return chromosome;
    }

    public void setChromosome(int[] chromosome) {
        this.chromosome = chromosome;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getPercentOfTotalFitness() {
        return percentOfTotalFitness;
    }

    public void setPercentOfTotalFitness(double percentOfTotalFitness) {
        this.percentOfTotalFitness = percentOfTotalFitness;
    }

    public double calculateFitness() {
        int decimal = NumberHelper.binaryToDecimal(this.chromosome);
        int bits = this.chromosome.length;
        double realX = NumberHelper.realX(decimal, -1, 1, bits);
        System.out.println("decimal " + " real " + realX);

        return Function.fitness(realX);
    }
}
