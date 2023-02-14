import static  org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestMatrixCalculator {
    final Matrix matrixObject = new MatrixCalculator();

    // Compare 2-D double arrays.
    // Element x within an array is considered equal to y in the corresponding location
    // in the other array if |x - y| < tolerance. 
    void assert2DArrayEquals(double[][] expected, double[][] actual, double tolerance) {
        if (expected == null) {
            assertNull(actual);
        } else if (actual == null) {
            assertNull(expected);
        } else {
            assertEquals(expected.length, actual.length);
            for (int row = 0; row < expected.length; row++) {
                assertArrayEquals(expected[row], actual[row], tolerance);
            }
        }
    }
    
    @Test
    void testAddition() {
        double[][] A = { 
            { 5, 6, 1 }, 
            { 7, 8, 2 }, 
            { 0, 0, 0 } 
        };
        double[][] B = { 
            { 1, 2, -1     }, 
            { 3, 4,  0.001 }, 
            { 0, 0,  0     } 
        };
        double[][] sum = matrixObject.add(A, B);
        double[][] expected = { {  6,  8, 0 }, 
                                { 10, 12, 2.001 }, 
                                {  0,  0, 0 } };
        assert2DArrayEquals(expected, sum, 0.0001);
    }

    
    @Test
    void testMatrixScalarMultiplicationFromLeft() {
        double c = 3.0;
        double[][] A = { { 5, 6, 1 }, 
                         { 7, 8, 2 }, 
                         { 0, 0, 0 } };
        double[][] answer = matrixObject.multiply(c, A);
        double[][] expected = { { 15, 18, 3 }, 
                                { 21, 24, 6 }, 
                                {  0,  0, 0 } };
        assert2DArrayEquals(expected, answer, 0.0001);
    }
    

    @Test
    void testGaussEliminationAMatrix() {

        double[][] A = { {  3, -13, 9,   3 }, 
                         { -6,   4, 1, -18 }, 
                         {  6,  -2, 2,   4 }, 
                         { 12,  -8, 6,  10 } };
        int[] l = { 0, 1, 2, 3 };
        double[][] expectedReducedMatrix = { {  0.5, -12,       8,         1        }, 
                                             { -1,   -0.166667, 4.33333, -13.83333  }, 
                                             {  6,   -2,        2,         4        }, 
                                             {  2,    0.33333, -0.15385,  -0.46154 } };
        //Do the Elimination
        matrixObject.gauss(A, l);
        assert2DArrayEquals(expectedReducedMatrix, A, 0.0001);
    }

    @Test
    public void TestGaussEliminationBColumn() {

        double[][] A = { {  3, -13, 9,   3 }, 
                         { -6,   4, 1, -18 }, 
                         {  6,  -2, 2,   4 }, 
                         { 12,  -8, 6,  10 } };
        double[] b = { -19, -34, 16, 26 };
        int[] l = { 0, 1, 2, 3 };

        // Expected b column after elimination
        double[] bExpected = { -27, -22.5, 16, -0.46154 };

        //Do the Elimination
        matrixObject.gauss(A, l);
        matrixObject.gaussModifiedForwardEliminationRHS(A, l, b);

        assertArrayEquals(bExpected, b, 0.0001);
    }


    @Test
    void testGaussEliminationLColumn() {
        double[][] A = { {  3, -13, 9,   3 }, 
                         { -6,   4, 1, -18 }, 
                         {  6,  -2, 2,   4 }, 
                         { 12,  -8, 6,  10 } };
        int[] l = { 0, 1, 2, 3 };

        // The expected index vector
        int[] lExpected = { 2, 0, 1, 3 };

        // Do the Elimination
        matrixObject.gauss(A, l);
        assertArrayEquals(lExpected, l);
    }

    @Test
    void testGaussEliminationXColumn() {

        double[][] A = { {  3, -13, 9,   3 }, 
                         { -6,   4, 1, -18 }, 
                         {  6,  -2, 2,   4 }, 
                         { 12,  -8, 6,  10 } };
        double[] b = { -19, -34, 16, 26 };
        int[] l = { 0, 1, 2, 3 };
        // The expected solution vector
        double[] xExpected = { 3, 1, -2, 1 };

        //Do the Elimination
        matrixObject.gauss(A, l);
        matrixObject.gaussModifiedForwardEliminationRHS(A, l, b);
        double[] x = matrixObject.solve(A, l, b);
        assertArrayEquals(xExpected, x, 0.0001);
    }
}

