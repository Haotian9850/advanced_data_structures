/*
* Doubly linked list with int data
* Implementation*/
public class doublyLinkedList {
    protected doublyListNode head;
    protected doublyListNode tail;
    public int listSize;

    public doublyLinkedList(){
        this.head = null;
        this.tail = null;
        this.listSize = 0;
    }

    /*Is the list empty?*/
    public boolean isEmpty(){
        return this.listSize == 0;
    }

    /*get capacity of a list*/
    public int getSize(){
        return this.listSize;
    }

    /*insert node at list beginning*/
    public void insertAtHead(doublyListNode d){
        if (this.head == null){
            this.head = d;
            this.tail = this.head;
        }else{
            this.head.setPrevNode(d);
            d.setNextNode(this.head);
            this.head = d;
        }
        this.listSize ++;
    }

    /*insert node at list beginning by providing node data*/
    public void insertAtHead(int n){
        doublyListNode newNode = new doublyListNode(n);
        if (this.head == null){
            this.head = newNode;
            this.tail = this.head;
        }else{
            this.head.setPrevNode(newNode);
            newNode.setNextNode(this.head);
            this.head = newNode;
        }
        this.listSize ++;
    }

    /*append node to list tail*/
    public void insertAtTail(doublyListNode d){
        if(this.head == null){
            this.head = d;
            this.tail = this.head;
        }else{
            d.setPrevNode(this.tail);
            this.tail.setNextNode(d);
            this.tail = d;
        }
        this.listSize ++;
    }

    /*append node to list tail by providing list data*/
    public void insertAtTail(int n){
        doublyListNode newNode = new doublyListNode(n, null, null);
        if (this.head == null){
            this.head = newNode;
            this.tail = this.head;
        }else{
            newNode.setPrevNode(this.tail);
            this.tail.setNextNode(newNode);
            this.tail = newNode;
        }
        this.listSize ++;
    }

    /*insert a node at certain list position*/
    public void insertAtPos(doublyListNode d, int position){
        if(position == 0){
            insertAtHead(d);
            return;
        }
        doublyListNode ptr = this.head;
        for(int i = 1; i < this.listSize; i ++){
            if (i == position){
                doublyListNode temp = ptr.getNextNode();
                ptr.setNextNode(d);
                d.setPrevNode(ptr);
                d.setNextNode(temp);
                temp.setPrevNode(d);
            }
            ptr = ptr.getNextNode();    //move pointer forward until last node before position
        }
        this.listSize ++;
    }

    /*insert a node at certain list position by providing node data*/
    public void insertAtPos(int data, int position){
        if (position == 0){
            insertAtHead(data);
            return;
        }
        doublyListNode newNode = new doublyListNode(data);
        doublyListNode ptr = this.head;
        for(int i = 1; i < this.listSize; i ++){
            if (i == position){
                doublyListNode temp = ptr.getNextNode();
                ptr.setNextNode(newNode);
                newNode.setPrevNode(ptr);
                newNode.setNextNode(temp);
                temp.setPrevNode(newNode);
                break;
            }
            ptr = ptr.getNextNode();    //move pointer forward until last node before position
        }
        this.listSize ++;
    }

    /*delete a node from certain list position*/
    public void deleteFromPos(int position){
        if(position == 0){
            if(this.listSize == 1){
                this.head = null;
                this.tail = null;
                this.listSize = 0;
                return;
            }
            this.head = this.head.getNextNode();
            this.head.setPrevNode(null);
            this.listSize --;
            return;
        }
        if (position == this.listSize - 1){
            this.tail = this.tail.getPrevNode();
            this.tail.setNextNode(null);
            this.listSize --;
            return;
        }
        doublyListNode ptr = this.head.getNextNode();
        for(int i = 1; i < this.listSize; i ++){
            if(i == position){
                //when pointer is to be removed from the list
                doublyListNode previous = ptr.getPrevNode();
                doublyListNode next = ptr.getNextNode();
                previous.setNextNode(next);
                next.setPrevNode(previous);
                this.listSize --;
                break;
            }
            ptr = ptr.getNextNode();
        }
    }

    /*print out all contents of a list*/
    public void display(){
        if(this.listSize == 0){
            System.out.println("This is an empty list!");
            return;
        }
        //single-element list
        if (this.head.getNextNode() == null){
            System.out.println(this.head.getData());
            return;
        }
        doublyListNode ptr = this.head;
        System.out.print(ptr.getData() + " <=> ");
        ptr = ptr.getNextNode();
        while(ptr.getNextNode() != null){
            System.out.print(ptr.getData() + " <=> ");
            ptr = ptr.getNextNode();
        }
        System.out.println(ptr.getData());    //print out list tail
    }

    /*construct a doubly linked list from a int[]*/
    public doublyListNode constructFromArray(int[] a){
        this.head = new doublyListNode(a[0]);
        doublyListNode ptr = this.head;
        this.tail = this.head;
        this.listSize = 1;
        for(int i = 1; i < a.length; i ++){
            this.insertAtTail(a[i]);
            ptr = ptr.getNextNode();
            this.listSize ++;
        }
        this.tail = ptr;
        return this.head;   //return list head node
    }

}
