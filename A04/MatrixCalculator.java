public class MatrixCalculator implements Matrix {
    @Override
    public double[][] add(double[][] A, double[][] B) {
        // Check if the two matrices are addition compatible
        if (A.length != B.length || A[0].length != B[0].length) {
            return null;
        }

        var addedMatrix = new double[A.length][A[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                addedMatrix[i][j] = A[i][j] + B[i][j];
            }
        }

        return addedMatrix;
    }


    @Override
    public double[][] subtract(double[][] A, double[][] B) {
        // check if the two matrices are subtraction compatible
        if (A.length != B.length || A[0].length != B[0].length) {
            return null;
        }

        var subtractedMatrix = new double[A.length][A[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                subtractedMatrix[i][j] = A[i][j] - B[i][j];
            }
        }

        return subtractedMatrix;

    }


    @Override
    public double[][] multiply(double[][] A, double[][] B) {
        // check if the two matrices are multiplication compatible
        if (A[0].length != B.length) {
            return null;
        }

        var multipliedMatrix = new double[A.length][B[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    multipliedMatrix[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return multipliedMatrix;
    }


    @Override
    public double[][] multiply(double c, double[][] A) {
        var multipliedMatrix = new double[A.length][A[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                multipliedMatrix[i][j] = c * A[i][j];
            }
        }

        return multipliedMatrix;
    }
    

    @Override
    public double[][] multiply(double[][] A, double c) {
        return multiply(c, A);
    }


    @Override
    public void gauss(double[][] A, int[] l) {
        int n = A.length;
        double[] s = new double[n];

        for (int i = 0; i < n; i++) {
            l[i] = i;
            double smax = 0;

            for (int j = 0; j < A[0].length; j++) {
                smax = Math.max(smax, Math.abs(A[i][j]));
            }
            s[i] = smax;
        }

        for (int k = 0; k < n - 1; k++) {
            double rmax = 0;

            for (int i = k; i < n; i++) {
                double r = Math.abs(A[l[i]][k] / s[l[i]]);

                if (r > rmax) {
                    rmax = r;
                    int temp = l[i];
                    l[i] = l[k];
                    l[k] = temp;
                }
            }


            for (int i = k + 1; i < n; i++) {
                double xmult = A[l[i]][k] / A[l[k]][k];
                A[l[i]][k] = xmult;

                for (int j = k + 1; j < A[0].length; j++) {
                    A[l[i]][j] = A[l[i]][j] - xmult * A[l[k]][j];
                }
            }
        }
    }


    @Override
    public void gaussModifiedForwardEliminationRHS(double[][] a, int[] l, double[] b) {
        int n = a.length;
        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                b[l[i]] = b[l[i]] - a[l[i]][k] * b[l[k]];
            }
        }
    }


    @Override
    public double[] solve(double[][] A, int[] l, double[] b) {
        int n = A.length;
        double[] x = new double[n];

        gaussModifiedForwardEliminationRHS(A, l, x);

        x[n - 1] = b[l[n - 1]] / A[l[n - 1]][n - 1];

        for (int i = x.length - 2; i >= 0; i--) {
            double sum = b[l[i]];
            for (int j = i + 1; j < n; j++) {
                sum = sum - A[l[i]][j] * x[j];
            }
            x[i] = sum / A[l[i]][i];
        }

        return x;
    }

 
    @Override
    public double[][] inverse(double[][] A) {

        //1. Do the Gaussian elimination on matrix A.
        int[] l = new int[A.length];
        gauss(A, l);

        //2. Create an identity matrix with the same dimensions as A
        double[][] identity = new double[A.length][A[0].length];
        for (int i = 0; i < identity.length; i++) {
            for (int j = 0; j < identity[i].length; j++) {
                if (i == j) {
                    identity[i][j] = 1;
                } else {
                    identity[i][j] = 0;
                }
            }
        }

        //3. Do the elimination on the right hand side using each column
        //   of the identity matrix as the b column in elimination.
        for (int i = 0; i < identity.length; i++) {
            gaussModifiedForwardEliminationRHS(A, l, identity[i]);
        }

        //4. Solve for that column and replace the column with the solution
        //   vector contents.
        for (int i = 0; i < identity.length; i++) {
            identity[i] = solve(A, l, identity[i]);
        }
        
        transpose(identity);
        return identity;
    }

    // tranpsose the matrix
    public void transpose(double[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A[i].length; j++) {
                double temp = A[i][j];
                A[i][j] = A[j][i];
                A[j][i] = temp;
            }
        }
    }
}
