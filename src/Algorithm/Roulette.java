package Algorithm;

import Model.Chromosome;
import Utils.RandomHelper;

public class Roulette {
    public Chromosome[] population;

    Roulette(Chromosome[] population) {
        this.population = population;
    }

    // Spin the roulette
    public Chromosome spin() {
        int i = 0;
        double percent = this.population[i].getPercentOfTotalFitness();
        double random = RandomHelper.randomDoubleInInterval(0, 1);

        while (percent < random) {
            i++;
            percent = this.population[i].getPercentOfTotalFitness();
        }

        Chromosome drawn = this.population[i];

        return drawn;
    }
}
