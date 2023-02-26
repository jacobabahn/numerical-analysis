import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

// Conveniently use shorter Math method names
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.pow;
import static java.lang.Math.exp;

public class TestRoots {
    // Some named functions to test
    private static double f(double x) {
        return pow(x, 3) - 2 * sin(x);
    }

    private static double fPrime(double x) {
        return 3 * pow(x, 2) - 2 * cos(x);
    }

    //---------------------------------------------------------------------
    // Using named methods for functions
    
    @Test
    public void bisectionTestTrigFunctions() {
        int nmax = 50;
        double epsilon = 0.0000005;
        RootCalculator roots = new RootCalc();
        double root = roots.bisectionMethod(0.5, 2, TestRoots::f, epsilon, nmax);
        assertEquals(1.2361839, root, epsilon);
    }
    
    @Test
    public void bisectionTestNoRootInRange() {
        int nmax = 50;
        double epsilon = 0.0000005;
        RootCalculator roots = new RootCalc();
        double root = roots.bisectionMethod(2.0, 3.0, TestRoots::f, epsilon, nmax);
        assertEquals(RootCalculator.NO_ROOT, root);
    }
    
    @Test
    public void newtonsTestTrigFunctions() {
        int nmax = 50;
        double epsilon = 0.0000005;
        RootCalculator roots = new RootCalc();
        double root = roots.newtonsMethod(1.0, TestRoots::f, TestRoots::fPrime, epsilon, nmax);
        assertEquals(1.2361839, root, epsilon);
    }
    
    
    //---------------------------------------------------------------------
    // Using lambda expressions for functions
    
    @Test
    public void newtonsTestlambda() {
        int nmax = 50;
        double epsilon = 0.0000005;
        RootCalculator roots = new RootCalc();
        // Translate these to Java lambda expressions:  f(x) = 3x^2 + 2x - 5, f'(x) = 6x + 2. 
        double root = roots.newtonsMethod(1.0, x -> 3*x*x + 2*x - 5, x -> 6*x + 2, epsilon, nmax);
        assertEquals(1.0, root, epsilon);
    }
 
}
