import java.util.NoSuchElementException;

/*
* Stack with int value
* Implementation*/
public class stack {
    protected int[] array;
    protected int top;  //only retains top pointer, count from zero
    protected int capacity;
    protected int size;

    public stack(){
        this.capacity = 0;
        this.size = 0;
        this.array = new int[this.capacity];
        this.top = -1;
    }

    public stack(int n){
        this.capacity = n;
        this.size = 0;
        this.array = new int[n];
        this.top = -1;
    }

    /*Is the stack empty?*/
    public boolean isEmpty(){
        return top == -1;
    }

    /*get stack len(not capacity!)*/
    public int getSize(){
        return this.capacity;
    }

    /*get stack's designed capacity*/
    public int getCapacity(){
        return this.capacity;
    }

    /*Is the stack full (count from zero)?*/
    public boolean isFull(){
        return this.top == this.capacity - 1;
    }

    /*standard peek operation of a stack*/
    public int peek(){
        if(isEmpty()){
            throw new NoSuchElementException("Stack is empty!");
        }
        return this.array[this.top];
    }

    /*standard pop operation of a stack*/
    public void pop(){
        if(isEmpty()){
            throw new NoSuchElementException("Stack is empty!");
        }
        this.array[this.top] = 0;   //zero refers to empty spot, as an array will be filled with zero when first declared
        this.top --;    //decrement top pointer !important
        this.size --;
    }

    /*standard push operation of a stack*/
    public void push(int n){
        if(isFull()){
            throw new IndexOutOfBoundsException("Stack is full!");
        }
        this.top ++;
        this.array[this.top] = n;
        this.size ++;   //increment stack len
    }

    /*visualization of a stack*/
    public void display(){
        if(isEmpty()){
            System.out.println("Stack is empty!");
        }
        for(int i = this.capacity - 1; i > this.top; i --){
            System.out.println(this.array[i]);
        }
        System.out.println(this.array[this.top] + " <-top");
        for(int i = this.top - 1; i >= 0; i --){
            System.out.println(this.array[i]);
        }
    }

    /*construct a stack from a int[]. Returned stack will be a full stack.*/
    public void constructFromArray(int[] a){
        if(this.capacity < a.length){
            throw new IndexOutOfBoundsException("Stack is of insufficient capacity!");
        }
        for(int i = 0; i < a.length; i ++){
            this.push(a[i]);
        }
    }
}
