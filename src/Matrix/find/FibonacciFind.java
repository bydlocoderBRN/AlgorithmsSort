package Matrix.find;

import Matrix.operations.MatrixAsVector;
import Matrix.operations.MatrixGeneral;

public class FibonacciFind {

    private static int[] addToMas(int val, int[] mas){
        int[] mas1 = new int[mas.length+1];
        for (int i = 0; i<mas.length;i++){
            mas1[i]=mas[i];
        }
        mas1[mas.length]=val;
        return mas1;
    }
    private static int[] getFib(int maxVal){
        int fibMas[] = new int[2];
        fibMas[0] = 0;
        fibMas[1]=1;
        int v1=1;
        int v2=1;
        int v3 =0;
        if(maxVal>2) {
            while (v3 < maxVal) {
                v3 = v1 + v2;
                fibMas = addToMas(v3, fibMas);
                v2 = v1;
                v1 = v3;
            }
        }else{fibMas = addToMas(2,fibMas);}


        return fibMas;
    }
    private static MatrixAsVector matrix;
    private static double value;
    private static int mainIndex;
    public static int findFib(double[][] mas, double val){
//        System.out.println("Matrix: ");
//        MatrixGeneral.printMatrix(mas);
//        System.out.println("Value = " + val);
        matrix = new MatrixAsVector(mas);
        value=val;
        mainIndex=-1;
        fibRec(0,matrix.getLength()-1);
        return mainIndex;
    }
    private static void fibRec(int start, int finish){
//        System.out.println("Start" + start + " finish"+ finish);
        int[] fibMas = getFib(finish-start);


        if (fibMas[fibMas.length-1]>finish){
            fibMas[fibMas.length-1]=finish-start;
        }

        System.out.println();
        boolean leftFound = false;
        boolean rightFound = false;
        int leftBoard=0;
        int rightBoard=1;
//        System.out.println("Start cycle");
        for (int i :fibMas){
            i=i+start;
//            System.out.println("I= "+ matrix.getElement(i) + " Value = " + value);
            if(value==matrix.getElement(i)){
                mainIndex=i;
                break;
            }else {
                if (value> matrix.getElement(i)){
                    leftFound=true;
                    leftBoard=i;
                }else
                    if(value<matrix.getElement(i)){
                        rightFound=true;
                        rightBoard=i;
                    }
                if(leftFound && rightFound){
                    fibRec(leftBoard,rightBoard);
                }
            }

        }
    }

}
