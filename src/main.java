public class main {
    /*main class for data structure testing*/
    public static void main(String[] args){
        /*testing singly linked list*/

        System.out.println("--------------------------------- singlyLinkedList test---------------------------------");
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        singlyLinkedList s = new singlyLinkedList();
        s.constructFromArray(a);
        s.display();
        s.insertAtHead(0);
        s.display();
        s.insertAtTail(13);
        s.display();
        s.deleteFromPos(4);   //has a problem!
        s.display();
        s.insertAtPos(4, 4);    //working properly
        s.display();
        System.out.println(s.getSize());
        System.out.println(s.isEmpty());

        singlyLinkedList s1 = new singlyLinkedList();
        System.out.println(s1.isEmpty());
        s1.insertAtTail(900);
        s1.display();
        s1.deleteFromPos(0);
        s1.display();

        System.out.println("--------------------------------- doublyLinkedList test---------------------------------");
        int[] b = new int[]{1, 2, 3, 4, 5};
        doublyLinkedList d = new doublyLinkedList();
        d.constructFromArray(b);
        d.display();
        d.insertAtTail(6);
        d.display();
        d.insertAtHead(0);
        d.display();
        d.insertAtPos(4, 3);
        d.display();
        d.deleteFromPos(3);
        d.display();
        System.out.println(d.isEmpty());
        doublyLinkedList d1 = new doublyLinkedList();
        System.out.println(d1.isEmpty());
        d1.insertAtTail(1);
        d1.display();
        d1.deleteFromPos(0);
        d1.display();
        d1.insertAtHead(7);
        d1.display();
        d1.insertAtHead(8);
        d1.display();

        System.out.println("--------------------------------- stack test---------------------------------");
        int[] c = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        stack testStack = new stack(9); //capacity == 9
        testStack.constructFromArray(c);
        testStack.display();
        testStack.push(9);
        testStack.display();
        System.out.println(testStack.isEmpty());
        System.out.println(testStack.isFull());
        System.out.println(testStack.peek());
        testStack.pop();
        testStack.display();    //works!

        System.out.println("--------------------------------- queue test---------------------------------");
        int[] e = new int[]{1, 2, 3, 4, 5};
        queue q = new queue(9);
        q.constructFromArray(e);
        q.display();
        System.out.println(q.peek());
        q.poll();
        System.out.println(q.isEmpty());
        System.out.println(q.isFull());
        q.display();
        q.insert(6);
        System.out.println(q.isFull());
        q.display();

        System.out.println("--------------------------------- binarySearchTree test---------------------------------");
        binarySearchTree g = new binarySearchTree();
        int[] f = new int[]{9, 5, 1, 2, 4, 7, 8, 3, 12, 11};
        g.constructFromArray(f);
        System.out.println(g.isEmpty());
        g.display();
        g.insert(29);
        System.out.println(g.countNodes());
        g.display();
        System.out.println(g.isEmpty());
        g.delete(9);
        g.delete(1);
        g.display();

        g.inOrderTraversal();
        g.preOrderTraversal();
        g.postOrderTraversal();

    }
}
