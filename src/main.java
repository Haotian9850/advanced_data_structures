public class main {
    /*main class for data structure testing*/
    public static void main(String[] args){
        minHeap heap = new minHeap();
        heap.constructFromArray(new int[]{1, 3, 5, 18, 21, 6, 7, 29, 30, 31, 32, 29, 47, 58, 99});
        heap.display();
    }
}
