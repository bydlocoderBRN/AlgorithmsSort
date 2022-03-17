package Matrix.sort;

import Matrix.operations.MatrixAsVector;

public class Shell {
    public static double[][] shellSort(double[][] matrix){
        MatrixAsVector mat = new MatrixAsVector(matrix);
        int step = mat.getLength()/2;

        while (step>=1){
            for(int i = step;i<mat.getLength();i++){
                for(int j = i; j>=step;j=j-step){
                    if(mat.getElement(j)<mat.getElement(j-step)){
                        mat.swap(j,j-step);
                    }else break;
                }


            }
            step=step/2;
        }
        return mat.getMatrix();
    }

}
