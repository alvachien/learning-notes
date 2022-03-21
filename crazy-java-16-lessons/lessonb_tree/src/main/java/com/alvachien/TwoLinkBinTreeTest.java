package com.alvachien;

public class TwoLinkBinTreeTest {
    public static void main(String[] args) {
        TwoLinkBinTree<String> bintree = new TwoLinkBinTree<String>("Root");

        TwoLinkBinTree.TreeNode<String> tn1 = bintree.addNode(bintree.root(), "Level 2(left)", true);
        TwoLinkBinTree.TreeNode<String> tn2 = bintree.addNode(bintree.root(), "Level 2(right)", false);
        TwoLinkBinTree.TreeNode<String> tn3 = bintree.addNode(tn2, "Level 3(left)", true);
        TwoLinkBinTree.TreeNode<String> tn4 = bintree.addNode(tn2, "Level 3(right)", false);
        TwoLinkBinTree.TreeNode<String> tn5 = bintree.addNode(tn3, "Level 4(right)", true);

        System.out.println("tn2.left child: " + bintree.leftChild(tn2));
        System.out.println("tn2.right child: " + bintree.rightChild(tn2));

        System.out.println(bintree.deep());
    }

}
