package Matrix.operations;

public class MatrixGeneral {
    public static void printMatrix(double[][] matrix){
        for (int i =0; i<matrix.length;i++){
            for (int j = 0; j<matrix[0].length; j++){
                System.out.println(matrix[i][j]);
            }
            System.out.println("\n");
        }
    }
}
