package com.alvachien;

import java.util.ArrayList;
import java.util.List;

public class TreeChild<E> {
    private static class ChildNode {
        private int pos;
        private ChildNode next;
        public ChildNode(int pos, ChildNode next) {
            this.pos = pos;
            this.next = next;
        }
    }

    public static class Node<T> {
        private T data;
        private ChildNode first;
        public Node(T data) {
            this.data = data;
            this.first = null;
        }
        public String toString() {
            if (first != null) {
                return "TreeChildNode[data=" + data + ", first=" + first.pos + "]";
            }
            return "TreeChildNode[data=" + data + ", first=-1]";
        }
    }

    private final int DEFAULT_TREE_SIZE = 100;
    private int treeSize = 0;
    private Node<E>[] nodes;
    private int nodeNums;

    public TreeChild(E data) {
        treeSize = DEFAULT_TREE_SIZE;
        nodes = new Node[treeSize];
        nodes[0] = new Node<E>(data);
        nodeNums ++;
    }
    public TreeChild(E data, int treeSize) {
        this.treeSize = treeSize;
        nodes = new Node[treeSize];
        nodes[0] = new Node<E>(data);
        nodeNums ++;
    }

    public void addNode(E data, Node parent) {
        for(int i = 0; i < treeSize; i ++) {
            if (nodes[i] == null) {
                nodes[i] = new Node(data);
                if (parent.first == null) {
                    parent.first = new ChildNode(i, null);
                }
                else {
                    ChildNode next = parent.first;
                    while(next.next != null) {
                        next = next.next;
                    }
                    next.next = new ChildNode(i, null);
                }
                nodeNums ++;
                return;
            }
        }

        throw new RuntimeException("Runtime exception");
    }

    public boolean empty() {
        return nodes[0] == null;
    }

    public Node<E> root() {
        return nodes[0];
    }

    public List<Node<E>> children(Node parent) {
        List<Node<E>> list = new ArrayList<Node<E>>();
        ChildNode next = parent.first;
        while(next != null) {
            list.add(nodes[next.pos]);
            next = next.next;
        }

        return list;
    }
    public Node<E> child(Node parent, int index) {
        ChildNode next = parent.first;
        for(int i = 0; next != null; i ++, next = next.next) {
            if (index == i) {
                return nodes[next.pos];
            }
        }
        return null;
    }

    public int deep() {
        return deep(root());
    }

    private int deep(Node node) {
        if (node.first == null) {
            return 1;
        }
        int max = 0;
        ChildNode next = node.first;
        while(next != null) {
            int tmp = deep(nodes[next.pos]);
            if (tmp > max) {
                max = tmp;
            }
            next = next.next;
        }
        return max + 1;
    }

    public int pos(Node node) {
        for(int i = 0; i < treeSize; i ++) {
            if (nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }
}
