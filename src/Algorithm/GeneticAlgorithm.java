package Algorithm;

import Model.Chromosome;
import Utils.FileHelper;
import Utils.MergeSort;
import Utils.PopulationHelper;
import Algorithm.Roulette;

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

        saveInitialGeneration(initialPopulation);

        Chromosome[] generation = initialPopulation;

        FileHelper avgFile = new FileHelper("Media");
        FileHelper bestFile = new FileHelper("Melhores");
        double best = theBestFitness(generation).getFitness();
        double avg = theAvgFitness(generation);
        String bestText = Double.toString(best).replace('.', ',') + " \n";
        String avgText =  Double.toString(avg).replace('.', ',') + " \n";

        double fittest = getMaxFittest(initialPopulation);
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
//            fillProportionalPercentOfTotalFitness(merged);
//            printGeneration(merged);

//            for (int j = 0; j < n; j++) {
//                newGeneration[j] = merged[j];
//            }

            int bests = n / 2;
            Chromosome[] theRest = new Chromosome[merged.length - bests - 1];

            int k = 0;
            for (int j = 0; j < merged.length; j++) {
                if (j <= bests) {
                    newGeneration[j] = merged[j];
                } else {
                    theRest[k] = merged[j];
                    k++;
                }
            }

            //fillProportionalPercentOfTotalFitness(theRest);

            Roulette roulette = new Roulette(theRest);
            // printGeneration(theRest);

            for (int j = bests + 1; j < newGeneration.length; j++) {
                newGeneration[j] = roulette.spin();
            }

            generation = newGeneration;
            best = theBestFitness(generation).getFitness();
            avg = theAvgFitness(generation);
            bestText += Double.toString(best).replace('.', ',') + " \n";
            avgText +=  Double.toString(avg).replace('.', ',') + " \n";
            // printGeneration(generation);
            countGeneration++;

        } while (countGeneration != 5000);

        bestFile.write(bestText);
        avgFile.write(avgText);
        avgFile.save();
        bestFile.save();

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
            if (minFit > generation[i].getFitness()) {
                minFit = generation[i].getFitness();
            }
        }
        return minFit;
    }

    private static double getMaxFittest(Chromosome[] generation) {
        double maxFit = 0;
        for (int i = 0; i < generation.length; i++) {
            if (maxFit < generation[i].getFitness()) {
                maxFit = generation[i].getFitness();
            }
        }
        return maxFit;
    }

//    private static Chromosome theBestFitness(Chromosome[] generation) {
//        double bestFitness = Double.MAX_VALUE;
//        int bestIdx = 0;
//
//        for (int i = 0; i < generation.length; i++) {
//            if (bestFitness > generation[i].getFitness()) {
//                bestFitness = generation[i].getFitness();
//                bestIdx = i;
//            }
//        }
//
//        return generation[bestIdx];
//    }

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

    private static double theAvgFitness(Chromosome[] generation) {
        double total = generation.length;
        double sum = 0;

        for (int i = 0; i < total; i++) {
            sum += generation[i].getFitness();
        }

        if (sum == 0) {
            return sum;
        }

        return sum / total;
    }

    private static void fillProportionalPercentOfTotalFitness(Chromosome[] population) {
        double summationFitness = summationFitness(population);
        int sizeOfPopulation = population.length;
        double total = 0;

        for (int i = 0; i < sizeOfPopulation; i++) {
            //percent = (1 / population[i].getFitness() + Math.pow(10, -9)) / summationFitness;
            double percent = population[i].getFitness() / summationFitness;
            total += percent;
            population[i].setPercentOfTotalFitness(total);
        }
    }

    private static void printGeneration(Chromosome[] population) {
        System.out.println("Attention|||");
        for (int i = 0; i < population.length; i++) {
            System.out.println("proportional: " + population[i].getPercentOfTotalFitness() + " fitness " + population[i].getFitness());
        }
        System.out.println("finish|||");
    }

    private static void saveInitialGeneration(Chromosome[] initialPopulation) {
        FileHelper fileHelper = new FileHelper("initial_population");
        String text = "";
        for (int i = 0; i < initialPopulation.length; i++) {
            int[] chromosome = initialPopulation[i].getChromosome();
            String stringChromosome = "";
            for (int j = 0; j < chromosome.length; j++) {
                stringChromosome += chromosome[j];
            }
            text += "Cromossomo: " + stringChromosome + " fitness: " + initialPopulation[i].getFitness() + " \n";
        }

        fileHelper.write(text);
        fileHelper.save();
    }

    private static double summationFitness(Chromosome[] population) {
        int sizeOfPopulation = population.length;
        double totalFitness = 0;

        for (int i = 0; i < sizeOfPopulation; i++) {
            double fitness = population[i].getFitness();
            //totalFitness +=  (1 / fitness +  Math.pow(10, -9));
            totalFitness +=  fitness;
        }

        return totalFitness;
    }
}
