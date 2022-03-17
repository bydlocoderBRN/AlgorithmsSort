package Matrix.sort;

import Matrix.operations.MatrixAsVector;

public class Heap {
            /*Arr[(i-1)/2]	Returns the parent node
            Arr[(2*i)+1]	Returns the left child node
            Arr[(2*i)+2]	Returns the right child node*/

    static MatrixAsVector matrix;


    private static int getLeftChildIndex(int parentIndex){return (2*parentIndex)+1;}
    private static int getRightChildIndex(int parentIndex){return (2*parentIndex)+2;}
    private static int getParentIndex(int childIndex){return (childIndex-1)/2;}
    private static int currChildIndex;
    private static int endIndex;
    private static boolean isChildHasChildren = false;
    private static boolean isLeftChanged = false;
    private static boolean isRightChanged = false;
    private static boolean isOnlyChildSwap=false;


    public static double[][] heapSort(double[][] mas){
        matrix = new MatrixAsVector(mas);
        currChildIndex = matrix.getLength() - 2;
        endIndex = matrix.getLength() - 1;
        isOnlyChildSwap=false;
        while (endIndex>0){
            heapRecurrent(getParentIndex(currChildIndex));
            matrix.swap(0,endIndex);
            endIndex--;
            isOnlyChildSwap=true;
            currChildIndex=1;
        }
        return matrix.getMatrix();

    }


    private static void heapRecurrent(int parentIndex ){
        if((getLeftChildIndex(parentIndex)<endIndex) && (getRightChildIndex(parentIndex)<endIndex)) {
            isLeftChanged = false;
            isRightChanged = false;
            if (matrix.getElement(getLeftChildIndex(parentIndex)) < matrix.getElement(parentIndex)) {
                if (matrix.getElement(getLeftChildIndex(parentIndex)) < matrix.getElement(getRightChildIndex(parentIndex))) {
                    matrix.swap(getLeftChildIndex(parentIndex), parentIndex);
                    isLeftChanged = true;
                } else {
                    matrix.swap(getRightChildIndex(parentIndex), parentIndex);
                    isRightChanged = true;
                }
            } else if (matrix.getElement(getRightChildIndex(parentIndex)) < matrix.getElement(parentIndex)) {
                if (matrix.getElement(getRightChildIndex(parentIndex)) < matrix.getElement(getLeftChildIndex(parentIndex))) {
                    matrix.swap(getRightChildIndex(parentIndex), parentIndex);
                    isRightChanged = true;
                } else {
                    matrix.swap(getLeftChildIndex(parentIndex), parentIndex);
                    isLeftChanged = true;
                }
            }


            if (isLeftChanged) {
                if (getRightChildIndex(getLeftChildIndex(parentIndex)) < endIndex || getLeftChildIndex(getLeftChildIndex(parentIndex)) < endIndex) {
                    isChildHasChildren = true;
                    int tmpChildIndex = getLeftChildIndex(parentIndex);
                    heapRecurrent(tmpChildIndex);
                    isLeftChanged = false;
                    isRightChanged = false;
                    isChildHasChildren = false;
                }
            } else if (isRightChanged) {
                if (getRightChildIndex(getRightChildIndex(parentIndex)) < endIndex || getLeftChildIndex(getRightChildIndex(parentIndex)) < endIndex) {
                    isChildHasChildren = true;
                    int tmpChildIndex = getRightChildIndex(parentIndex);
                    heapRecurrent(tmpChildIndex);
                    isLeftChanged = false;
                    isRightChanged = false;
                    isChildHasChildren = false;
                }
            } else {
                isChildHasChildren = false;
            }


            if (!isChildHasChildren && (!isOnlyChildSwap)) {
                if (currChildIndex > 0) {
                    currChildIndex--;
                    heapRecurrent(getParentIndex(currChildIndex));
                }
            }
        }


    }





}
