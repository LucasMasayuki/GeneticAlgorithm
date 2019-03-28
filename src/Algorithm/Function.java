package Algorithm;

public class Function {
    // Return the fitness
    public static double fitness(double x, double y) {
        return function(x, y);
    }

    private static double function(double x, double y) {
        // return x * Math.sin(10 * Math.PI * x) + 1;
        return 20 + Math.pow(x, 2) + Math.pow(y, 2) - 10*(Math.cos(2*Math.PI*x) + Math.cos(2*Math.PI*y));
    }
}
