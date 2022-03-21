package com.alvachien;

import java.util.ArrayList;
import java.util.List;

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
    }
}
