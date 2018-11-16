/*
* Binary search tree node with int data
* Implementation*/

import java.util.NoSuchElementException;

public class binaryNode {
    public binaryNode left;
    public binaryNode right;
    public int data;

    public binaryNode(){
        this.left = null;
        this.right = null;
        this.data = 0;
    }

    public binaryNode(int n){
        this.left = null;
        this.right = null;
        this.data = n;
    }

    /*set node data*/
    public void setData(int data){
        this.data = data;
    }

    /*get node data*/
    public int getData(){
        if(this == null){
            throw new NoSuchElementException("Node data has not been set!");
        }
        return this.data;
    }

    /*set left child*/
    public void setLeftChild(binaryNode left){
        this.left = left;
    }

    /*set right child*/
    public void setRightChild(binaryNode right){
        this.right = right;
    }

    /*get left child*/
    public binaryNode getLeftChild(){
        return this.left;
    }

    /*get right child*/
    public binaryNode getRightChild(){
        return this.right;
    }

    /*prints out node information.*/
    public String toString(){
        return "Data: " + Integer.toString(this.getData()) + " Left: " + Integer.toString(this.getLeftChild().getData()) + " Right: " + Integer.toString(this.getRightChild().getData());
    }


}
