package com.alvachien;

public class ThreeLinkBinTree<E> {
    public static class TreeNode<T> {
        T data;
        TreeNode<T> left;
        TreeNode<T> right;
        TreeNode<T> parent;
        public TreeNode() {            
        }
        public TreeNode(T data) {
            this.data = data;
        }
        public TreeNode(T data, TreeNode<T> left, TreeNode<T> right, TreeNode<T> parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    private TreeNode<E> root;

    public ThreeLinkBinTree() {
        this.root = new TreeNode<E>();
    }
    public ThreeLinkBinTree(E data) {
        this.root = new TreeNode<E>(data);
    }

    /**
     * Add a new node
     * @param parent
     * @param data
     * @param isLeft
     * @return
     */
    public TreeNode<E> addNode(TreeNode<E> parent, E data, boolean isLeft) {
        if (parent == null) {
            throw new RuntimeException("Parent is NULL");
        }
        if (isLeft && parent.left != null) {
            throw new RuntimeException("Left node exists");
        }
        if (!isLeft && parent.right != null) {
            throw new RuntimeException("Right node exists");
        }

        TreeNode<E> newNode = new TreeNode<E>(data);
        if (isLeft) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.parent = parent;
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
    public E parent(TreeNode<E> node) {
        if (node == null) {
            throw new IllegalArgumentException("Node is NULL");
        }
        return node.parent == null ? null : node.parent.data;
    }

    public E leftChild(TreeNode<E> node) {
        if (node == null) {
            throw new IllegalArgumentException("Node is NULL");
        }
        return node.left == null ? null : node.left.data;
    }
    public E rightChild(TreeNode<E> node) {
        if (node == null) {
            throw new IllegalArgumentException("Node is NULL");
        }
        return node.right == null ? null : node.right.data;
    }

    public int deep() {
        return deep(root);
    }

    public int deep(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }

        int leftDeep = deep(node.left);
        int rightDeep = deep(node.right);
        int max = leftDeep > rightDeep ? leftDeep : rightDeep;
        return max + 1;
    }

}
