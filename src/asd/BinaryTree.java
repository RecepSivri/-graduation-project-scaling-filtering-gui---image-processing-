package asd;
/**
 * Created by recep on 12.04.2016.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for a binary tree that stores type E objects.
 * @author Koffman and Wolfgang
 * @param <E>
 **/
public class BinaryTree<E> implements Serializable {



    /*<listing chapter="6" number="1">*/
    /** Class to encapsulate a tree node.
     * @param <E> */
    protected static class Node<E> implements Serializable {
        // Data Fields

        /** The information stored in this node. */
        public E data;
        /** Reference to the left child. */
        public Node<E> left;
        /** Reference to the right child. */
        public Node<E> right;

        // Constructors
        /**
         * Construct a node with given data and no children.
         * @param data The data to store in this node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }

        // Methods
        /**
         * Returns a string representation of the node.
         * @return A string representation of the data fields
         */
        @Override
        public String toString() {
            return data.toString();
        }
    }


    /*</listing>*/
    // Data Field
    /** The root of the binary tree */
    protected Node<E> root;

    /** Construct an empty asd.BinaryTree */
    public BinaryTree() {
        root = null;
    }

    /**
     * Construct a asd.BinaryTree with a specified root.
     * Should only be used by subclasses.
     * @param root The node that is the root of the tree.
     */
    protected BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Constructs a new binary tree with data in its root,leftTree
     * as its left subtree and rightTree as its right subtree.
     * @param data
     * @param leftTree
     * @param rightTree
     */
    public BinaryTree(E data, BinaryTree<E> leftTree,
                      BinaryTree<E> rightTree) {
        root = new Node<E>(data);
        if (leftTree != null) {
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null) {
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    /**
     * Return the left subtree.
     * @return The left subtree or null if either the root or
     * the left subtree is null
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null) {
            return new BinaryTree<E>(root.left);
        } else {
            return null;
        }
    }

    /**
     * Return the right sub-tree
     * @return the right sub-tree or
     *         null if either the root or the
     *         right subtree is null.
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null) {
            return new BinaryTree<E>(root.right);
        } else {
            return null;
        }
    }

    /**
     * Return the data field of the root
     * @return the data field of the root
     *         or null if the root is null
     */
    public E getData() {
        if (root != null) {
            return root.data;
        } else {
            return null;
        }
    }

    /**
     * Determine whether this tree is a leaf.
     * @return true if the root has no children
     */
    public boolean isLeaf() {
        return (root == null || (root.left == null && root.right == null));
    }



    /**
     * Perform a preorder traversal.
     * @param node The local root
     */



    /*<listing chapter="6" number="2">*/
    /**
     * Method to read a binary tree.
     * @pre The input consists of a preorder traversal
     *      of the binary tree. The line "null" indicates a null tree.
     * @param bR The input file
     * @return The binary tree
     * @throws IOException If there is an input error
     */
    public static BinaryTree<String> readBinaryTree(BufferedReader bR)
            throws IOException {
        // Read a line and trim leading and trailing spaces.
        String data = bR.readLine().trim();
        System.out.println(data);
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(bR);
            BinaryTree<String> rightTree = readBinaryTree(bR);
            return new BinaryTree<String>(data, leftTree, rightTree);
        }
    }

    public BinaryTree<Vertex>  getPriorityOperationTree(ArrayList<Vertex> operations)
    {
        BinaryTree<Vertex> operators=new BinaryTree<Vertex>();
        BinaryTree<Vertex> root;
        for(int i=0;i<operations.size();++i)
        {
            if(operations.get(i).getType().equals("Display"))
            {
                operators.root=new Node<Vertex>(operations.get(i));
            }
        }
        root=operators;
        operators.root.left=new Node<Vertex>(operators.root.data.fromList.get(0));

        addOperations(operators.getLeftSubtree());
        return root;
    }
    private void addOperations(BinaryTree<Vertex> operationTree)
    {
        if(operationTree.root.data.fromList.size()==0)
            return;
        else
        if(operationTree.root.data.fromList.size()==1)
        {
            operationTree.root.left=new Node<Vertex>(operationTree.root.data.fromList.get(0));
            operationTree.root=operationTree.root.left;
            addOperations( operationTree);
        }
        else
        if(operationTree.root.data.fromList.size()==2)
        {
            operationTree.root.left=new Node<Vertex>(operationTree.root.data.fromList.get(0));

            addOperations( operationTree.getLeftSubtree());


            operationTree.root.right=new Node<Vertex>(operationTree.root.data.fromList.get(1));

            addOperations( operationTree.getRightSubtree());
        }
    }

}

