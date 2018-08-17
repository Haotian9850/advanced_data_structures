import java.util.Arrays;
import java.util.NoSuchElementException;

/*
* Array-base min heap with int data. Heap root is set to the first (index = 0) element of the underlying array
* Implementation*/
public class minHeap {
    private final int n = 2;    //number of children for every heap node
    private int size;       //size of heap (AKA number of valid heap nodes)
    private int[] array;    //underlying array

    public minHeap(){
        this.size = 0;
        this.array = new int[0];
    }

    public minHeap(int capacity){
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

    /*find min value of the heap*/
    public int findMin(){
        if(isEmpty()){
            throw new NoSuchElementException("The heap is empty!");
        }
        return this.array[0];
    }

    /*standard deleteMin operation of a min heap*/
    public int deleteMin(){
        int result = this.array[0];
        delete(0);
        return result;
    }

    /*remove a specified index from the heap then restore all heap properties*/
    public int delete(int index){
        if(isEmpty()){
            throw new NoSuchElementException("The heap is empty!");
        }
        int toBeDeleted = this.array[index];
        this.array[index] = this.array[this.size - 1];
        this.size --;
        heapifyDown(index);
        return toBeDeleted;
    }

    /*display underlying array of a heap*/
    public void displayArray(){
        if(isEmpty()){
            throw new NoSuchElementException("The heap is empty!");
        }
        System.out.print("nodeValue: ");
        for(int i = 0; i < this.size; i ++){
            System.out.print(this.array[i] + " ");
        }
        System.out.println();
        System.out.print("index:     ");
        for(int i = 0; i < this.size; i ++){
            System.out.print(i + " ");
        }
        System.out.println();   //new line separator
    }

    /*construct a min heap from an int[]*/
    public void constructFromArray(int[] a){
        this.setCapacity(a.length);
        for(Integer i: a){
            this.insert(i);
        }
    }

    /*return true if a min heap has full size property*/
    public boolean isComplete(){
        int size = this.size;
        int minus = 1;
        while(size != 0){
            size -= minus;
            minus *= 2;
            if(size < 0){
                return false;
            }
        }
        return true;
    }

    /*visualization of an array-based min heap*/
    public void display(){
        if(!isComplete()){
            throw new IndexOutOfBoundsException("The heap is not complete and therefore cannot be visualized.");
        }
        int numberOfNodes = 2;
        int currIndex = 1;
        System.out.println(this.array[0]);
        while(currIndex < this.size){
            for(int i = currIndex; i < currIndex + numberOfNodes; i ++){
                System.out.print(this.array[i] + " ");
            }
            int temp = currIndex;
            currIndex += numberOfNodes;
            numberOfNodes *= 2;
            System.out.println();   //new line separator
        }
    }



}
