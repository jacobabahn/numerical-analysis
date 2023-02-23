import com.sun.org.apache.xpath.internal.functions.Function;

public class RootCalc implements RootCalculator {
    @Override
    public double bisectionMethod(double a, double b, Function f, double epsilon, int nMax) {}

    @Override
    public double newtonsMethod(double x, Function f, Function fPrime, double epsilon, int nMax) {}
}
