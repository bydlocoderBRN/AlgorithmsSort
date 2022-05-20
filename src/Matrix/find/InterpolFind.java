package Matrix.find;

import Matrix.operations.MatrixAsVector;
import Matrix.operations.MatrixGeneral;

public class InterpolFind {
    static double value;
    static MatrixAsVector matrix;
    static int index;
    public static int findInterpol(double[][] mas, double val){
        index=-1;
        matrix = new MatrixAsVector(mas);
        value=val;
        MatrixGeneral.printMatrix(matrix.getMatrix());

        recBinary(0,matrix.getLength()-1);
        return index;
    }

    private static void recBinary(int leftIndex, int rightIndex){
        double probe = leftIndex + ((rightIndex-leftIndex)*(value - matrix.getElement(leftIndex)))/(matrix.getElement(rightIndex)-matrix.getElement(leftIndex));
        int pivot = (int) probe;

        if(matrix.getElement(pivot) == value){
            index = pivot;
        }else{
            if(rightIndex-leftIndex>0) {
                if (value > matrix.getElement(pivot)) {
                    recBinary(pivot + 1, rightIndex);
                } else if (value < matrix.getElement(pivot)) {
                    recBinary(leftIndex, pivot - 1);
                }
            }
        }
    }
}
