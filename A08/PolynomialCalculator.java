import java.util.Arrays;

public class PolynomialCalculator implements InterpolatedPolynomial {

    @Override
    public double[] coef(double[] x, double[] y) {
        int n = x.length;

        double[] a = y.clone();

        for (int j = 1; j < n; j++) {
            for (int i = n - 1; i > j; i--) {
                a[i] = (a[i] - a[i - 1]) / (x[i] - x[i - j]);
            }
        }
        return a;
    }
    

    @Override
    public double eval(double[] x, double[] a, double t) {
        int n = x.length;
        double temp = a[a.length - 1];
        for (int i = n - 1; i > 0; i--) {
            temp = temp * (t - x[i]) + a[i];
        }
        return temp;
    }

    public String toString(double[] x, double[] a) {
        if (a == null) {
           return "No Solution";
        }

        System.out.println("x = " + Arrays.toString(x));
        System.out.println("a = " + Arrays.toString(a));

        return "Polynomial";

    }

}
