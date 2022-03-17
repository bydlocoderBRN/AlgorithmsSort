package Matrix.sort;

import Matrix.operations.MatrixAsVector;

public class Selection {
    // сортировка выбором - фактически делит массив на неотсортированный и отсортированный.
    // В неотсортированной части ищется максимальный элемент и вставляется справа -  в отсортированную.
    //Данная сортировка неустойчива - т.е. одинаковые элементы могут иметь разные индексы после нскольких сортировок
    public static double[][] sortSelection(double[][] matrix){
        MatrixAsVector mtx = new MatrixAsVector(matrix);
        int maxIndex = 0;
        for(int start=0;start<mtx.getLength()-1;start++) {

            double max = mtx.getElement(start);
            for (int i = start; i < mtx.getLength(); i++) {
                if(mtx.getElement(i)>max){
                    max = mtx.getElement(i);
                    maxIndex = i;
                }
            }
            mtx.swap(start,maxIndex);
        }
        return mtx.getMatrix();
    }

}
