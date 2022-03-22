package com.alvachien;

public class SortedBinTreeTest {

    public static void main(String[] args) {
        SortedBinTree<Integer> tree = new SortedBinTree<>();
        tree.addNode(5);
        tree.addNode(20);
        tree.addNode(10);
        tree.addNode(3);
        tree.addNode(8);
        tree.addNode(15);
        tree.addNode(30);

        System.out.println(tree.breadthFirst());

        tree.remove(20);
        System.out.println(tree.breadthFirst());
    }

}
