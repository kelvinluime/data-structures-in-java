/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment:
 * Date:
 */
public class BinaryNode<T> {

    private T data;
    private BinaryNode<T> leftChild;
    private BinaryNode<T> rightChild;

    public BinaryNode(){
        this(null);
    }

    public BinaryNode(T dataPortion){
        this(dataPortion, null, null);
    }

    public BinaryNode(T data, BinaryNode<T> leftChild, BinaryNode<T> rightChild){
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public T getData(){
        return data;
    }

    public void setData(T data){
        this.data = data;
    }

    public BinaryNode<T> getLeftChild(){
        return leftChild;
    }

    public BinaryNode<T> getRightChild(){
        return rightChild;
    }

    public void setLeftChild(BinaryNode<T> leftChild){
        this.leftChild = leftChild;
    }

    public void setRightChild(BinaryNode<T> rightChild){
        this.rightChild = rightChild;
    }

    public boolean hasLeftChild(){
        return leftChild != null;
    }

    public boolean hasRightChild(){
        return rightChild != null;
    }

    public boolean isLeaf(){
        return !hasLeftChild() && !hasRightChild();
    }

    public int getNumberOfNodes(){
        return getNumberOfNodes(this);
    }

    private int getNumberOfNodes(BinaryNode<T> node){
        int leftNumber = 0;
        int rightNumber = 0;
        if(leftChild != null)
            leftNumber = leftChild.getNumberOfNodes();
        if(rightChild != null)
            rightNumber = rightChild.getNumberOfNodes();
        return 1 + leftNumber + rightNumber;
    }

    public int getHeight(){
        return getHeight(this);
    }

    private int getHeight(BinaryNode<T> node){
        int height = 0;
        if(node != null){
            height = 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
        }
        return height;
    }

    public BinaryNode<T> copy(){
        BinaryNode<T> newRoot = new BinaryNode<>(data);
        if(leftChild != null)
            newRoot.setLeftChild(leftChild.copy());
        if(rightChild != null)
            newRoot.setRightChild(rightChild.copy());
        return newRoot;
    }

}
