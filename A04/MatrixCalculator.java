public class MatrixCalculator implements Matrix {
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
    public double[][] add(double[][] A, double[][] B) {
        // Check if the two matrices are addition compatible
        if (A.length != B.length || A[0].length != B[0].length) {
            return null;
        }

        addedMatrix = new double[A.length][A[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                addedMatrix[i][j] = A[i][j] + B[i][j];
            }
        }

        return addedMatrix;
    }

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
    public double[][] subtract(double[][] A, double[][] B) {
        // check if the two matrices are subtraction compatible
        if (A.length != B.length || A[0].length != B[0].length) {
            return null;
        }

        subtractedMatrix = new double[A.length][A[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                subtractedMatrix[i][j] = A[i][j] - B[i][j];
            }
        }

        return subtractedMatrix;

    }

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
    public double[][] multiply(double[][] A, double[][] B) {
        // check if the two matrices are multiplication compatible
        if (A[0].length != B.length) {
            return null;
        }

        multipliedMatrix = new double[A.length][B[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    multipliedMatrix[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return multipliedMatrix;
    }

    /**
     * Calculates the scalar product cA.
     * This method does not modify the contents of
     * matrix A.
     * @param c the scalar
     * @param A the matrix
     * @return cA
     */
    public double[][] multiply(double c, double[][] A) {
        multipliedMatrix = new double[A.length][A[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                multipliedMatrix[i][j] = c * A[i][j];
            }
        }

        return multipliedMatrix;
    }
    
    /**
     * Calculates the scalar product Ac.
     * This method does not modify the contents of
     * matrix A.
     * @param A the matrix
     * @param c the scalar
     * @return Ac
     */
    public double[][] multiply(double[][] A, double c) {
        return multiply(c, A);
    }

    /**
     * Performs Gaussian Elimination with Scaled Partial Pivoting
     * Gauss does the elimination on A and updates l and A.
     * It does not affect the b vector.
     * @param A matrix of coefficients
     * @param l array of indices
     */
    public void gauss(double[][] A, int[] l) {}

    /**
     * Completes the forward elimination by  
     * applying it to the b column.
     * @param A matrix of coefficients
     * @param l array of indices
     * @param b array of right hand side values
     */
    public void gaussModifiedForwardEliminationRHS(double[][] a, int[] l, double[] b) {}

    /**
     * Applies the forward elimination to the b column vector and returns 
     * this as the solution. This is the last part of the Solve procedure 
     * pseudocode in the book.
     * @param A matrix of coefficients
     * @param l array of indices
     * @param b array of right hand side values
     * @return the system solution
     */
    public double[] solve(double[][] A, int[] l, double[] b) {}

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
    public double[][] inverse(double[][] A) {}
    
}
