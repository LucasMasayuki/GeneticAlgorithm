package Utils;

import Algorithm.Roulette;
import Model.Child;
import Model.Chromosome;

public class PopulationHelper {
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

    public static Chromosome[] generatePopulationOne(
            Chromosome[] currentPopulation,
            int n,
            double pc,
            double pm
    ) {
        Roulette roulette = new Roulette(currentPopulation);
        Chromosome[] population = new Chromosome[n];
        for (int i = 0; i < n; i+=2) {
            Chromosome parentOne = roulette.spin();
            Chromosome parentTwo = roulette.spin();
            int[] chromosome1 = parentOne.getChromosome();
            int[] chromosome2 = parentTwo.getChromosome();

            String parent1 = "";
            String parent2 = "";

            for (int j = chromosome1.length - 1; j > 0; j--) {
                parent1 += Integer.toString(chromosome1[j]);
                parent2 += Integer.toString(chromosome2[j]);
            }

            System.out.println("PopulationOne: Parent 1 " + parent1 + " Fittest1 " + parentOne.getFitness() + " Parent 2 " + parent2 + " Fittest2 " + parentTwo.getFitness());
            Child childOne = new Child(parentOne, parentTwo, pc, pm, 1, 1);
            Child childTwo = new Child(parentTwo, parentOne, pc, pm, 1, 1);
            int[] chromosome12 = childOne.getChromosome();
            int[] chromosome22 = childTwo.getChromosome();

            String child1 = "";
            String child2 = "";

            for (int j = chromosome12.length - 1; j > 0; j--) {
                child1 += Integer.toString(chromosome12[j]);
                child2 += Integer.toString(chromosome22[j]);
            }

            System.out.println("PopulationOne: Child 1 " + child1 + " Fittest1 " + childOne.getFitness() + " Child 2 " + child2 + " Fittest2 " + childTwo.getFitness());
            population[i] = childOne;
            population[i + 1] = childTwo;
        }

        MergeSort.sort(population, n);
        return population;
    }

    public static Chromosome[] generatePopulationTwo(
            Chromosome[] currentPopulation,
            int n,
            double pc,
            double pm
    ) {
        Roulette roulette = new Roulette(currentPopulation);
        Chromosome[] population = currentPopulation;
        for (int i = 0; i < n; i+=2) {
            Chromosome parentOne = theBestFitness(population);
            Chromosome parentTwo = roulette.spin();
            int[] chromosome1 = parentOne.getChromosome();
            int[] chromosome2 = parentTwo.getChromosome();

            String parent1 = "";
            String parent2 = "";

            for (int j = chromosome1.length - 1; j > 0; j--) {
                parent1 += Integer.toString(chromosome1[j]);
                parent2 += Integer.toString(chromosome2[j]);
            }

            System.out.println("PopulationTwo: Parent 1 " + parent1 + " Fittest1 " + parentOne.getFitness() + " Parent 2 " + parent2 + " Fittest2 " + parentTwo.getFitness());
            Child childOne = new Child(parentOne, parentTwo, pc, pm, 1, 1);
            Child childTwo = new Child(parentTwo, parentOne, pc, pm, 1, 1);
            int[] chromosome12 = childOne.getChromosome();
            int[] chromosome22 = childTwo.getChromosome();

            String child1 = "";
            String child2 = "";

            for (int j = chromosome12.length - 1; j > 0; j--) {
                child1 += Integer.toString(chromosome12[j]);
                child2 += Integer.toString(chromosome22[j]);
            }

            System.out.println("PopulationTwo: Child 1 " + child1 + " Fittest1 " + childOne.getFitness() + " Child 2 " + child2 + " Fittest2 " + childTwo.getFitness());
            population[i] = childOne;
            population[i + 1] = childTwo;
        }

        MergeSort.sort(population, n);
        return population;
    }

    public static Chromosome[] generatePopulationThree(
            Chromosome[] currentPopulation,
            int n,
            double pc,
            double pm
    ) {
        Roulette roulette = new Roulette(currentPopulation);
        Chromosome[] population = new Chromosome[n];
        for (int i = 0; i < n; i+=2) {
            Chromosome parentOne = roulette.spin();
            Chromosome parentTwo = roulette.spin();
            int[] chromosome1 = parentOne.getChromosome();
            int[] chromosome2 = parentTwo.getChromosome();

            String parent1 = "";
            String parent2 = "";

            for (int j = chromosome1.length - 1; j > 0; j--) {
                parent1 += Integer.toString(chromosome1[j]);
                parent2 += Integer.toString(chromosome2[j]);
            }

            System.out.println("PopulationThree: Parent 1 " + parent1 + " Fittest1 " + parentOne.getFitness() + " Parent 2 " + parent2 + " Fittest2 " + parentTwo.getFitness());
            Child childOne = new Child(parentOne, parentTwo, pc, pm, 2, 1);
            Child childTwo = new Child(parentTwo, parentOne, pc, pm, 2, 1);
            int[] chromosome12 = childOne.getChromosome();
            int[] chromosome22 = childTwo.getChromosome();

            String child1 = "";
            String child2 = "";

            for (int j = chromosome12.length - 1; j > 0; j--) {
                child1 += Integer.toString(chromosome12[j]);
                child2 += Integer.toString(chromosome22[j]);
            }

            System.out.println("PopulationThree: Child 1 " + child1 + " Fittest1 " + childOne.getFitness() + " Child 2 " + child2 + " Fittest2 " + childTwo.getFitness());
            population[i] = childOne;
            population[i + 1] = childTwo;
        }

        MergeSort.sort(population, n);
        return population;
    }

    public static Chromosome[] generatePopulationFour(
            Chromosome[] currentPopulation,
            int n,
            double pc,
            double pm
    ) {
        Roulette roulette = new Roulette(currentPopulation);
        Chromosome[] population = currentPopulation;
        for (int i = 0; i < n; i+=2) {
            Chromosome parentOne = theBestFitness(population);
            Chromosome parentTwo = roulette.spin();
            int[] chromosome1 = parentOne.getChromosome();
            int[] chromosome2 = parentTwo.getChromosome();

            String parent1 = "";
            String parent2 = "";

            for (int j = chromosome1.length - 1; j > 0; j--) {
                parent1 += Integer.toString(chromosome1[j]);
                parent2 += Integer.toString(chromosome2[j]);
            }

            System.out.println("PopulationFour: Parent 1 " + parent1 + " Fittest1 " + parentOne.getFitness() + " Parent 2 " + parent2 + " Fittest2 " + parentTwo.getFitness());
            Child childOne = new Child(parentOne, parentTwo, pc, pm, 2, 1);
            Child childTwo = new Child(parentTwo, parentOne, pc, pm, 2, 1);
            int[] chromosome12 = childOne.getChromosome();
            int[] chromosome22 = childTwo.getChromosome();

            String child1 = "";
            String child2 = "";

            for (int j = chromosome12.length - 1; j > 0; j--) {
                child1 += Integer.toString(chromosome12[j]);
                child2 += Integer.toString(chromosome22[j]);
            }

            System.out.println("PopulationFour: Child 1 " + child1 + " Fittest1 " + childOne.getFitness() + " Child 2 " + child2 + " Fittest2 " + childTwo.getFitness());
            population[i] = childOne;
            population[i + 1] = childTwo;
        }

        MergeSort.sort(population, n);
        return population;
    }

    public static Chromosome[] generatePopulationFive(
            Chromosome[] currentPopulation,
            int n,
            double pc,
            double pm
    ) {
        Roulette roulette = new Roulette(currentPopulation);
        Chromosome[] population = new Chromosome[n];
        for (int i = 0; i < n; i+=2) {
            Chromosome parentOne = roulette.spin();
            Chromosome parentTwo = roulette.spin();
            int[] chromosome1 = parentOne.getChromosome();
            int[] chromosome2 = parentTwo.getChromosome();

            String parent1 = "";
            String parent2 = "";

            for (int j = chromosome1.length - 1; j > 0; j--) {
                parent1 += Integer.toString(chromosome1[j]);
                parent2 += Integer.toString(chromosome2[j]);
            }

            System.out.println("PopulationFive: Parent 1 " + parent1 + " Fittest1 " + parentOne.getFitness() + " Parent 2 " + parent2 + " Fittest2 " + parentTwo.getFitness());
            Child childOne = new Child(parentOne, parentTwo, pc, pm, 3, 1);
            Child childTwo = childOne.generateBrotherUniform(pc, pm,  1);
            int[] chromosome12 = childOne.getChromosome();
            int[] chromosome22 = childTwo.getChromosome();

            String child1 = "";
            String child2 = "";

            for (int j = chromosome12.length - 1; j > 0; j--) {
                child1 += Integer.toString(chromosome12[j]);
                child2 += Integer.toString(chromosome22[j]);
            }

            System.out.println("PopulationFive: Child 1 " + child1 + " Fittest1 " + childOne.getFitness() + " Child 2 " + child2 + " Fittest2 " + childTwo.getFitness());
            population[i] = childOne;
            population[i + 1] = childTwo;
        }

        MergeSort.sort(population, n);
        return population;
    }


    public static Chromosome[] generatePopulationSix(
            Chromosome[] currentPopulation,
            int n,
            double pc,
            double pm
    ) {
        Roulette roulette = new Roulette(currentPopulation);
        Chromosome[] population = currentPopulation;
        for (int i = 0; i < n; i+=2) {
            Chromosome parentOne = theBestFitness(population);
            Chromosome parentTwo = roulette.spin();
            int[] chromosome1 = parentOne.getChromosome();
            int[] chromosome2 = parentTwo.getChromosome();

            String parent1 = "";
            String parent2 = "";

            for (int j = chromosome1.length - 1; j > 0; j--) {
                parent1 += Integer.toString(chromosome1[j]);
                parent2 += Integer.toString(chromosome2[j]);
            }

            System.out.println("PopulationSix: Parent 1 " + parent1 + " Fittest1 " + parentOne.getFitness() + " Parent 2 " + parent2 + " Fittest2 " + parentTwo.getFitness());
            Child childOne = new Child(parentOne, parentTwo, pc, pm, 3, 1);
            Child childTwo = childOne.generateBrotherUniform(pc, pm,  1);
            int[] chromosome12 = childOne.getChromosome();
            int[] chromosome22 = childTwo.getChromosome();

            String child1 = "";
            String child2 = "";

            for (int j = chromosome12.length - 1; j > 0; j--) {
                child1 += Integer.toString(chromosome12[j]);
                child2 += Integer.toString(chromosome22[j]);
            }

            System.out.println("PopulationSix: Child 1 " + child1 + " Fittest1 " + childOne.getFitness() + " Child 2 " + child2 + " Fittest2 " + childTwo.getFitness());
            population[i] = childOne;
            population[i + 1] = childTwo;
        }

        MergeSort.sort(population, n);
        return population;
    }
}
