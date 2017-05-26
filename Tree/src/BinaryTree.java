/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment:
 * Date:
 */
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;
public class BinaryTree<T> implements BinaryTreeInterface<T>, Iterable{

    private BinaryNode<T> root;

    public BinaryTree(){
        root = null;
    }

    public BinaryTree(T rootData){
           root = new BinaryNode(rootData);
    }

    public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
        privateSetTree(rootData, leftTree, rightTree);
    }

    public T getRootData() {
        if(isEmpty())
           throw new EmptyTreeException();
        else
            return root.getData();
    }

    public int getHeight() {
        return root.getHeight();
    }

    public void setTree(T rootData) {
        root = new BinaryNode<>(rootData);
    }

    public int getNumberOfNodes() {
        int num;
        if(root == null)
            num = 0;
        else
            num = root.getNumberOfNodes();
        return num;
    }

    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
        privateSetTree(rootData, (BinaryTree<T>)leftTree, (BinaryTree<T>)rightTree);
    }

    public Iterator<T> getLeftIterator() {return new LeftIterator();}

    public Iterator<T> iterator() {
        return new InOrderIterator();
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        setTree(null);
    }

    protected void setRootData(T rootData){
        root.setData(rootData);
    }

    protected void setRootNode(BinaryNode<T> rootNode){
        root = rootNode;
    }

    protected BinaryNode<T> getRootNode(){
        return root;
    }

    private void privateSetTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
        root = new BinaryNode<>(rootData);
        if(leftTree != null && !leftTree.isEmpty()){
            root.setLeftChild(leftTree.root);
        }
        if(rightTree != null && !rightTree.isEmpty()){
            if(rightTree != leftTree)
                root.setRightChild(rightTree.root);
            else
                root.setRightChild(rightTree.root.copy());
        }
        if(leftTree != null && leftTree != this)
            leftTree.clear();
        if(rightTree != null && rightTree != this)
            rightTree.clear();
    }

    public class LeftIterator implements Iterator<T>{

        private BinaryNode<T> current;
        private Stack<BinaryNode<T>> stack;

        public LeftIterator(){
            current = root;
            stack = new Stack<>();
        }

        public boolean hasNext() {
            return !current.isLeaf();
        }

        public T next() {
            BinaryNode<T> nextNode;
            while(current != null){
                stack.push(current);
                if(current.hasLeftChild())
                    current = current.getLeftChild();
                else if(current.hasRightChild())
                    current = current.getRightChild();
            }
            if(!stack.isEmpty())
                nextNode = stack.pop();
            else
                throw new NoSuchElementException();
            return nextNode.getData();
        }

        public void remove() {
             throw new UnsupportedOperationException();
        }
    }

    public class InOrderIterator implements Iterator<T>{

        private Stack<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;

        public InOrderIterator(){
            nodeStack = new Stack<>();
            currentNode = root;
        }

        public boolean hasNext() {
            return !nodeStack.isEmpty() || currentNode != null;
        }

        public T next() {
            BinaryNode<T> nextNode;
            while(currentNode != null){
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftChild();
            }
            if(!nodeStack.isEmpty()){
                nextNode = nodeStack.pop();
                assert nextNode != null;
                currentNode = nextNode.getRightChild();
            } else {
                throw new NoSuchElementException();
            }
            return nextNode.getData();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
