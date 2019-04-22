package Algorithm;

import Model.Chromosome;
import Utils.RandomHelper;

public class Roulette {
    public Chromosome[] population;

    public Roulette(Chromosome[] population) {
        this.population = population;
    }

    // Spin the roulette
    public Chromosome spin() {
        int i = 0;
        double percent = this.population[i].getPercentOfTotalFitness();
        double random = RandomHelper.randomDoubleInInterval(0, 1);

        while (percent < random) {
            // System.out.println("percentual " + percent);
            // System.out.println("aleatorio " + random);
            i++;
            percent = this.population[i].getPercentOfTotalFitness();
        }

        if (percent != random && i != 0) {
            i -= 1;
        }

        return this.population[i];
    }
}
