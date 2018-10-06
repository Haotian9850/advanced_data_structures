/*
* Array-base min heap with int data. Heap root is set to the first (index = 0) element of the underlying array
* Implementation: underlying array uses a dummy head set to Integer.MIN_VALUE.*/
public class minHeap {
    private int[] array;
    private int size;
    private int capacity;

    public minHeap(){
        this.array = new int[100];
        this.size = 0;
        this.capacity = 100;
    }

    public minHeap(int capacity){
        this.array = new int[capacity + 1];
        this.size = 0;
        this.capacity = capacity;
        this.array[0] = Integer.MIN_VALUE;
    }

    /*get parent index of a heap "node" */
    private int getParent(int index){
        return index / 2;
    }

    /*get left child index of a heap "node"*/
    private int getLeftChild(int index){
        return 2 * index;
    }

    /*get right child index of a heap "node"*/
    private int getRightChild(int index){
        return 2 * index + 1;
    }

    /*Is the node a "leaf" in the heap?*/
    private boolean isLeaf(int index){
        if(index >= this.size / 2 && index < this.size){
            return true;
        }
        return false;
    }

    /*maintain heap property*/
    public void heapify(int index){
        if(!isLeaf(index)){
            if (this.array[index] > this.array[getLeftChild(index)] || this.array[index] > this.array[getRightChild(index)]){
                if(this.array[getLeftChild(index)] < this.array[getRightChild(index)]){
                    swap(this.array, index, getLeftChild(index));
                    heapify(getLeftChild(index));
                }else{
                    swap(this.array, index, getRightChild(index));
                    heapify(getRightChild(index));
                }
            }
        }
    }

    /*delete and return "root" of the heap. Application: heap sort*/
    public int peek(){
        int result = this.array[1];
        this.array[1] = this.array[this.size --];
        heapify(1);
        return result;
    }

    /*insert an int into the heap*/
    public void insert(int n){
        if(this.size + 1 > this.capacity){
            throw new IndexOutOfBoundsException("Insufficient heap space! Try re-declare a larger heap!");
        }
        this.array[this.size ++] = n;
        int curr = this.size;
        while(this.array[curr] < this.array[getParent(curr)]){
            swap(this.array, curr, getParent(curr));
            curr = getParent(curr); //update curr ptr
        }
    }

    /*swap two element in an int[]*/
    private void swap(int[] a, int i, int j){
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }

    /*construct a min heap from an int[]*/
    public void constructFromArray(int[] a){
        if(this.capacity < a.length){
            throw new IndexOutOfBoundsException("Insufficient heap space! Try re-declare a larger heap of capacity " + a.length + 1 + " !");
        }
        for(Integer i: a){
            insert(i);
        }
    }

    /*print out underlying array*/
    public void printUnderlyingArray(){
        System.out.println();   //new line separator
        for(Integer i: this.array){
            System.out.print(i + " ");
        }
        System.out.println();   //new line separator
    }


    /*visualization of an array-based min heap*/
    public void display(){
        try{
            int temp = -1;
            int numberOfNodes = 2;
            int currIndex = 1;
            System.out.println(this.array[1]);
            while(currIndex < this.size) {
                for (int i = currIndex; i < currIndex + numberOfNodes; i++) {
                    System.out.print(this.array[i] + " ");
                }
                temp = currIndex;
                currIndex += numberOfNodes;
                numberOfNodes *= 2;
                System.out.println();   //new line separator
            }
        }catch(IndexOutOfBoundsException e){
            //do nothing! -weird exception!
        }
    }

    /*utility function for display*/
    public void printLastLine(int temp){
        //print rest of the underlying array
        for(int i = temp + 1; i < this.size; i ++){
            System.out.print(this.array[i]);
        }
    }



}
