package Matrix.sort;


import Matrix.operations.MatrixAsVector;

public class Tournament {

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
    private static MatrixAsVector winners1;
    private static MatrixAsVector losers;
    private static MatrixAsVector mainMatrix;
    private static int countRecurrent;
    private static int mainMasIndex;
    private static int winIndex;
    private static int looseIndex;
    private static boolean isLowLeftNull;
    private static boolean isLowRightNull;
    public static double[][] sortTournament(double[][] mas){
        winners = new MatrixAsVector(new double[mas.length][mas[0].length]);
        winners1 = new MatrixAsVector(new double[mas.length][mas[0].length]);
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
            if(looseIndex!=0) {
                for (int i = 0; i < looseIndex; i++) {
                    mainMatrix.setElement(i, losers.getElement(i));
                }
                for (int i = 0; i < winIndex; i++) {
                    mainMatrix.setElement(i + looseIndex, winners.getElement(i));
                }
                sortTournament(mainMatrix.getMatrix());
            }
        } else System.out.println("Array is too short");
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
