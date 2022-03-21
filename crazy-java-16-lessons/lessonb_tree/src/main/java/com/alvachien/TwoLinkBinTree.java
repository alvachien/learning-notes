package com.alvachien;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TwoLinkBinTree<E> {
    public static class TreeNode<T> {
        T data;
        TreeNode<T> left;
        TreeNode<T> right;
        public TreeNode() {            
        }
        public TreeNode(T data) {
            this.data = data;
        }
        public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode<E> root;

    public TwoLinkBinTree() {
        this.root = new TreeNode<E>();
    }
    public TwoLinkBinTree(E data) {
        this.root = new TreeNode<E>(data);
    }

    /**
     * Add a new node to the tree.
     * @param parent Parent node
     * @param data Data
     * @param isLeft Add as left if true;
     * @return The new created node
     */
    public TreeNode<E> addNode(TreeNode<E> parent, E data, boolean isLeft) {
        if (parent == null) {
            throw new RuntimeException("Parent Node is NULL");
        }
        if (isLeft && parent.left != null) {
            throw new RuntimeException("Parent Node left child already exist");
        }
        if (!isLeft && parent.right != null) {
            throw new RuntimeException("Parent Node right child already exist");
        }

        TreeNode<E> newNode = new TreeNode<E>(data);
        if (isLeft) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        return newNode;
    }

    public boolean empty() {
        return root.data == null;
    }

    public TreeNode<E> root() {
        if (empty()) {
            throw new RuntimeException("Empty tree");
        }
        return root;
    }

    // public E parent(TreeNode<E> node) {
    //     return null;
    // }

    public E leftChild(TreeNode<E> parent) {
        if (parent == null) {
            throw new RuntimeException("Parent is NULL");
        }
        return parent.left == null ? null : (E)parent.left.data;
    }

    public E rightChild(TreeNode<E> parent) {
        if (parent == null) {
            throw new RuntimeException("Parent is NULL");
        }
        return parent.right == null ? null : (E)parent.right.data;
    }

    public int deep() {
        return deep(root);
    }

    private int deep(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        } 
        else {
            int leftDeep = deep(node.left);
            int rightDeep = deep(node.right);

            int max = leftDeep > rightDeep ? leftDeep : rightDeep;

            return max + 1;
        }
    }

    public List<TreeNode<E>> preIterator() {
        return preIterator(root);
    }

    private List<TreeNode<E>> preIterator(TreeNode<E> node) {
        List<TreeNode<E>> list = new ArrayList<>();
        list.add(node);

        if (node.left != null) {
            list.addAll(preIterator(node.left));
        }
        if(node.right != null) {
            list.addAll(preIterator(node.right));
        }
        return list;
    }

    public List<TreeNode<E>> inIterator() {
        return inIterator(root);
    }

    private List<TreeNode<E>> inIterator(TreeNode<E> node) {
        List<TreeNode<E>> list = new ArrayList<>();
        if (node.left != null) {
            list.addAll(inIterator(node.left));
        }
        list.add(node);
        if (node.right != null) {
            list.addAll(inIterator(node.right));
        }
        return list;
    }

    public List<TreeNode<E>> postIterator() {
        return postIterator(root);
    }
    private List<TreeNode<E>> postIterator(TreeNode<E> node) {
        List<TreeNode<E>> list = new ArrayList<>();
        if (node.left != null) {
            list.addAll(postIterator(node.left));
        }
        if (node.right != null) {
            list.addAll(postIterator(node.right));
        }
        list.add(node);
        return list;
    }

    public List<TreeNode<E>> breadthFirst() {
        Queue<TreeNode<E>> queue = new ArrayDeque<>();
        List<TreeNode<E>> list = new ArrayList<>();

        if (root != null) {
            queue.offer(root);
        }
        while(!queue.isEmpty()) {
            list.add(queue.peek());

            TreeNode<E> p = queue.poll();
            if (p.left != null) {
                queue.offer(p.left);
            }
            if (p.right != null) {
                queue.offer(p.right);
            }
        }
        return list;
    }
}
