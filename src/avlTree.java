import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/*
* AVL tree with int data
* Implementation*/
public class avlTree {
    private avlNode root;

    public avlTree(){
        this.root = null;
    }

    public avlTree(avlNode root){
        this.root = root;
    }

    /*is the tree empty?*/
    public boolean isEmpty(){
        return this.root == null;
    }

    /*clear all nodes*/
    public void cutDown(){
        this.root = null;
    }

    /*count number of nodes in an AVL tree*/
    public int numberOfNodes() {
        return numberOfNodes(this.root);
    }

    /*search for a node within a AVL tree*/
    public boolean search(int value){
        return search(this.root, value);
    }

    /*utility method of search*/
    public boolean search(avlNode root, int value){
        boolean result = false;
        while(root != null && !result){
            int rootValue = root.getData();
            if(rootValue < value){
                root = root.getRightChild();
            }else if(rootValue > value){
                root = root.getLeftChild();
            }else{
                result = true;
                break;
            }
            result = search(root, value);
        }
        return result;
    }

    /*utility method of numberOfNodes*/
    public int numberOfNodes(avlNode root){
        if(root == null){
            return 0;
        }else{
            int result = 1;
            result += numberOfNodes(root.getLeftChild());
            result += numberOfNodes(root.getRightChild());
            return result;
        }
    }

    /*return the larger of two nodes*/
    private int max(int left, int right){
        return left > right ? left : right;
    }

    /*insert a tree node*/
    public void insert(int value){
        this.root = insert(this.root, value);
    }

    /*special method: return height of a node even if it is null*/
    public int height(avlNode n){
        if (n == null){
            return -1;
        }else{
            return n.getHeight();
        }
    }

    /*utility method for insert*/
    private avlNode insert(avlNode t, int value){
        if(t == null){
            t = new avlNode(value);
        }else if(value < t.getData()){
            t.left = insert(t.left, value);
            if(height(t.getLeftChild()) - height(t.getRightChild()) == 2){
                if(value < t.getLeftChild().getData()){
                    //left-left
                    t = rotateLeft(t);
                }else{
                    //left-right
                    t = doubleRotateLeft(t);
                }
            }
        }else if(value > t.getData()){
            t.right = insert(t.right, value);
            if(height(t.getRightChild()) - height(t.getLeftChild()) == 2){
                if(value > t.getRightChild().getData()){
                    //right-right
                    t = rotateRight(t);
                }else{
                    //right-left
                    t = doubleRotateRight(t);
                }
            }
        }else{
            throw new IndexOutOfBoundsException("Duplicate. Cannot be inserted!");
        }
        t.height = max(height(t.getLeftChild()), height(t.getRightChild())) + 1;
        return t;
    }

    /*AVL Tree operations to maintain shape property:
    *   rotateRight: left-left
    *   rotateLeft: right-right
    *   doubleRotateRight:
    *   doubleRotateLeft
    *   */
    private avlNode rotateLeft(avlNode n){
        avlNode nLeft = n.left;
        n.left = nLeft.right;
        nLeft.right = n;
        n.height = max(height(n.getLeftChild()), height(n.getRightChild())) + 1;
        nLeft.height = max(height(nLeft.getLeftChild()), n.getHeight()) + 1;
        return nLeft;
    }

    private avlNode rotateRight(avlNode n){
        avlNode nRight = n.right;
        n.right = nRight.left;
        nRight.left = n;
        n.height = max(height(n.getLeftChild()), height(n.getRightChild())) + 1;
        nRight.height = max(height(nRight.getRightChild()), n.getHeight()) + 1;
        return nRight;
    }

    private avlNode doubleRotateRight(avlNode n){
        n.right = rotateLeft(n.right);
        return rotateRight(n);
    }

    private avlNode doubleRotateLeft(avlNode n){
        n.left = rotateRight(n.left);
        return rotateLeft(n);

    }

    /*methods to be implemented: traversal, constructFromArray, deletion, display*/

    /*standard in-order traversal of an AVL tree*/
    public void inOrderTraversal(){
        inOrderTraversal(this.root);
        System.out.println();   //new line separator
    }

    /*utility method of inOrderTraversal*/
    private void inOrderTraversal(avlNode t){
        if(t != null){
            inOrderTraversal(t.getLeftChild());
            System.out.print(t.getData() + " ");
            inOrderTraversal(t.getRightChild());
        }
    }

    /*standard pre-order traversal of an AVL tree*/
    public void preOrderTraversal(){
        preOrderTraversal(this.root);
        System.out.println();   //new line separator
    }

    /*utility method of preOrderTraversal*/
    private void preOrderTraversal(avlNode t){
        if(t != null){
            System.out.print(t.getData() + " ");
            preOrderTraversal(t.getLeftChild());
            preOrderTraversal(t.getRightChild());
        }
    }

    /*standard post-order traversal of an AVL tree*/
    public void postOrderTraversal(){
        postOrderTraversal(this.root);
        System.out.println();   //new line separator
    }

    /*utility method for postOrderTraversal*/
    private void postOrderTraversal(avlNode t){
        if(t != null){
            postOrderTraversal(t.getLeftChild());
            postOrderTraversal(t.getRightChild());
            System.out.print(t.getData() + " ");
        }
    }

    /*construct an AVL tree from an int[]*/
    public void constructFromArray(int[] a){
        this.root = constructFromArrayUtility(a);
    }

    /*utility method of constructFromArray*/
    private avlNode constructFromArrayUtility(int[] a){
        avlNode newRoot = new avlNode(a[0]);
        avlTree newTree = new avlTree(newRoot);
        for(int i = 1; i < a.length; i ++){
            newTree.insert(a[i]);
        }
        return newTree.root;
    }

    /*visualization of a binary search tree - basically a level order traversal*/
    public void display(){
        ArrayList<ArrayList<Integer>> result = levelOrderTraversal(this.root);

        for(ArrayList<Integer> a: result){
            for(Integer i: a){
                System.out.print(i + " ");
            }
            System.out.println();   //new line separator
        }
    }


    /*utility method of display*/
    private ArrayList<ArrayList<Integer>> levelOrderTraversal(avlNode treeRoot){
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> nodeValues = new ArrayList<Integer>();
        if(isEmpty()){
            throw new NoSuchElementException("The tree is empty!");
        }

        LinkedList<avlNode> currentLevel = new LinkedList<avlNode>();
        LinkedList<avlNode> nextLevel = new LinkedList<avlNode>();
        currentLevel.add(treeRoot);

        while(!currentLevel.isEmpty()){
            avlNode theNode = currentLevel.remove();
            if(theNode.getLeftChild() != null){
                nextLevel.add(theNode.getLeftChild());
            }
            if(theNode.getRightChild() != null){
                nextLevel.add(theNode.getRightChild());
            }
            nodeValues.add(theNode.getData());
            if(currentLevel.isEmpty()){
                currentLevel = nextLevel;   //move down
                nextLevel = new LinkedList<avlNode>();
                result.add(nodeValues);
                nodeValues = new ArrayList<Integer>();
            }
        }
        return result;
    }
}
