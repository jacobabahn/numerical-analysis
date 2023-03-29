import java.util.Arrays;

public class PolynomialCalculator implements InterpolatedPolynomial {

    @Override
    public double[] coef(double[] x, double[] y) {
        int n = x.length;

        double[] a = y.clone();

        for (int j = 1; j < n; j++) {
            for (int i = n - 1; i >= j; i--) {
                a[i] = (a[i] - a[i - 1]) / (x[i] - x[i - j]);
            }
        }
        return a;
    }
    

    @Override
    public double eval(double[] x, double[] a, double t) {
        int n = x.length;
        double temp = a[a.length - 1];
        for (int i = n - 2; i >= 0; i--) {
            temp = temp * (t - x[i]) + a[i];
        }
        return temp;
    }

    public String toString(double[] x, double[] a) {
        if (a == null) {
           return "No Solution";
        }

        String result = "";
        for (int i = 0; i < a.length; i++) {
            result += Math.abs(a[i]) + " ";
        
            for (int j = 0; j < i; j++) {
                if (x[j] < 0) {
                    result += "(x + " + Math.abs(x[j]) + ") ";
                } else {
                    result += "(x - " + x[j] + ") ";
                }
            }

            if (i != a.length - 1) {
                if (a[i + 1] >= 0) {
                    result += "+ ";
                } else {
                    result += "- ";
                }
            }
        }
        
        return result;
    }

}
