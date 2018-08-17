/*Binary search tree with int nodes
* Implementation*/

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class binarySearchTree {
    private binaryNode root;

    public binarySearchTree(){
        this.root = null;
    }

    public binarySearchTree(binaryNode root){
        this.root = root;
    }

    /*Is the tree empty?*/
    public boolean isEmpty(){
        return this.root == null;
    }

    /*insert a node with int data into the tree*/
    public void insert(binaryNode n){
        insertNode(this.root, n);
    }

    /*insert a node with int data into the tree by providing node data*/
    public void insert(int n){
        this.root = insertValue(this.root, n);
    }

    /*utility method for inserting a node*/
    private binaryNode insertNode(binaryNode treeRoot, binaryNode newNode){
        if(treeRoot == null){
            treeRoot = newNode;
        }else{
            if(newNode.getData() <= treeRoot.getData()){
                treeRoot.left = insertNode(treeRoot.getLeftChild(), newNode);
            }else{
                treeRoot.right = insertNode(treeRoot.getRightChild(), newNode);
            }
        }
        return treeRoot;
    }

    /*utility method for inserting a value*/
    private binaryNode insertValue(binaryNode treeRoot, int n){
        if(treeRoot == null){
            treeRoot = new binaryNode(n);
        }else{
            if(n <= treeRoot.getData()){
                treeRoot.left = insertValue(treeRoot.getLeftChild(), n);
            }else{
                treeRoot.right = insertValue(treeRoot.getRightChild(), n);
            }
        }
        return treeRoot;
    }

    /*delete a specified value from a binary search tree*/
    public void delete(int value){
        if(isEmpty()){
            throw new NoSuchElementException("Empty tree, nothing's deleted!");
        }else if(!search(value)){
            throw new NoSuchElementException("Value not fonud, nothing's deleted!");
        }else{
            this.root = deleteValue(this.root, value);
            System.out.println(value + " has been removed from the tree!");
        }
    }

    /*utility method of deleting a value from a binary search tree*/
    private binaryNode deleteValue(binaryNode subTreeRoot, int value) {
        binaryNode parent;
        binaryNode temp;
        binaryNode n;
        if(subTreeRoot.getData() == value){
            binaryNode leftChild = subTreeRoot.getLeftChild();
            binaryNode rightChild = subTreeRoot.getRightChild();
            if(leftChild == null && rightChild == null){
                return null;
            }else if(leftChild == null){
                parent = rightChild;
                return parent;
            }else if(rightChild == null){
                parent = leftChild;
                return parent;
            }else{
                temp = rightChild;
                parent = rightChild;
                while(parent.getLeftChild() != null){
                    parent = parent.getLeftChild();
                }
                parent.setLeftChild(leftChild);
                return temp;
            }
        }
        if(value < subTreeRoot.getData()){
            n = deleteValue(subTreeRoot.getLeftChild(), value);
            subTreeRoot.setLeftChild(n);
        }else{
            n = deleteValue(subTreeRoot.getRightChild(), value);
            subTreeRoot.setRightChild(n);
        }
        return subTreeRoot;
    }

    /*count number of nodes in a binary search tree*/
    public int countNodes(){
        return countNodes(this.root);
    }

    /*utility method to count number of nodes in a binary search tree*/
    private int countNodes(binaryNode treeRoot){
        //base case for recursive approach
        if(treeRoot == null){
            return 0;
        }else{
            int temp = 1;   //count in root
            temp += countNodes(treeRoot.getLeftChild());
            temp += countNodes(treeRoot.getRightChild());
            return temp;
        }
    }

    /*search for a specified node within a binary search tree*/
    public boolean search(int value){
        return search(this.root, value);
    }

    /*utility method for searching a specified node within a binary search tree*/
    private boolean search(binaryNode treeRoot, int value){
        boolean result = false;
        binaryNode ptr = treeRoot;
        while(treeRoot != null && !result){
            int rootValue = treeRoot.getData();
            if(rootValue > value){
                ptr = ptr.getLeftChild();
            }else if(value > rootValue){
                ptr = ptr.getRightChild();
            }else{
                result = true;
                break;
            }
            result = search(ptr, value);
        }
        return result;
    }

    /*standard in-order traversal of a binary search tree*/
    public void inOrderTraversal(){
        inOrderTraversal(this.root);
        System.out.println();
    }

    /*utility method of in-order traversal of a binary search tree*/
    private void inOrderTraversal(binaryNode treeRoot){
        if(treeRoot != null){
            inOrderTraversal(treeRoot.getLeftChild());
            System.out.print(treeRoot.getData() + " ");
            inOrderTraversal(treeRoot.getRightChild());
        }
    }

    /*standard pre-order traversal of a binary search tree*/
    public void preOrderTraversal(){
        preOrderTraversal(this.root);
        System.out.println();   //new line separator
    }

    /*utility method of pre-order traversal of a binary search tree*/
    private void preOrderTraversal(binaryNode treeRoot){
        if(treeRoot != null){
            System.out.print(treeRoot.getData() + " ");
            preOrderTraversal(treeRoot.getLeftChild());
            preOrderTraversal(treeRoot.getRightChild());
        }
    }

    /*standard post-order traversal of a binary search tree*/
    public void postOrderTraversal(){
        postOrderTraversal(this.root);
        System.out.println();
    }

    /*utility method of post-order traversal of a binary search tree*/
    private void postOrderTraversal(binaryNode treeRoot){
        if(treeRoot != null){
            postOrderTraversal(treeRoot.getLeftChild());
            postOrderTraversal(treeRoot.getRightChild());
            System.out.print(treeRoot.getData() + " ");
        }
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
    private ArrayList<ArrayList<Integer>> levelOrderTraversal(binaryNode treeRoot){
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> nodeValues = new ArrayList<Integer>();
        if(isEmpty()){
            throw new NoSuchElementException("The tree is empty!");
        }

        LinkedList<binaryNode> currentLevel = new LinkedList<binaryNode>();
        LinkedList<binaryNode> nextLevel = new LinkedList<binaryNode>();
        currentLevel.add(treeRoot);

        while(!currentLevel.isEmpty()){
            binaryNode theNode = currentLevel.remove();
            if(theNode.getLeftChild() != null){
                nextLevel.add(theNode.getLeftChild());
            }
            if(theNode.getRightChild() != null){
                nextLevel.add(theNode.getRightChild());
            }
            nodeValues.add(theNode.getData());
            if(currentLevel.isEmpty()){
                currentLevel = nextLevel;   //move down
                nextLevel = new LinkedList<binaryNode>();
                result.add(nodeValues);
                nodeValues = new ArrayList<Integer>();
            }
        }
        return result;
    }

    /*construct a binary search tree from an int[]*/
    public void constructFromArray(int[] a){
        this.root = constructFromArrayUtility(a);
    }

    /*utility method of constructing a binary search tree from an int[]*/
    public binaryNode constructFromArrayUtility(int[] a){
        binaryNode newRoot = new binaryNode(a[0]);  //set root value
        binarySearchTree newTree = new binarySearchTree(newRoot);
        for(int i = 1; i < a.length; i ++){
            newTree.insert(a[i]);
        }
        return newTree.root;
    }
}
