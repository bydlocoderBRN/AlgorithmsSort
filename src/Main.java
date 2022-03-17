
import Matrix.operations.MatrixAsVector;
import Matrix.operations.MatrixGenerator;
import Matrix.sort.*;

public class Main {

    public static void main(String[] args) {
        MatrixGenerator mg5050 = new MatrixGenerator();
        MatrixAsVector matrix5050 = new MatrixAsVector(mg5050.getRandMatrix());
        MatrixGenerator mg1015 = new MatrixGenerator(10, 15, 500, -500);
        MatrixAsVector matrix1015 = new MatrixAsVector(mg1015.getRandMatrix());
        MatrixGenerator mg2020 = new MatrixGenerator(20,20,1000,-1000);
        MatrixAsVector matrix2020 = new MatrixAsVector(mg2020.getRandMatrix());
        MatrixGenerator mg65 = new MatrixGenerator(6,5,500,-500);
        MatrixAsVector matrix65 = new MatrixAsVector(mg65.getRandMatrix());
        long start1 = System.currentTimeMillis();
        double[][] mBubble = Bubble.sortBubble(matrix2020.getMatrix());
        long finish1 = System.currentTimeMillis();
        long elapsed1 = finish1 - start1;
        System.out.println("Прошло времени, мс (bubble): " + elapsed1);
        long start2 = System.currentTimeMillis();
        double[][] mSelect = Selection.sortSelection(matrix2020.getMatrix());
        long finish2 = System.currentTimeMillis();
        long elapsed2 = finish2 - start2;
        System.out.println("Прошло времени, мс (Select): " + elapsed2);
        long start3 = System.currentTimeMillis();
        double[][] mInsert = Insert.sortInsert(matrix2020.getMatrix());
        long finish3 = System.currentTimeMillis();
        long elapsed3 = finish3 - start3;
        System.out.println("Прошло времени, мс (insert): " + elapsed3);
        long start4 = System.currentTimeMillis();
        double[][] mShell = Shell.shellSort(matrix2020.getMatrix());
        long finish4 = System.currentTimeMillis();
        long elapsed4 = finish4 - start4;
        System.out.println("Прошло времени, мс (shell): " + elapsed4);
        long start = System.currentTimeMillis();
        double[][] mTour = Tournament.sortTournament(matrix2020.getMatrix());
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("Прошло времени, мс (tournament): " + elapsed);
        long start5 = System.currentTimeMillis();
        double[][] mQuick = Quick.quickSort(matrix2020.getMatrix());
        long finish5 = System.currentTimeMillis();
        long elapsed5 = finish5 - start5;
        System.out.println("Прошло времени, мс (quick): " + elapsed5);
        long start6 = System.currentTimeMillis();
        double[][] mHeap = Heap.heapSort(matrix2020.getMatrix());
        long finish6 = System.currentTimeMillis();
        long elapsed6 = finish6 - start6;
        System.out.println("Прошло времени, мс (heap): " + elapsed6);


        System.out.println("Bubble matrix: "+ "\n");
        MatrixAsVector.printMatrix(mBubble);
        System.out.println("SelectMatrix: "+ "\n");
        MatrixAsVector.printMatrix(mSelect);
        System.out.println("Insert Matrix: "+ "\n");
        MatrixAsVector.printMatrix(mInsert);
        System.out.println("Shell Matrix: "+ "\n");
        MatrixAsVector.printMatrix(mShell);
        System.out.println("Tour MATRIX: " + "\n");
        MatrixAsVector.printMatrix(mTour);
        System.out.println("Quick MATRIX: " + "\n");
        MatrixAsVector.printMatrix(mQuick);
        System.out.println("Heap MATRIX: " + "\n");
        MatrixAsVector.printMatrix(mHeap);

    }
}