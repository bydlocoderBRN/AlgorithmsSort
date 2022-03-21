package Matrix.find;

import Matrix.operations.MatrixAsVector;
import Matrix.operations.MatrixGeneral;
import Matrix.sort.Shell;

public class BinaryFind {

    static double value;
    static MatrixAsVector matrix;
    static int index;
    public static int findBinary(double[][] mas, double val){
        index=-1;
        matrix = new MatrixAsVector(mas);
        value=val;
        MatrixGeneral.printMatrix(matrix.getMatrix());
        System.out.println(value);
        recBinary(0,matrix.getLength()-1);
        return index;
    }

    private static void recBinary(int leftIndex, int rightIndex){
        int pivot = leftIndex + (rightIndex-leftIndex)/2;
        System.out.println(pivot);
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
