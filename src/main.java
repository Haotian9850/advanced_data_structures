public class main {
    /*main class for data structure testing*/
    public static void main(String[] args){
        int[] array = new int[]{12, 2, 23, 565, 5, 4, 78, 8999, 145615, 2, 1, 0, 56};
        binarySearchTree testBST = new binarySearchTree();
        testBST.constructFromArray(array);
        testBST.display();
        testBST.delete(12);
        testBST.display();
        testBST.insert(9);
        testBST.display();
        testBST.delete(0);
        testBST.display();
    }
}
