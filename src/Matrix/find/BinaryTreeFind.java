package Matrix.find;

import Matrix.operations.MatrixAsVector;
import Matrix.operations.MatrixGeneral;

public class BinaryTreeFind {
    public static int findInTree(double[][] mas, double val){
        return new Tree(mas).findNodeByValue(val).getIndex();
    }
    private static class Node {
        private static int globalindex;
        private int index;
        private double value; // ключ узла
        private Node leftChild; // Левый узел потомок
        private Node rightChild; // Правый узел потомок
        Node(){
            this.index = globalindex+1;
            globalindex++;
        }
        public void printNode() { // Вывод значения узла в консоль
            System.out.println(" Выбранный узел имеет значение :" + value);
        }
        public static void nullGlobalInd(){
            globalindex=0;
        };
        public static void setGlobalindex(int ind){
            globalindex=ind;
        }
        public int getIndex(){
            return this.index;
        }
        public double getValue() {
            return this.value;
        }

        public void setValue(final double value) {
            this.value = value;
        }

        public Node getLeftChild() {
            return this.leftChild;
        }

        public void setLeftChild(final Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return this.rightChild;
        }

        public void setRightChild(final Node rightChild) {
            this.rightChild = rightChild;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    '}';
        }
    }

    private static class Tree {
        private Node rootNode; // корневой узел

        public Tree() { // Пустое дерево
            rootNode = null;
        }
        public Tree(double[][] mas){
            Node.setGlobalindex(-1);
            MatrixAsVector matrix = new MatrixAsVector(mas);
            rootNode=null;
            for (int i =0; i< matrix.getLength();i++){
                insertNode(matrix.getElement(i));
            }
            Node.nullGlobalInd();
        }

        public Node findNodeByValue(double value) { // поиск узла по значению
            Node currentNode = rootNode; // начинаем поиск с корневого узла
            while (currentNode.getValue() != value) { // поиск покуда не будет найден элемент или не будут перебраны все
                if (value < currentNode.getValue()) { // движение влево?
                    currentNode = currentNode.getLeftChild();
                } else { //движение вправо
                    currentNode = currentNode.getRightChild();
                }
                if (currentNode == null) { // если потомка нет,
                    return null; // возвращаем null
                }
            }
            return currentNode; // возвращаем найденный элемент
        }

        public void insertNode(double value) { // метод вставки нового элемента
            Node newNode = new Node(); // создание нового узла
            newNode.setValue(value); // вставка данных
            if (rootNode == null) { // если корневой узел не существует
                rootNode = newNode;// то новый элемент и есть корневой узел
            }
            else { // корневой узел занят
                Node currentNode = rootNode; // начинаем с корневого узла
                Node parentNode;
                while (true) // мы имеем внутренний выход из цикла
                {
                    parentNode = currentNode;
                    if(value == currentNode.getValue()) {   // если такой элемент в дереве уже есть, не сохраняем его
                        return;    // просто выходим из метода
                    }
                    else  if (value < currentNode.getValue()) {   // движение влево?
                        currentNode = currentNode.getLeftChild();
                        if (currentNode == null){ // если был достигнут конец цепочки,
                            parentNode.setLeftChild(newNode); //  то вставить слева и выйти из методы
                            return;
                        }
                    }
                    else { // Или направо?
                        currentNode = currentNode.getRightChild();
                        if (currentNode == null) { // если был достигнут конец цепочки,
                            parentNode.setRightChild(newNode);  //то вставить справа
                            return; // и выйти
                        }
                    }
                }
            }
        }


        }


//    static MatrixAsVector matrix;
//    static MatrixAsVector matrixTree;
//    static int matrixIndex;
//    static int valIndex;
//    public static int BiTreeFind(double[][] mas, double val){
//        matrixIndex=0;
//        valIndex=-1;
//
//        double[][] masTree= new double[mas.length][mas[0].length];
//        for(int i =0;i<masTree.length;i++){
//            for(int j = 0; j<masTree[0].length;j++){
//                masTree[i][j]=Double.MIN_VALUE;
//            }
//        }
//        matrix = new MatrixAsVector(mas);
//        matrixTree = new MatrixAsVector(masTree);
//        while(matrixIndex<matrix.getLength()){
//            BinaryTree.recurrentTree(matrix.getElement(matrixIndex),0, true);
//            matrixIndex++;
//        }
//
//        BinaryTree.recurrentTree(val,0,false);
//        MatrixGeneral.printMatrix(masTree);
//        System.out.println("VAL = " + val);
//        return valIndex;
//
//
//    }
//
//    private class BinaryTree{
//
//            /*каждый узел имеет не более двух детей;
//каждое значение, меньшее, чем значение узла, становится левым ребенком или ребенком левого ребенка;
//каждое значение, большее или равное значению узла, становится правым ребенком или ребенком правого ребенка. */
//
//
//
//        private static int getLeftChildIndex(int parentIndex){return (2*parentIndex)+1;}
//        private static int getRightChildIndex(int parentIndex){return (2*parentIndex)+2;}
//        private static int getParentIndex(int childIndex){return (childIndex-1)/2;}
//
//        private static void recurrentTree(double val, int pIndex, boolean isBuild){
//            if(isBuild) {
//                if (matrixTree.getElement(pIndex) == Double.MIN_VALUE) {
//                    matrixTree.setElement(pIndex, val);
//                } else {
//                    if (val < matrixTree.getElement(pIndex)) {
//                        if (getLeftChildIndex(pIndex) < matrixTree.getLength()) {
//                            recurrentTree(val, getLeftChildIndex(pIndex), true);
//                        }
//                    } else {
//                        if (getRightChildIndex(pIndex) < matrixTree.getLength()) {
//                            recurrentTree(val, getRightChildIndex(pIndex), true);
//                        }
//                    }
//
//                }
//            }else {
//                if(matrixTree.getElement(pIndex)==val){
//                    valIndex=pIndex;
//                }else {
//                    if(val<matrixTree.getElement(pIndex)) {
//                        if (getLeftChildIndex(pIndex) < matrixTree.getLength()) {
//                            recurrentTree(val, getLeftChildIndex(pIndex), false);
//                        }
//                    }else {
//                        if (getRightChildIndex(pIndex) < matrixTree.getLength()) {
//                            recurrentTree(val, getRightChildIndex(pIndex), false);
//                        }
//                    }
//                }
//            }
//
//        }
//    }



}
