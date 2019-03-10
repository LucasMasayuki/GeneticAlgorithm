package Algorithm;

public class Function {
    // Return the fitness
    public static double fitness(double x) {
        return function(x);
    }

    // The function, don't implemented
    private static double function(double x) {
        return x * Math.sin(10 * Math.PI * x) + 1;
    }
}
