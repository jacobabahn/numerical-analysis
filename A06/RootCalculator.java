/**
 * Calculate the roots (zeros) of functions using
 * the bisection method or Nweton's method.
 */
public interface RootCalculator {
    /**
     * The value the root methods return when they cannot compute a root.
     */
    public static final double NO_ROOT = Double.NaN;
    
    /**
     * Interface for the Bisection Method
     * @param a left hand endpoint of the starting interval.
     * @param b right hand endpoint of the starting interval.
     * @param f this is the function for which you want to find the root.
     * @param epsilon the accuracy desired.
     * @param nMax the maximum number of iterations to attempt.
     */
    double bisectionMethod(double a, double b, Function f, double epsilon, int nMax);

    /**
     * Interface for Newton's Method
     * @param x the initial condition.
     * @param f the function for which you want to find the root.
     * @param fPrime the derivative of the function for which you want to find the root.
     * @param epsilon the accuracy desired.
     * @param nMax the maximum number of iterations to attempt.
     */
    double newtonsMethod(double x, Function f, Function fPrime, double epsilon, int nMax);
}
