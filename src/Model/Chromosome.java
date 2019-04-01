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
            this.chromosome[i] = RandomHelper.randomDoubleInInterval(0, 1) > 0.5 ? 1 : 0;
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

    private int[] getBinaryOfChromosome(int index) {
        int bits = this.chromosome.length / 2;
        int[] binary = new int[bits];

        for (int i = 0; i < bits; i++) {
            binary[i] = this.chromosome[index];
            index++;
        }

        return binary;
    }

    public double calculateFitness() {
        int bits = this.chromosome.length / 2;
        int[] binaryX = getBinaryOfChromosome(0);
        int[] binaryY = getBinaryOfChromosome(bits);

        int x = NumberHelper.binaryToDecimal(binaryX);
        int y = NumberHelper.binaryToDecimal(binaryY);
        //System.out.println("decimal " + " x " + x + " y " + y);

        double realX = NumberHelper.realX(x, -5, 5, bits);
        double realY = NumberHelper.realX(y, -5, 5, bits);

        //System.out.println("decimal " + " real x " + realX + " real y " + realY);

        return Function.fitness(realX, realY);
    }
}
