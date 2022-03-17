package Matrix.sort;

import Matrix.operations.MatrixAsVector;

public class Insert {
    //Парная сортировка вставками - берет два элемента из неостортированной части.
    //Сначала вставляет больший на свое место в отсортированную
    //Затем вставляет меньший, учитывая что отсорт. часть правее большего э-та рассматривать е нужно
    //Эта сортировка устойчивая. Особенно эффективна с почти отстортированными массивами
    public static double[][] sortInsert(double[][] matrix){
        MatrixAsVector mat = new MatrixAsVector(matrix);
        double buffMax;
        double buffMin;
        for(int i =0;i<mat.getLength()-1;i=i+2){
            if(mat.getElement(i)>mat.getElement(i+1)){
                buffMax= mat.getElement(i);
                buffMin= mat.getElement(i+1);
            }else{
                buffMax= mat.getElement(i+1);
                buffMin= mat.getElement(i);
            }
            if(i>0){
                int iSort = i-1;

                while ((iSort>=0) && buffMax < mat.getElement(iSort) ) {
                    mat.swap(iSort, iSort + 2);
                    iSort--;
                }

                mat.setElement(iSort+2, buffMax);

                while ((iSort>=0) && buffMin < mat.getElement(iSort) ) {
                    mat.swap(iSort, iSort + 1);
                    iSort--;
                }

                mat.setElement(iSort+1, buffMin);
            }else{
                if(buffMax == mat.getElement(i)){
                    mat.swap(i,i+1);
                }
            }
        }
        return  mat.getMatrix();
    }
}
