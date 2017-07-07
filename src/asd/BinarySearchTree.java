package asd;

/**
 * Created by recep on 12.04.2016.
 */


import java.util.Iterator;
import java.util.ArrayList;

public class BinarySearchTree<E extends Comparable<E>>
        extends BinaryTree<E>{
    // Data Fields


    protected boolean addReturn;
    protected E deleteReturn;



    private class TreeIterator implements Iterator {

        private Node<E> next;//next node
        private Node<E> head;//head node
        private ArrayList result;//contains results
        public TreeIterator(BinarySearchTree tree)
        {
            result=InOrderTravel(tree);
            int i;
            head=new Node<E>((E)result.get(0));
            next= head;
            for(i=0;i<result.size();++i) {

                next.right=new Node<E>((E)result.get(i));//creates new node object.
                next=next.right;//traveling nodes.

            }

            next=head;//return restart.

        }
        @Override
        public boolean hasNext() {//has next method.
            return next != null;
        }

        @Override
        public Object next() {//next method.
            Node<E> temp=next;
            next=next.right;//travels next node.
            return next;
        }
    }

    /**
     *TreeItr returns TreeIterator object.
     * @param tree
     * @return TreeIterator object.
     */
    public Iterator<E> TreeItr(BinarySearchTree tree) {
        return new TreeIterator(tree);
    }


    public E find(E target) {
        return find(root, target);
    }


    private E find(Node<E> localRoot, E target) {
        if (localRoot == null) {
            return null;
        }

        // Compare the target with the data field at the root.
        int compResult = target.compareTo(localRoot.data);
        if (compResult == 0) {
            return localRoot.data;
        } else if (compResult < 0) {
            return find(localRoot.left, target);
        } else {
            return find(localRoot.right, target);
        }
    }


    public boolean add(E item) {
        root = add(root, item);
        return addReturn;
    }


    private Node<E> add(Node<E> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree � insert it.
            addReturn = true;
            return new Node<E>(item);
        } else if (item.compareTo(localRoot.data) == 0) {
            // item is equal to localRoot.data
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            // item is less than localRoot.data
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        } else {
            // item is greater than localRoot.data
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    public E delete(E target) {
        root = delete(root, target);
        return deleteReturn;
    }

    private Node<E> delete(Node<E> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree.
            deleteReturn = null;
            return localRoot;
        }

        // Search for item to delete.
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0) {
            // item is smaller than localRoot.data.
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        } else if (compResult > 0) {
            // item is larger than localRoot.data.
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        } else {
            // item is at local root.
            deleteReturn = localRoot.data;
            if (localRoot.left == null) {
                // If there is no left child, return right child
                // which can also be null.
                return localRoot.right;
            } else if (localRoot.right == null) {
                // If there is no right child, return left child.
                return localRoot.left;
            } else {
                // Node being deleted has 2 children, replace the data
                // with inorder predecessor.
                if (localRoot.left.right == null) {
                    // The left child has no right child.
                    // Replace the data with the data in the
                    // left child.
                    localRoot.data = localRoot.left.data;
                    // Replace the left child with its left child.
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                } else {
                    // Search for the inorder predecessor (ip) and
                    // replace deleted node's data with ip.
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }

    private E findLargestChild(Node<E> parent) {
        // If the right child has no right child, it is
        // the inorder predecessor.
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findLargestChild(parent.right);
        }
    }

    private ArrayList InOrderTravel(BinaryTree tree)
    {
        ArrayList  result=new ArrayList();
        if(tree.isLeaf())// if tree is leaf implement this situations.
        {
            result.add(tree.getData());//adding data of leaf to result.
        }
        else
        {
            if(tree.getLeftSubtree()!=null)//if left of tree is not null.
                result.addAll(InOrderTravel(tree.getLeftSubtree()));//add  result of left of tree to result arraylist.
            result.add(tree.getData());//add current data of node to result.
            if(tree.getRightSubtree()!=null)//if right of tree is not null.
                result.addAll(InOrderTravel(tree.getRightSubtree()));//add  result of right of tree to result arraylist.
            return result;
        }
        return result;//returns arraylist of result.

    }

    public static void main(String[] args) {

        BinaryTree<Integer> Btree = new BinaryTree<Integer>();
        Btree.root=new Node<Integer>(12);
        Btree.root.left=new Node<Integer>(15);
        Btree.root.right=new Node<Integer>(16);
        System.out.println(Btree.toString());
    }
}