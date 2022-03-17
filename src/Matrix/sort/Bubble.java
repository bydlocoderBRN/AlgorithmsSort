package Matrix.sort;

import Matrix.operations.MatrixAsVector;

public class Bubble {
    // Сортировка пузыпьком - самая простая в реализации но не эффективная
    public static double[][] sortBubble(double[][] matrix){
        MatrixAsVector matr = new MatrixAsVector(matrix);
        boolean sortDone = false;
        while (!sortDone) {
            sortDone = true;
            for (int i = 0; i < matr.getLength() - 1; i++) {
                if (matr.getElement(i) < matr.getElement(i + 1)) {
                    matr.swap(i, i + 1);
                    sortDone=false;
                }
            }
        }
        return matr.getMatrix();
    }
}
