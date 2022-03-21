package com.alvachien;

public class ArrayBinTreeTest {
    public static void main(String[] args) {
        ArrayBinTree<String> bintree = new ArrayBinTree<String>(4, "Root");

        bintree.add(0, "Level 2(right)", false);
        bintree.add(2, "Level 3(right)", false);
        bintree.add(6, "Level 4(left)", true);

        System.out.println(bintree);
    }
}
