package Algorithm;

public class Function {
    private static double constant;
    // Return the fitness
    public static double fitness(int x) {
        return constant + function(x);
    }

    // The function, don't implemented
    private static double function(int x) {
        return x;
    }
}
