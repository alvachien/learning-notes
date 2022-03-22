package com.alvachien;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class HuffmanTree {
    public static class Node<E> {
        E data;
        double weight;
        Node<E> leftChild;
        Node<E> rightChild;
        public Node(E data, double weight) {
            this.data = data;
            this.weight = weight;
        }
        public String toString() {
            return "Node[data=" + data +", weight=" + weight + "]";
        }
    }

    public static void main(String[] args) {
        List<HuffmanTree.Node<String>> nodes = new ArrayList<>();
        nodes.add(new HuffmanTree.Node<>("A", 40.0));
        nodes.add(new HuffmanTree.Node<>("B", 8.0));
        nodes.add(new HuffmanTree.Node<>("C", 10.0));
        nodes.add(new HuffmanTree.Node<>("D", 30.0));
        nodes.add(new HuffmanTree.Node<>("E", 10.0));
        nodes.add(new HuffmanTree.Node<>("F", 2.0));

        HuffmanTree.Node<String> root = HuffmanTree.createTree(nodes);
        System.out.println(breadthFirst(root));
    }

    private static <T> HuffmanTree.Node<T> createTree(List<HuffmanTree.Node<T>> nodes) {
        while(nodes.size() > 1) {
            quickSort(nodes);

            HuffmanTree.Node<T> left = nodes.get(nodes.size() - 1);
            HuffmanTree.Node<T> right = nodes.get(nodes.size() - 2);

            HuffmanTree.Node<T> parent = new HuffmanTree.Node(null, left.weight + right.weight);

            parent.leftChild = left;
            parent.rightChild = right;

            nodes.remove(nodes.size() - 1);
            nodes.remove(nodes.size() - 1);

            nodes.add(parent);
        }

        return nodes.get(0);
    }

    private static <E> void swap(List<HuffmanTree.Node<E>> nodes, int i, int j) {
        HuffmanTree.Node<E> tmp = nodes.get(i);
        nodes.set(i, nodes.get(j));
        nodes.set(j, tmp);
    }

    private static <T> void subSort(List<HuffmanTree.Node<T>> nodes, int start, int end) {
        if (start < end) {
            HuffmanTree.Node<T> base = nodes.get(start);
            int i = start;
            int j = end + 1;
            while(true) {
                while(i < end && nodes.get(++i).weight >= base.weight);
                while(j > start && nodes.get(--j).weight <= base.weight);
                if(i < j) {
                    swap(nodes, i, j);
                } else {
                    break;
                }
            }
            swap(nodes, start, j);

            subSort(nodes, start, j - 1);
            subSort(nodes, j + 1, end);
        }
    }

    public static <T> void quickSort(List<HuffmanTree.Node<T>> nodes) {
        subSort(nodes, 0, nodes.size() - 1);
    }

    public static <T> List<HuffmanTree.Node<T>> breadthFirst(HuffmanTree.Node<T> root) {
        Queue<HuffmanTree.Node<T>> queue = new ArrayDeque<>();
        List<HuffmanTree.Node<T>> list = new ArrayList<>();
        if (root != null) {
            queue.offer(root);
        }
        while(!queue.isEmpty()) {
            list.add(queue.peek());

            HuffmanTree.Node<T> p = queue.poll();
            if (p.leftChild != null) {
                queue.offer(p.leftChild);
            }
            if (p.rightChild != null) {
                queue.offer(p.rightChild);
            }
        }
        return list;
    }
}
