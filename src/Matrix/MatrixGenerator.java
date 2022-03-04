package Matrix;

public class MatrixGenerator {
    private int m; //строки
    private int n; //столбцы
    private double minLim; //максимальное возможное значение матрицы
    private double maxLim; //минимальное возможное значение матрицы
    public MatrixGenerator(){
        m=50;
        n=50;
        minLim = -250;
        maxLim = 1000;
    }
    public MatrixGenerator(int str, int col, double maxVal, double minVal){
        m=str;
        n=col;
        minLim = minVal;
        maxLim = maxVal;
    }

    public double[][] getRandMatrix(){
        double[][] matr = new double[m][n];
        for (int i = 0; i<m; i++){
            for (int j=0; j<n; j++){

                matr[i][j] = ((int) ((Math.random()*(maxLim-minLim) + minLim)*1000.0))/1000.0;
            }
        }
        return matr;
    }
}
