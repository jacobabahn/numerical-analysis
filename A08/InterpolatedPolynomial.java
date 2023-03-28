public interface InterpolatedPolynomial {
    /**
     * Calculates the coefficients using the improved divided difference method and saves the coefficients to double[] a.
     * 
     * @param x the array of x values.
     * @param y the array of y values.
     * @param a 
     * @return the resulting array of coefficients for the polynomial, if possible;
     *         otherwise, returns null.
     */
    double[] coef(double[] x, double[] y);

    /**
     * Evaluates at t the polynomial defined by the array of coefficients. 
     * You should also be able to write a string representation of the polynomial.
     * This is not implemented in the pseudocode. You will need to figure
     * out how to do this.
     * 
     * @param x the array of x values.
     * @param a the array of coefficients for the polynomial.
     * @param t the parameter for the polynomial.
     * @return the result of f(t), if possible; otherwise, returns
     *         Double.NaN.
     */
    double eval(double[] x, double[] a, double t);
    
    /**
     * Produces a human-readable string representation of an interpolated polynomial defined by 
     * an array of coefficients, if possible. If a is null, the method returns
     * "No Solution".
     *  
     * This is not implemented in pseudocode provided by the textbook. You will need to figure 
     * out how to do this.
     * 
     * @param x the array of x values.
     * @param a the array of coefficients for the polynomial.
     * @return the result of f(t).
     */
    public abstract String toString(double[] x, double[] a);
}
