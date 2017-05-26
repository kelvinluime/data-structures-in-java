/**
 * Student Name: Kin Man Lui (Kelvin)
 * Instructor: Professor Schatz
 * Course: CS111C-001
 * Assignment:
 * Date:
 */
public interface BinaryTreeInterface<T> extends TreeInterface<T> {

    void setTree(T rootData);

    void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree);

}
