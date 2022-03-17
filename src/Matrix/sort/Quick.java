package Matrix.sort;

import Matrix.operations.MatrixAsVector;

public class Quick {
    static MatrixAsVector matrix;
    public static double[][] quickSort(double[][] mas){
        matrix = new MatrixAsVector(mas);
        quickSortRec(0, matrix.getLength()-1);
        return matrix.getMatrix();
    }
    private static void quickSortRec(int startIndex, int finishIndex) {

        int leftMarker = startIndex;
        int rightMarker = finishIndex;
        double pivot = matrix.getElement((leftMarker + rightMarker) / 2);
        while (leftMarker <= rightMarker){

            while (matrix.getElement(leftMarker) < pivot) {
                leftMarker++;
            }

            while (matrix.getElement(rightMarker) > pivot) {
                rightMarker--;
            }



            if (leftMarker < rightMarker) {
                matrix.swap(leftMarker,rightMarker);
            }

            leftMarker++;
            rightMarker--;

        }


        if (leftMarker < finishIndex) {
            quickSortRec(leftMarker, finishIndex);
        }
        if (startIndex< rightMarker) {
            quickSortRec(startIndex, rightMarker);
        }
//        boolean isLeftDone = false;
//        boolean isRightDone = false;
//        boolean isCursorsNear = false;
//        boolean stopCycle = false;
//        int pivot = (finishIndex - startIndex) / 2;
//        matrix.swap(pivot, finishIndex);
//        pivot = finishIndex;
//
//        int leftCursor = startIndex;
//        int rightCursor = finishIndex - 1;
//
//
////        if (finishIndex - startIndex > 1) {
//            while (!stopCycle) {
//                if (rightCursor - leftCursor == 1) {
//                    isCursorsNear = true;
//                }
//                if (matrix.getElement(leftCursor) >= matrix.getElement(pivot)) {
//                    isLeftDone = true;
//                } else {
//                    if (!isCursorsNear) {
//                        leftCursor++;
//                    }
//                }
//                if (rightCursor - leftCursor == 1) {
//                    isCursorsNear = true;
//                }
//                if (matrix.getElement(rightCursor) < matrix.getElement(pivot)) {
//                    isRightDone = true;
//                } else {
//                    if (!isCursorsNear) {
//                        if (rightCursor > 1) {
//                            rightCursor--;
//                        }
//                    }
//                }
//                if (rightCursor - leftCursor == 1) {
//                    isCursorsNear = true;
//                }
//                if (isRightDone && isLeftDone) {
//                    matrix.swap(leftCursor, rightCursor);
//                    isLeftDone = false;
//                    isRightDone = false;
//                }
//                if (isCursorsNear) {
//                    if (matrix.getElement(leftCursor) <= matrix.getElement(pivot) && matrix.getElement(rightCursor) > matrix.getElement(pivot)) {
//                        while (pivot != rightCursor) {
//                            matrix.swap(pivot, pivot - 1);
//                            pivot--;
//                        }
//                        stopCycle = true;
//                    }else
//                    if (matrix.getElement(leftCursor) >= matrix.getElement(pivot) && matrix.getElement(rightCursor) < matrix.getElement(pivot)) {
//                        matrix.swap(leftCursor, rightCursor);
//                        while (pivot != rightCursor) {
//                            matrix.swap(pivot, pivot - 1);
//                            pivot--;
//                        }
//                        stopCycle = true;
//                    }else
//                    if (matrix.getElement(leftCursor) >= matrix.getElement(pivot) && matrix.getElement(rightCursor) > matrix.getElement(pivot)) {
//                        while (pivot != leftCursor) {
//                            matrix.swap(pivot, pivot - 1);
//                            pivot--;
//                        }
//                        stopCycle = true;
//                    }else
//                    if (matrix.getElement(leftCursor) <= matrix.getElement(pivot) && matrix.getElement(rightCursor) < matrix.getElement(pivot)) {
//                        while (pivot != rightCursor + 1) {
//                            matrix.swap(pivot, pivot - 1);
//                            pivot--;
//                        }
//                        stopCycle = true;
//                    }
//                }
//
//            }
////        }
//
//        //ПОЧЕМУ ТЫ НЕ ВЫХОДИШЬ ИЗ РЕКУРСИИ СУЧАРА??????????????????????
//
////        if(pivot > 0 && pivot<finishIndex){
////
////        }else if(pivot ==0){
////            startIndex = 1;
////        }else if(pivot ==finishIndex){
////            startIndex = 0;
////            finishIndex=pivot-1;
////        }
//
////        if (finishIndex - startIndex > 1) {
////            System.out.println("pivot " + pivot + " = " + matrix.getElement(pivot) + " startIndex = " + startIndex + " finishIndex = " + finishIndex);
////            if (pivot > 0 && pivot < finishIndex) {
//               if(pivot-1>1) {
//                   if(pivot-1-startIndex>1) {
//                       quickSortRec(startIndex, pivot - 1);
//                   }
//               }
//               if((finishIndex - (pivot+1))>1) {
//                   quickSortRec(pivot + 1, finishIndex);
//               }
//
////            } else if (pivot == 0) {
////                System.out.println("start3");
////                quickSort(matrix.getMatrix(), 1, finishIndex);
////                System.out.println("finish3");
////
////            } else if (pivot == finishIndex) {
////                System.out.println("start4");
////                quickSort(matrix.getMatrix(), 0, finishIndex - 1);
////                System.out.println("finish4");
////
////            }
////        }


    }
}
