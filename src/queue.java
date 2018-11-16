import java.util.NoSuchElementException;

/*
* Queue with int value
* Implementation*/
public class queue {
    protected int[] array;
    protected int front;    //keep track of both front and end pointers, count from zero!
    protected int end;
    protected int capacity;
    protected int len;

    public queue(){
        this.array = new int[0];
        this.capacity = 0;
        this.len = 0;
        this.front = -1;
        this.end = -1;
    }

    public queue(int n){
        this.array = new int[n];
        this.capacity = n;
        this.len = 0;
        this.front = -1;
        this.end = -1;
    }

    /*Is the queue empty?*/
    public boolean isEmpty(){
        return this.len == 0 || this.front == -1;
    }

    /*Is the queue full?*/
    public boolean isFull(){
        return this.front == 0 && this.end == this.capacity - 1;
    }

    /*get length of the queue*/
    public int getLength(){
        return this.len;
    }

    /*get capacity of the queue*/
    public int getCapacity(){
        return this.capacity;
    }

    /*set capacity of a queue*/
    public void setCapacity(int n){
        this.capacity  = n;
        this.array = new int[n];
    }

    /*standard peek operation of a queue*/
    public int peek(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty!");
        }
        return this.array[this.front];
    }

    /*standard poll operation of a queue*/
    public int poll(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty!");
        }else{
            int result = this.array[this.front];
            this.array[this.front] = 0; //reset empty spot with zero
            this.len --;
            if(this.front == this.end){
                //single-element queue
                this.front = -1;
                this.end = -1;
            }else{
                this.front ++;
            }
            return result;
        }
    }

    /*standard insert operation of a queue*/
    public void insert(int n){
        if(this.end == -1){
            //if the queue is empty
            this.front = 0;
            this.end = this.front;
            this.array[this.front] = n;
        }else if(this.end +1 == this.capacity){
            throw new IndexOutOfBoundsException("The queue is full!");
        }else if(this.end + 1 < this.capacity){
            this.end ++;
            this.array[this.end] = n;
        }
        this.len ++;
    }

    /*visualization of a queue*/
    public void display(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty!");
        }
        for(int i = 0; i < this.front; i ++){
            System.out.println(this.array[i]);
        }
        System.out.println(this.array[this.front] + "<-front");
        for(int i = this.front + 1; i < this.end; i ++){
            System.out.println(this.array[i]);
        }
        System.out.println(this.array[this.end] + "<-end");
        for(int i = this.end + 1; i < this.capacity; i ++){
            System.out.println(this.array[i]);
        }
    }

    /*construct a queue from a int[]*/
    public void constructFromArray(int[] a){
        if(this.capacity < a.length){
            throw new IndexOutOfBoundsException("The queue is of insufficient capacity!");
        }
        for(int i = 0; i < a.length; i ++){
            this.insert(a[i]);
        }
    }


}
