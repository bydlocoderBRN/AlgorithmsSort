package Matrix;

import java.security.PublicKey;

public class MatrixOperations {
    public static void printMatrix(double[][] matrix){
        for (int i =0; i<matrix.length;i++){
            for (int j = 0; j<matrix[0].length; j++){
                System.out.println(matrix[i][j]);
            }
            System.out.println("\n");
        }
    }

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
    public static class Tournament{
        /*Рекуррентный алгоритм:
        *Меняем region
        *Проверка счетчика св. мест (если равен 2 (0,1,2), то перекидываем значение с верхушки в массив !!ОБНУЛЯЕТСЯ
        * ПРИ КАЖДОМ РАЗЕ, КОГДА СВОБОДНОЕ МЕСТО БЫЛО НАЙДЕНО)
        * Проверка на свободное место на уровень выше
        *   есть:
        *       Вставляем туда меньший элемент текущего промежутка
        *       На освободивш. место вставляем новый элемент
        *       Сравниваем новый элемент с э-м уровнем выше
        *           Если он меньше:
        *               Меняем местами новый э-т с э-ом уровнем выше
        *               Сравниваем новый э-т на новой позиции с э-м уровнем выше, в случае чего меняем их местами
        *           Если он больше:
        *               прибавляем к счетчику свободных мест +1
        *               запускаем алгоритм снова с другой областью
        *   нет:
        *       прибавляем к счетчику свободных мест +1
        *       запускаем алгоритм снова с другой областью
        *
        *                               top
        *                          /          \
        *                      mid[0]         mid[1]
        *                    /      \         /    \
        *             low[0][0]  low[0][1]   low[1][0] low[1][1]
        *
        *
        *
        *
        *
        * */
        private class TourTree{
            public static class Region{
                public static final String LOW_LEFT = "LOW_LEFT";
                public static final String LOW_RIGHT = "LOW_RIGHT";
                public static final String MIDDLE = "MIDDLE";
                private static String[] regs = {LOW_LEFT,LOW_RIGHT,MIDDLE};
                private static int regsIndex=0;
                public static void nullNextRegion(){
                    regsIndex = 0;
                }
                public static String nextRegion(){
                    if(regsIndex >2){
                        regsIndex=0;
                    }
                    String s  = regs[regsIndex];
                    regsIndex++;
                    return s;
                }
            }
            private static String[][] low = {{"null","null"},{"null","null"}};
            private static String [] mid = {"null","null"};
            private static String top = "null";
            private static String region;
            public static void setNullFull(){
                low[0][0] = "null";
                low[0][1] = "null";
                low[1][0] = "null";
                low[1][1] = "null";
                mid[0] = "null";
                mid[1]="null";
                top = "null";
            }
            public static void setRegion(String region) {
                TourTree.region = region;
            }
            public static double getLeft() {
                if (region.equals(Region.LOW_LEFT)) {
                    return Double.parseDouble(low[0][0]);
                }else
                if (region.equals(Region.LOW_RIGHT)) {
                    return Double.parseDouble(low[1][0]);
                } else
                if (region.equals(Region.MIDDLE)) {
                    return Double.parseDouble(mid[0]);
                }else return Double.MIN_VALUE;
            }
            public static double getRight(){
                if(region.equals(Region.LOW_LEFT)){
                    return Double.parseDouble(low[0][1]);
                }else
                if(region.equals(Region.LOW_RIGHT)){
                    return Double.parseDouble(low[1][1]);
                }else
                if(region.equals(Region.MIDDLE)){
                    return Double.parseDouble(mid[1]);
                }else return Double.MIN_VALUE;
            }
            public static double getUpper(){
                if(region.equals(Region.LOW_LEFT)){
                    return Double.parseDouble(mid[0]);
                }else
                if(region.equals(Region.LOW_RIGHT)){
                    return Double.parseDouble(mid[1]);
                }else
                if(region.equals(Region.MIDDLE)){
                    return Double.parseDouble(top);
                }else return Double.MIN_VALUE;
            }
            public static void setLeft(double val) {
                if (region.equals(Region.LOW_LEFT)) {
                    low[0][0] = String.valueOf(val);
                }else
                if (region.equals(Region.LOW_RIGHT)) {
                    low[1][0] = String.valueOf(val);
                } else
                if (region.equals(Region.MIDDLE)) {
                    mid[0] = String.valueOf(val);
                }
            }
            public static void setRight(double val){
                if(region.equals(Region.LOW_LEFT)){
                    low[0][1]= String.valueOf(val);
                }else
                if(region.equals(Region.LOW_RIGHT)){
                    low[1][1]= String.valueOf(val);
                }else
                if(region.equals(Region.MIDDLE)){
                    mid[1]= String.valueOf(val);
                }
            }
            public static void setUpper(double val){
                if(region.equals(Region.LOW_LEFT)){
                    mid[0] = String.valueOf(val);
                }else
                if(region.equals(Region.LOW_RIGHT)){
                    mid[1] = String.valueOf(val);
                }else
                if(region.equals(Region.MIDDLE)){
                    top= String.valueOf(val) ;
                }
            }
            public static boolean isLeftNull(){
                if (region.equals(Region.LOW_LEFT)) {
                    return low[0][0].equals("null");
                }else
                if (region.equals(Region.LOW_RIGHT)) {
                    return low[1][0].equals("null");
                } else
                if (region.equals(Region.MIDDLE)) {
                    return mid[0].equals("null");
                }else return false;
            }
            public static boolean isRightNull(){
                if (region.equals(Region.LOW_LEFT)) {
                    return low[0][1].equals("null");
                }else
                if (region.equals(Region.LOW_RIGHT)) {
                    return low[1][1].equals("null");
                } else
                if (region.equals(Region.MIDDLE)) {
                    return mid[1].equals("null");
                }else return false;
            }
            public static boolean isUpperNull(){
                if (region.equals(Region.LOW_LEFT)) {
                    return mid[0].equals("null");
                }else
                if (region.equals(Region.LOW_RIGHT)) {
                    return mid[1].equals("null");
                } else
                if (region.equals(Region.MIDDLE)) {
                    return top.equals("null");
                }else return false;
            }
            public static void setLeftNull(){
                if(region.equals(Region.LOW_LEFT)){
                    low[0][0]= "null";
                }else
                if(region.equals(Region.LOW_RIGHT)){
                    low[1][0]= "null";
                }else
                if(region.equals(Region.MIDDLE)){
                    mid[0]= "null";
                }
            }
            public static void setRightNull(){
                if(region.equals(Region.LOW_LEFT)){
                    low[0][1]= "null";
                }else
                if(region.equals(Region.LOW_RIGHT)){
                    low[1][1]= "null";
                }else
                if(region.equals(Region.MIDDLE)){
                    mid[1]= "null";
                }
            }
            public static void setUpperNull(){
                if(region.equals(Region.LOW_LEFT)){
                    mid[0]= "null";
                }else
                if(region.equals(Region.LOW_RIGHT)){
                    mid[1]= "null";
                }else
                if(region.equals(Region.MIDDLE)){
                    top= "null";
                }
            }
            public static void addLeftElement(double val){
                if(region.equals(Region.LOW_LEFT)){
                    low[0][0] = String.valueOf(val);
                    if(!mid[0].equals("null")){
                        if(Double.parseDouble(low[0][0])<Double.parseDouble(mid[0])){
                            String tmp = low[0][0];
                            low[0][0] = mid[0];
                            mid[0]=tmp;
                            if(!top.equals("null")){
                                if(Double.parseDouble(mid[0])<Double.parseDouble(top)){
                                    String tmp1 = mid[0];
                                    mid[0] = top;
                                    top=tmp1;
                                }
                            }
                        }
                    }
                }else
                if(region.equals(Region.LOW_RIGHT)){
                    low[1][0] = String.valueOf(val);
                    if(!mid[1].equals("null")) {
                        if (Double.parseDouble(low[1][0]) < Double.parseDouble(mid[1])) {
                            String tmp = low[1][0];
                            low[1][0] = mid[1];
                            mid[1] = tmp;
                            if (!top.equals("null")) {
                                if (Double.parseDouble(mid[1]) < Double.parseDouble(top)) {
                                    String tmp1 = mid[1];
                                    mid[1] = top;
                                    top = tmp1;
                                }
                            }
                        }

                    }
                }
            }

            public static void addRightElement(double val){
                if(region.equals(Region.LOW_LEFT)){
                    low[0][1] = String.valueOf(val);
                    if(!mid[0].equals("null")){
                        if(Double.parseDouble(low[0][1])<Double.parseDouble(mid[0])){
                            String tmp = low[0][1];
                            low[0][1] = mid[0];
                            mid[0]=tmp;
                            if(!top.equals("null")){
                                if(Double.parseDouble(mid[0])<Double.parseDouble(top)){
                                    String tmp1 = mid[0];
                                    mid[0] = top;
                                    top=tmp1;
                                }
                            }
                        }
                    }
                }else
                if(region.equals(Region.LOW_RIGHT)){
                    low[1][1] = String.valueOf(val);
                    if(!mid[1].equals("null")) {
                        if (Double.parseDouble(low[1][1]) < Double.parseDouble(mid[1])) {
                            String tmp = low[1][1];
                            low[1][1] = mid[1];
                            mid[1] = tmp;
                            if (!top.equals("null")) {
                                if (Double.parseDouble(mid[1]) < Double.parseDouble(top)) {
                                    String tmp1 = mid[1];
                                    mid[1] = top;
                                    top = tmp1;
                                }
                            }
                        }

                    }
                }
            }

            public static double getTop() {
                return Double.parseDouble(top);
            }
            public static void nullTop(){
                top = "null";
            }
            public static boolean isTreeIsNull(){
                return low[0][0].equals("null") && low[0][1].equals("null") && low[1][0].equals("null") && low[1][1].equals("null") && mid[0].equals("null") && mid[1].equals("null") && top.equals("null");
            }
        }
        private static MatrixAsVector winners;
        private static MatrixAsVector losers;
        private static MatrixAsVector mainMatrix;
        private static int countRecurrent;
        private static int mainMasIndex;
        private static int winIndex;
        private static int looseIndex;
        private static boolean isLowLeftNull;
        private static boolean isLowRightNull;
        private static int sortTourRecurrentNum =0;
        public static double[][] sortTournament(double[][] mas){
            sortTourRecurrentNum++;
                winners = new MatrixAsVector(new double[mas.length][mas[0].length]);
                losers = new MatrixAsVector(new double[mas.length][mas[0].length]);
                mainMatrix = new MatrixAsVector(mas);
                countRecurrent = 0;
                mainMasIndex = 0;
                winIndex = 0;
                looseIndex = 0;
                isLowLeftNull = false;
                isLowRightNull = false;
                TourTree.Region.nullNextRegion();
                TourTree.setNullFull();
                if (mainMatrix.getLength() >= 4) {
                    TourTree.setRegion(TourTree.Region.LOW_LEFT);
                    TourTree.setLeft(mainMatrix.getElement(0));
                    TourTree.setRight(mainMatrix.getElement(1));
                    TourTree.setRegion(TourTree.Region.LOW_RIGHT);
                    TourTree.setLeft(mainMatrix.getElement(2));
                    TourTree.setRight(mainMatrix.getElement(3));
                    mainMasIndex = 4;
                    recurrentTree(TourTree.Region.nextRegion());
                    if(sortTourRecurrentNum<100) {
                        for (int i = 0; i < looseIndex; i++) {
                            mainMatrix.setElement(i, losers.getElement(i));
                        }
                        for (int i = 0; i < winIndex; i++) {
                            mainMatrix.setElement(i + looseIndex, winners.getElement(i));
                        }
//                        MatrixOperations.printMatrix(mainMatrix.getMatrix());
//                        System.out.println("===========================");
                        sortTournament(mainMatrix.getMatrix());
                    }else sortTourRecurrentNum=0;
                } else System.out.println("Array is too short");
//            MatrixOperations.printMatrix(losers.getMatrix());
//            System.out.println("==========================================");
            return winners.getMatrix();
        }


        private static void recurrentTree(String region){
            if(mainMasIndex<mainMatrix.getLength()) {
                if (countRecurrent <= 3) {
                    TourTree.setRegion(region);
                    if (TourTree.isUpperNull()) {
                        countRecurrent = 0;
                        if (TourTree.getLeft() < TourTree.getRight()) {
                            TourTree.setUpper(TourTree.getLeft());
                            TourTree.setLeftNull();
                            if(!region.equals(TourTree.Region.MIDDLE)) {
                                TourTree.addLeftElement(mainMatrix.getElement(mainMasIndex));
                                mainMasIndex++;
                            }
                        } else {
                            TourTree.setUpper(TourTree.getRight());
                            TourTree.setRightNull();
                            if(!region.equals(TourTree.Region.MIDDLE)) {
                                TourTree.addRightElement(mainMatrix.getElement(mainMasIndex));
                                mainMasIndex++;
                            }
                        }
                        recurrentTree(TourTree.Region.nextRegion());

                    }else{
                        countRecurrent++;
                        recurrentTree(TourTree.Region.nextRegion());
                    }
                } else {
                    loseOrWin(TourTree.getTop());
                    TourTree.nullTop();
                    countRecurrent = 0;
                    recurrentTree(TourTree.Region.nextRegion());
                }

            }else {
                TourTree.setRegion(TourTree.Region.MIDDLE);
                if (!TourTree.isUpperNull()) {
                    loseOrWin(TourTree.getTop());
                    TourTree.nullTop();
                }
                if(!isLowRightNull && !isLowLeftNull) {
                    if (TourTree.getLeft() < TourTree.getRight()) {
                        TourTree.setUpper(TourTree.getLeft());
                        TourTree.setLeftNull();
                    } else {
                        TourTree.setUpper(TourTree.getRight());
                        TourTree.setRightNull();
                    }
                }
                if(isLowLeftNull && TourTree.isLeftNull()){
                    TourTree.setUpper(TourTree.getRight());
                    TourTree.setRightNull();
                }
                if(isLowRightNull && TourTree.isRightNull()){
                    TourTree.setUpper(TourTree.getLeft());
                    TourTree.setLeftNull();
                }
                loseOrWin(TourTree.getTop());
                TourTree.nullTop();
                if (TourTree.isLeftNull() && !isLowLeftNull) {
                    TourTree.setRegion(TourTree.Region.LOW_LEFT);
                    if(!(TourTree.isLeftNull() && TourTree.isRightNull())) {
                        if (TourTree.isLeftNull()) {
                            TourTree.setUpper(TourTree.getRight());
                            TourTree.setRightNull();
                        } else if (TourTree.isRightNull()) {
                            TourTree.setUpper(TourTree.getLeft());
                            TourTree.setLeftNull();
                        } else {
                            if (TourTree.getLeft() < TourTree.getRight()) {
                                TourTree.setUpper(TourTree.getLeft());
                                TourTree.setLeftNull();
                            } else {
                                TourTree.setUpper(TourTree.getRight());
                                TourTree.setRightNull();
                            }
                        }
                    }else {isLowLeftNull = true;}
                } else if(TourTree.isRightNull() && !isLowRightNull){

                    TourTree.setRegion(TourTree.Region.LOW_RIGHT);
                    if(!(TourTree.isLeftNull() && TourTree.isRightNull())) {
                        if (TourTree.isLeftNull()) {
                            TourTree.setUpper(TourTree.getRight());
                            TourTree.setRightNull();
                        } else if (TourTree.isRightNull()) {
                            TourTree.setUpper(TourTree.getLeft());
                            TourTree.setLeftNull();
                        } else {
                            if (TourTree.getLeft() < TourTree.getRight()) {
                                TourTree.setUpper(TourTree.getLeft());
                                TourTree.setLeftNull();
                            } else {
                                TourTree.setUpper(TourTree.getRight());
                                TourTree.setRightNull();
                            }
                        }
                    }else{isLowRightNull=true;}

                }
                if(!TourTree.isTreeIsNull()) {
                    Tournament.recurrentTree(TourTree.Region.nextRegion());
                }
            }
        }
        private static void loseOrWin(double val){
            if(winIndex==0){
                winners.setElement(winIndex,val);
                winIndex++;
            }else{
                if(winners.getElement(winIndex-1)<=val){
                    winners.setElement(winIndex,val);
                    winIndex++;
                }else{
                    losers.setElement(looseIndex,val);
                    looseIndex++;
                }
            }
        }
    }
}
