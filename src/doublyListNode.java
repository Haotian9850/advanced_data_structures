/*
* Doubly linked list node with int data
* Implementation
* */
public class doublyListNode {
    private int data;
    private doublyListNode next;
    private doublyListNode prev;

    public doublyListNode(){
        this.data = 0;
        this.next = null;
        this.prev = null;
    }

    public doublyListNode(int n){
        this.data = n;
        this.next = null;
        this.prev = null;
    }

    public doublyListNode(int n, doublyListNode next, doublyListNode prev){
        this.data = n;
        this.next = next;
        this.prev = prev;
    }

    /*set next node*/
    public void setNextNode(doublyListNode d){
        this.next = d;
    }

    /*set next node by providing node data*/
    public void setNextNode(int n){
        doublyListNode nextNode = new doublyListNode(n);
        this.next = nextNode;
    }

    /*set previous node*/
    public void setPrevNode(doublyListNode d){
        this.prev = d;
    }

    /*set previous node by providing node data*/
    public void setPrevNode(int n){
        doublyListNode prevNode = new doublyListNode(n);
        this.prev = prevNode;
    }

    /*get next node*/
    public doublyListNode getNextNode(){
        return this.next;
    }

    /*get previous node*/
    public doublyListNode getPrevNode(){
        return this.prev;
    }

    /*set node data*/
    public void setData(int n){
        this.data = n;
    }

    /*get node data*/
    public int getData(){
        return this.data;
    }
}
