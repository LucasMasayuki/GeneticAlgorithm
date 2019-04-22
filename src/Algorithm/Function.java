package Algorithm;

public class Function {
    // Return the fitness
    public static double fitness(double x, double y) {
        // return function(x, y);
        double f = function(x, y);
        System.out.println(f);
        return 1 / (f +  Math.pow(10, -9));
    }

    private static double function(double x, double y) {
        double result = 20 + Math.pow(x, 2) + Math.pow(y, 2) - 10*(Math.cos(2*Math.PI*x) + Math.cos(2*Math.PI*y));
        if (result <= 0) System.out.println("passa" + result);
        return result;
    }
}
