package com.alvachien;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class SortedBinTree<T extends Comparable> {
    static class Node<E> {
        E data;
        Node<E> parent;
        Node<E> left;
        Node<E> right;
        public Node(E data, Node<E> parent, Node<E> left, Node<E> right) {
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public String toString() {
            return "Node[data=" + data + "]";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj.getClass() == Node.class) {
                Node<E> target = (Node<E>)obj;
                return data.equals(target.data)
                    && parent == target.parent
                    && left == target.left
                    && right == target.right;
            }
            return false;
        }
    }

    private Node<T> root;

    public SortedBinTree() {
        this.root = null;
    }
    public SortedBinTree(T data) {
        this.root = new Node<T>(data, null, null, null);
    }
    public void addNode(T data) {
        if (root == null) {
            root = new Node<T>(data, null, null, null);
        }
        else {
            Node<T> current = root;
            Node<T> parent = null;
            int cmp = 0;

            do {
                parent = current;
                cmp = data.compareTo(current.data);
                if (cmp > 0) {
                    current = current.right;
                } else {
                    current = current.left;
                }
            }
            while(current != null);

            Node<T> newNode = new Node<T>(data, parent, null, null);
            if (cmp > 0) {
                parent.right = newNode;
            } else {
                parent.left = newNode;
            }
        }
    }

    public void remove(T data) {
        Node<T> target = getNode(data);
        if (target != null) {
            if (target.left == null && target.right == null) {
                if (target == root) {
                    root = null;
                } else {
                    if (target == target.parent.left) {
                        target.parent.left = null;
                    } else {
                        target.parent.right = null;
                    }
                    target.parent = null;
                }
            } else if(target.left == null && target.right != null) {
                if (target == root) {
                    root = null;
                } else {
                    if (target == target.parent.left) {
                        target.parent.left = target.right;
                    } else {
                        target.parent.right = target.right;
                    }
                    target.right.parent = target.parent;
                }
            } else if(target.left != null && target.right == null) {
                if (target == root) {
                    root = target.left;
                } else {
                    if (target == target.parent.left) {
                        target.parent.left = target.left;
                    } else {
                        target.parent.right = target.left;
                    }
                    target.left.parent = target.parent;
                }
            }
        } else {
            Node<T> leftMaxNode = target.left;
            while(leftMaxNode.right != null) {
                leftMaxNode = leftMaxNode.right;
            }

            leftMaxNode.parent.right = null;
            leftMaxNode.parent = target.parent;
            if (target == target.parent.left) {
                target.parent.left = leftMaxNode;
            } else {
                target.parent.right = leftMaxNode;
            }

            leftMaxNode.left = target.left;
            leftMaxNode.right = target.right;

            target.parent = target.left = target.right = null;
        }
    }

    public Node<T> getNode(T data) {
        Node<T> p = root;

        while(p != null) {
            int comp = data.compareTo(p.data);

            if (comp < 0) {
                p = p.left;
            } else if(comp > 0) {
                p = p.right;
            } else {
                return p;
            }
        }

        return null;
    }

    public List<Node<T>> breadthFirst() {
        Queue<Node<T>> queue = new ArrayDeque<>();
        List<Node<T>> list = new ArrayList<>();

        if (root != null) {
            queue.offer(root);
        }

        while(!queue.isEmpty()) {
            list.add(queue.peek());

            Node<T> p = queue.poll();
            if(p.left != null) {
                queue.offer(p.left);
            }
            if (p.right != null) {
                queue.offer(p.right);
            }
        }
        return list;
    }

}
