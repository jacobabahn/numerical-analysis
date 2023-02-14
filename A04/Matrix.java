public interface Matrix {
    /**
     * Calculates A + B, where A and B are matrices. 
     * Returns null if the two matrices are not 
     * addition compatible.
     * This method does not modify the contents of
     * the matrices passed to it.
     * @param A the left matrix
     * @param B the right matrix
     * @return A + B, if possible; otherwise, null
     */
    double[][] add(double[][] A, double[][] B);

    /** 
     * Calculates A - B, where A and B are matrices. 
     * Returns null if the two matrices are not 
     * subtraction compatible.
     * This method does not modify the contents of
     * the matrices passed to it.
     * @param A the left matrix
     * @param B the right matrix
     * @return A - B, if possible; otherwise, null
     */
    double[][] subtract(double[][] A, double[][] B);

    /**
     * Calculates AB, where a and b are matrices. 
     * Returns null if the two matrices are not 
     * multiplication compatible.
     * This method does not modify the contents of
     * the matrices passed to it.
     * @param A the left matrix
     * @param B the right matrix
     * @return AB, if possible; otherwise, null
     */
    double[][] multiply(double[][] A, double[][] B);

    /**
     * Calculates the scalar product cA.
     * This method does not modify the contents of
     * matrix A.
     * @param c the scalar
     * @param A the matrix
     * @return cA
     */
    double[][] multiply(double c, double[][] A);
    
    /**
     * Calculates the scalar product Ac.
     * This method does not modify the contents of
     * matrix A.
     * @param A the matrix
     * @param c the scalar
     * @return Ac
     */
    double[][] multiply(double[][] A, double c);

    /**
     * Performs Gaussian Elimination with Scaled Partial Pivoting
     * Gauss does the elimination on A and updates l and A.
     * It does not affect the b vector.
     * @param A matrix of coefficients
     * @param l array of indices
     */
    void gauss(double[][] A, int[] l);

    /**
     * Completes the forward elimination by  
     * applying it to the b column.
     * @param A matrix of coefficients
     * @param l array of indices
     * @param b array of right hand side values
     */
    void gaussModifiedForwardEliminationRHS(double[][] a, int[] l, double[] b);

    /**
     * Applies the forward elimination to the b column vector and returns 
     * this as the solution. This is the last part of the Solve procedure 
     * pseudocode in the book.
     * @param A matrix of coefficients
     * @param l array of indices
     * @param b array of right hand side values
     * @return the system solution
     */
    double[] solve(double[][] A, int[] l, double[] b);

    /**
     * Calculates the inverse of matrix A using using the methods
     * you developed for Gaussian elimination.
     * 
     * 1. Do the Gaussian elimination on matrix A.
     * 2. Create an identity matrix with the same dimensions as A
     * 3. Do the elimination on the right hand side using each column 
     *    of the identity matrix as the b column in elimination.
     * 4. Solve for that column and replace the column with the solution 
     *    vector contents.
     * 
     * @param A matrix of double-precision floating-point numbers.
     * @return inverse of matrix A.
     */
    double[][] inverse(double[][] A);
    
}
