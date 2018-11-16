/*
* Singly linked list node with int data
* Implementation
* */
public class singlyListNode {
    private singlyListNode next;
    private int data;

    public singlyListNode(){
        this.next = null;
        this.data = 0;
    }

    public singlyListNode(int i, singlyListNode n){
        this.next = n;
        this.data = i;
    }

    /*set next node*/
    public void setNextNode(singlyListNode n){
        this.next = n;
    }

    /*set next node by providing node value*/
    public void setNextNode(int n){
        singlyListNode nextNode = new singlyListNode(n, null);
        this.next = nextNode;
    }

    /*get next node*/
    public singlyListNode getNextNode(){
        return this.next;
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
