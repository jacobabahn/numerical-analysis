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
    public void testGaussEliminationBColumn() {

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


    @Test
    void testGaussEliminationXMatrix2() {
        double[][] A = {
            { 0.02, 0.01, 0, 0},
            { 1, 2, 1, 0},
            { 0, 1, 2, 1},
            {0, 0, 100, 200}
        };

        double[] b = { 0.02, 1, 4, 800};
        int[] l = { 0, 1, 2, 3 };

        double xExpected[] = { 1, 0, 0, 4};
        matrixObject.gauss(A, l);
        matrixObject.gaussModifiedForwardEliminationRHS(A, l, b);

        double[] x = matrixObject.solve(A, l, b);
        assertArrayEquals(xExpected, x, 0.0001);
    }

    
    @Test
    void testGaussEliminationBColumn2() {
        double[][] A = {
            { 0.02, 0.01, 0, 0},
            { 1, 2, 1, 0},
            { 0, 1, 2, 1},
            {0, 0, 100, 200}
        };

        double[] b = { 0.02, 1, 4, 800};
        int[] l = { 0, 1, 2, 3 };

        double bExpected[] = {0.02, 0, 4, 500};
        matrixObject.gauss(A, l);
        matrixObject.gaussModifiedForwardEliminationRHS(A, l, b);

        assertArrayEquals(bExpected, b, 0.0001);
    }


    @Test
    void testInverse() {
        double[][] A = {
            { 4, 7 },
            { 2, 6 }
        };

        double[][] aExpected = {
            { 0.6, -0.7 },
            { -0.2, 0.4 }
        };

        double[][] a = matrixObject.inverse(A);
        assert2DArrayEquals(aExpected, a, 0.0001);
    }

    void testInverse2() {
        double[][] A = {
            { 2, 5, 8, 1 },
            { 4, 7, 2, 6 },
            { 3, 8, 6, 9 },
            { 1, 4, 7, 2 }
        };

        double[][] aExpected = {
            { -0.03125, 0.0625, 0.03125, -0.0625 },
            { 0.09375, -0.1875, 0.09375, 0.1875 },
            { 0.03125, -0.0625, -0.03125, 0.0625 },
            { -0.09375, 0.1875, -0.09375, -0.1875 }
        };

        double[][] a = matrixObject.inverse(A);
        assert2DArrayEquals(aExpected, a, 0.0001);
    }

    @Test
    void testInverse3() {
        double[][] A = { 
            {  3, -13, 9,   3 }, 
            { -6,   4, 1, -18 }, 
            {  6,  -2, 2,   4 }, 
            { 12,  -8, 6,  10 } 
        };

        double[][] aExpected = {
            {-0.6944444444444445, 0.30555555555555564, -3.486111111111112, 2.152777777777778 },
            { 1.416666666666667, -0.5833333333333334, 8.29166666666667, -4.791666666666668 },
            { 2.166666666666667, -0.8333333333333335, 11.91666666666667, -6.916666666666669 },
            { 0.6666666666666667, -0.33333333333333337, 3.6666666666666674, -2.166666666666667 }
        };

        double[][] a = matrixObject.inverse(A);
        assert2DArrayEquals(aExpected, a, 0.0001);
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
    void testAddition2() {
        double[][] A = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 9 }
        };
        double[][] B = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 9 }
        };

        double[][] sum = matrixObject.add(A, B);
        double[][] expected = {
            { 2, 4, 6 },
            { 8, 10, 12 },
            { 14, 16, 18 }
        };
        assert2DArrayEquals(expected, sum, 0.0001);
    }

    @Test
    void testAddition3() {
        double[][] A = {
            { 1.5, 2.1, 3 },
            { 4, 5, 6.4 },
            { 7, 8, 9.8 }
        };
        double[][] B = {
            { 1, 2, 3 },
            { -4.1, 5, 6 },
            { 7, 8, 9 }
        };

        double[][] sum = matrixObject.add(A, B);
        double[][] expected = {
            { 2.5, 4.1, 6 },
            { -0.1, 10, 12.4 },
            { 14, 16, 18.8 }
        };
        assert2DArrayEquals(expected, sum, 0.0001);
    }

    @Test
    void testAdditionBadDimensions() {
        double[][] A = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 9 }
        };
        double[][] B = {
            { 1, 2, 3 },
            { 4, 5, 6 }
        };

        double [][] sum = matrixObject.add(A, B);
        assertNull(sum);

    }

    @Test
    void testSubtractEmpty() {
        double[][] A = {{}};

        double[][] B = {{}};

        double[][] a = matrixObject.subtract(A, B);
        assert2DArrayEquals(B, a, 0);
    }

    @Test
    void testSubtraction() {
        double[][] A = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 9 }
        };

        double[][] B = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 9 }
        };

        double[][] aExpected = {
            { 0, 0, 0 },
            { 0, 0, 0 },
            { 0, 0, 0 }
        };

        double[][] a = matrixObject.subtract(A, B);
        assert2DArrayEquals(aExpected, a, 0.0001);
    }

    @Test
    void testSubtraction2() {
        double[][] A = {
            { 1.4, 2.5, 3.6 },
            { 4.7, 5.8, 6.9 },
            { 7.1, 8.2, 9.3 }
        };

        double[][] B = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 9 }
        };

        double[][] aExpected = {
            { 0.4, 0.5, 0.6 },
            { 0.7, 0.8, 0.9 },
            { 0.1, 0.2, 0.3 }
        };

        double[][] a = matrixObject.subtract(A, B);
        assert2DArrayEquals(aExpected, a, 0.0001);
    }

    @Test
    void testSubtractionBadDimensions() {
        double[][] A = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 9 }
        };
        double[][] B = {
            { 1, 2, 3 },
            { 4, 5, 6 }
        };

        double [][] a = matrixObject.subtract(A, B);
        assertNull(a);
    }

    @Test
    void testMultiplication() {
        double[][] A = {
            {1, 2, 3}, 
            {4, 5, 6}
        };

        double[][] B = {
            {1, 2, 3}, 
            {4, 5, 6},
            {7, 8, 9}
        };

        double[][] expected = {
            {30, 36, 42 },
            {66, 81, 96 }
        };

        double[][] result = matrixObject.multiply(A, B);
        assert2DArrayEquals(expected, result, 0.0001);
    }

    @Test
    void testMultiplication2() {
        double[][] A = {
            {1, 2, 3}, 
            {4, 5, 6}
        };

        double[][] B = {
            {1, 2}, 
            {4, 5},
            {7, 8}
        };

        double[][] expected = {
            {30, 36},
            {66, 81}
        };

        double[][] result = matrixObject.multiply(A, B);
        assert2DArrayEquals(expected, result, 0.0001);
    }


    @Test
    void testMultiplicationBadDimensions() {
        double[][] A = {
            {1, 2, 9}, 
            {4, 5, 6}
        };

        double[][] B = {
            {3, 2, 3}, 
            {4, 2, 6}
        };

        double[][] result = matrixObject.multiply(A, B);
        assertNull(result);
    }

    @Test
    void testScalarMultiplication() {
        double[][] A = {
            {1, 2, 3}, 
            {4, 5, 6}
        };

        double[][] expected = {
            {2, 4, 6}, 
            {8, 10, 12}
        };

        double[][] result = matrixObject.multiply(A, 2);
        assert2DArrayEquals(expected, result, 0.0001);
    }

    @Test
    void testScalarMultiplication2() {
        double[][] A = {
            {1, 2, 3}, 
            {4, 5, 6}
        };

        double[][] expected = {
            {0.5, 1, 1.5}, 
            {2, 2.5, 3}
        };

        double[][] result = matrixObject.multiply(0.5, A);
        assert2DArrayEquals(expected, result, 0.0001);
    }
}
