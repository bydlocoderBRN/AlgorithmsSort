package Matrix.operations;
public class MatrixAsVector {

    private double[][] originMatrix;
    private int len;
    public MatrixAsVector(double[][] mat){
        originMatrix = mat;
        len = originMatrix[0].length*originMatrix.length;
    }

    public int getLength(){
        return len;
    }


    public double getElement(int index) {

        return originMatrix[index/originMatrix[0].length][index% originMatrix[0].length];
    }


    public void setElement(int index, double value) {
        originMatrix[index/originMatrix[0].length][index% originMatrix[0].length] = value;
    }


    public void swap(int index1, int index2) {
       double tmp = originMatrix[index1/originMatrix[0].length][index1% originMatrix[0].length];
        originMatrix[index1/originMatrix[0].length][index1% originMatrix[0].length] = originMatrix[index2/originMatrix[0].length][index2% originMatrix[0].length];
        originMatrix[index2/originMatrix[0].length][index2% originMatrix[0].length] = tmp;
    }
    public double[][] getMatrix(){
        return originMatrix;
    }


}
