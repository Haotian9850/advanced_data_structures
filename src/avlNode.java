/*
* AVL tree node with int data
* Implementation*/
public class avlNode {
    public avlNode left;
    public avlNode right;
    private int data;
    public int height;

    public avlNode() {
        this.left = null;
        this.right = null;
        this.data = 0;
        this.height = 0;
    }

    public avlNode(int data){
        this.left = null;
        this.right = null;
        this.data = data;
        this.height = 0;
    }

    /*get left child of an AVL node*/
    public avlNode getLeftChild(){
        return this.left;
    }

    /*get right child if an AVL node*/
    public avlNode getRightChild(){
        return this.right;
    }

    /*get node data*/
    public int getData(){
        return this.data;
    }

    /*get node height*/
    public int getHeight(){
        return this.height;
    }

    /*set node data*/
    public void setData(int data){
        this.data = data;
    }


}
