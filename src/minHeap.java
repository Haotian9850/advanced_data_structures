import java.util.Arrays;

/*
* Array-base max heap with int data. Heap root is set to the first (index = 0) element of the underlying array
* Implementation*/
public class maxHeap {
    private final int n = 2;    //number of children for every heap node
    private int size;       //size of heap (AKA number of valid heap nodes)
    private int[] array;    //underlying array

    public maxHeap(){
        this.size = 0;
        this.array = new int[0];
    }

    public maxHeap(int capacity){
        this.size = 0;
        this.array = new int[capacity];
        Arrays.fill(this.array, -1);    //initialize underlying array with placeholder values (-1)
    }

    /*Is this heap empty?*/
    public boolean isEmpty(){
        return this.size == 0;
    }

    /*Is this heap full?*/
    public boolean isFull(){
        return this.size == this.array.length;  //count size from 1!
    }

    /*set heap capacity. Underlying array will be restored.*/
    public void setCapacity(int capacity){
        this.array = new int[capacity];
        Arrays.fill(this.array, -1);    //placeholder value -1
    }

    /*restore a heap*/
    public void restore(){
        this.array = new int[this.array.length];
        Arrays.fill(this.array, -1);
        this.size = 0;
    }

    /*get parent index of a heap node*/
    public int getParent(int i){
        return (i - 1) / n;
    }

    /*get index of kth child of a heap node*/
    public int getKthChild(int index, int k){
        return index * n + k;
    }

    /*heapify up operation to maintain heap property*/
    public void heapifyUp(int i){
        int temp = this.array[i];
        while(i > 0 && temp < this.array[getParent(i)]){
            //swap
            this.array[i] = this.array[getParent(i)];
            i = getParent(i);
        }
        this.array[i] = temp;
    }

    /*heapify down operation to maintain heap property*/
    public void heapifyDown(int index){
        int child;
        int temp = this.array[index];
        while(getKthChild(index, 1) < this.array.length){
            child = minChild(index);
            if(this.array[child] < temp){
                //swap
                this.array[index] = this.array[child];
            }else{
                break;  //heap property is restored
            }
            index = child;
        }
        this.array[index] = temp;
    }

    /*get the index of smallest child of a heap node*/
    public int minChild(int index){
        int resultIndex = getKthChild(index, 1);
        int  k = 2;
        int position = getKthChild(index, k);
        while(k <= n && position < this.size){
            if(this.array[position] < this.array[resultIndex]){
                resultIndex = position;
            }
            position = getKthChild(index, k ++);
        }
        return resultIndex;
    }

    /*standard insert operation of a heap*/
    public void insert(int value){
        if(isFull()){
            throw new IndexOutOfBoundsException("The heap is already full!");
        }
        this.array[this.size ++] = value;
        heapifyUp(this.size - 1);   //perform heapify up on the node just inserted
    }



}
