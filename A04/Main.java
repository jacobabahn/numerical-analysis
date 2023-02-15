import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        var matrix = new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        var matrix2 = new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        var calculator = new MatrixCalculator();
        // var result = calculator.add(matrix, matrix2);
        // System.out.println(Arrays.deepToString(result));
        // System.out.println(Arrays.deepToString(calculator.subtract(matrix, matrix2)));
        // System.out.println(Arrays.deepToString(calculator.multiply(matrix, matrix2)));
        // System.out.println(Arrays.deepToString(calculator.multiply(matrix, 2)));
        // System.out.println(Arrays.deepToString(calculator.multiply(3, matrix2)));

        double[][] A = { {  3, -13, 9,   3 }, 
                         { -6,   4, 1, -18 }, 
                         {  6,  -2, 2,   4 }, 
                         { 12,  -8, 6,  10 } };
        int[] l = { 0, 1, 2, 3 };

        
    }
}
