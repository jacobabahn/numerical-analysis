import com.sun.org.apache.xpath.internal.functions.Function;

public class RootCalc implements RootCalculator {
    @Override
    public double bisectionMethod(double a, double b, Function f, double epsilon, int nMax) {
        double fa = f.eval(a);
        double fb = f.eval(b);

        if (fa * fb > 0) {
            System.out.println("function has same signs at a and b");
            return 0;
        }

        double error = b - a;
        
        for (int n = 0; n < nMax; n++) {
            error /= 2;
            double c = a + error;
            double fc = f.eval(c);

            if (Math.abs(error) < epsilon) {
                System.out.println("convergence");
                return c;
            }

            if (fa * fc < 0) {
                b = c;
                fb = fc;
            } else {
                a = c;
                fa = fc;
            }
        }

        System.out.println("max iterations reached");
        return 0;
    }

    @Override
    public double newtonsMethod(double x, Function f, Function fPrime, double epsilon, int nMax) {
        double fx = f.eval(x);

        System.out.println("0, x: " + x + " fx: " + fx);

        for (int n = 0; n < nMax; n++) {
            double fp = fPrime.eval(x);

            if (Math.abs(fp) < epsilon) {
                System.out.println("small derivative");
                return x;
            }

            double d = fx / fp;
            x -= d;
            fx = f.eval(x);

            System.out.println(n + 1 + ", x: " + x + " fx: " + fx);

            if (Math.abs(d) < epsilon) {
                System.out.println("convergence");
                return x;
            }
        }

        System.out.println("max iterations reached");
        return 0;
    }
}
