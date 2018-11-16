/*
* Singly linked list with int data
* Implementation
* */
public class singlyLinkedList {
    private singlyListNode head;
    private singlyListNode tail;
    public int listSize;

    public singlyLinkedList(){
        this.head = null;
        this.tail = null;
        listSize = 0;
    }

    /*Is this list empty?*/
    public boolean isEmpty(){
        return this.listSize == 0;
    }

    /*get list siz*/
    public int getSize(){
        return this.listSize;
    }

    /*insert a node at list beginning*/
    public void insertAtHead(singlyListNode n){
        if (this.head == null){
            this.head = n;
            this.tail = this.head;
        }else{
            n.setNextNode(this.head);
            this.head = n;
        }
        this.listSize ++;   //increment list capacity
    }

    /*insert a node at list beginning by providing node value*/
    public void insertAtHead(int n){
        singlyListNode newNode = new singlyListNode(n, null);
        if (this.head == null){
            this.head = newNode;
            this.tail = this.head;
        }else{
            newNode.setNextNode(this.head);
            this.head = newNode;
        }
        this.listSize ++;
    }

    /*insert a node at list end*/
    public void insertAtTrail(singlyListNode n){
        if (this.head == null){
            this.head = n;
            this.tail = this.head;
        }else{
            this.tail.setNextNode(n);
            this.tail = n;
        }
        this.listSize ++;
    }

    /*append a node to list end by providing node value*/
    public void insertAtTail(int n){
        singlyListNode newNode = new singlyListNode(n, null);
        if (this.head == null){
            this.head = newNode;
            this.tail = this.head;
        }else{
            this.tail.setNextNode(newNode);
            this.tail = newNode;
        }
        this.listSize ++;
    }

    /*insert a node at certain list position*/
    public void insertAtPos(singlyListNode n, int position){
        singlyListNode ptr = this.head;
        int previous = position - 1;
        //find insertion position
        for (int i = 0 ; i < this.listSize; i ++){
            if (i == position){
                singlyListNode temp = ptr.getNextNode();
                ptr.setNextNode(n);
                n.setNextNode(temp);
                break;
            }
            ptr = ptr.getNextNode();
        }
        this.listSize ++;
    }

    /*insert a node at certain list position by providing node value*/
    public void insertAtPos(int val, int position){
        singlyListNode n = new singlyListNode(val, null);
        singlyListNode ptr = this.head;
        int previous = position - 1;
        //find insertion position
        for (int i = 0 ; i < this.listSize; i ++){
            if (i == position){
                singlyListNode temp = ptr.getNextNode();
                ptr.setNextNode(n);
                n.setNextNode(temp);
                break;
            }
            ptr = ptr.getNextNode();
        }
        this.listSize ++;
    }

    /*delete a node at certain list position*/
    public void deleteFromPos(int position){
        if (position == 0){
            //delete at head
            this.head = this.head.getNextNode();
            this.listSize --;
            return;
        }else if(position == this.listSize - 1){
            singlyListNode ptr = this.head;
            singlyListNode previous = this.head;
            while(ptr != this.tail){
                previous = ptr;
                ptr = ptr.getNextNode();
            }
            this.tail = previous;
            this.tail.setNextNode(null);    //delink original tail
            this.listSize --;
            return;
        }
        singlyListNode ptr = this.head;
        int previous = position - 1;
        for (int i = 1; i < this.listSize; i ++){
            if (i == previous){
                //when ptr reaches previous-the NEXT node is to be removed from the list
                singlyListNode temp = ptr.getNextNode();
                temp = temp.getNextNode();
                ptr.setNextNode(temp);
                break;
            }
            ptr = ptr.getNextNode();
        }
        this.listSize --;
    }

    /*construct a singly linked list from an int[] (int[] must not be null)*/
    public singlyListNode constructFromArray(int[] a){
        this.head = new singlyListNode(a[0], null);
        singlyListNode ptr = this.head;
        this.listSize = 1;
        for (int i = 1; i < a.length; i ++){
            ptr.setNextNode(a[i]);
            ptr = ptr.getNextNode();
            this.listSize ++;
        }
        this.tail = ptr;
        return this.head;
    }

    /*display all list content*/
    public void display(){
        if (this.listSize == 0){
            System.out.println("This is an empty list!");
            return;
        }else if(this.head.getNextNode() == null){
            System.out.print(this.head.getData());    //single-node list
            return;
        }
        System.out.print(this.head.getData() + " -> ");   //print out head node
        singlyListNode ptr = this.head.getNextNode();
        while(ptr.getNextNode() != null){
            System.out.print(ptr.getData() + " -> ");
            ptr = ptr.getNextNode();
        }
        //when ptr reaches second last node
        System.out.println(ptr.getData());
    }



}
