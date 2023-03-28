public class PolynomialCalculator implements InterpolatedPolynomial {

    @Override
    public double[] coef(double[] x, double[] y) {
        // Replace the following two statements with your code:
        double[] a = { 1.0, 2.0 };
        return a;
    }
    

    @Override
    public double eval(double[] x, double[] a, double t) {
        // Replace the following statement with your code:
        return 5.0;
    }

    public String toString(double[] x, double[] a) {
        // Replace the following statement with your code:
        return "POLYNOMIAL";
    }

}
