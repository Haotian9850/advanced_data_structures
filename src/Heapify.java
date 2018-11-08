public class Heapify {
    /*
    * Array-based heapify operations (min & max heap) 1-index based heap implementation
    * */

    public static void maxHeapify(int[] heap, int i){
        //find left and right child
        int leftIndex = i * 2;
        int rightIndex = i * 2 + 1;
        //check
        int maxIndex = i;
        if(leftIndex < heap.length && heap[leftIndex] > heap[maxIndex]){
            //asuming no duplicates?
            maxIndex = leftIndex;
        }
        if(rightIndex < heap.length && heap[rightIndex] > heap[maxIndex]){
            maxIndex = rightIndex;
        }
        if(i != maxIndex){
            swap(heap, i, maxIndex);
            maxHeapify(heap, maxIndex);
        }
    }

    public static void minHeapify(int[] heap, int i){
        int leftIndex = i * 2;
        int rightIndex = i * 2 + 1;
        int minIndex = i;
        if(leftIndex < heap.length && heap[leftIndex] < heap[minIndex]){
            minIndex = leftIndex;
        }
        if(rightIndex < heap.length && heap[rightIndex] < heap[minIndex]){
            minIndex = rightIndex;
        }
        if(minIndex != i){
            swap(heap, minIndex, i);
            //keep heapifying
            minHeapify(heap, minIndex);
        }
    }

    private static void swap(int[] heap, int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }



    public static void main(String[] args){
        int[] heap = {-1, 4, 10, 14, 7, 9, 3, 2, 8, 1};
        //construct heap
        for(int i = heap.length / 2; i >= 1; -- i){
            maxHeapify(heap, i);
        }
        for(Integer i : heap){
            System.out.print(i + " ");
        }
        System.out.println();   //new line separator
        for(int i = heap.length / 2; i >= 1; -- i){
            minHeapify(heap, i);
        }
        for(Integer i : heap){
            System.out.print(i + " ");
        }
    }
}
