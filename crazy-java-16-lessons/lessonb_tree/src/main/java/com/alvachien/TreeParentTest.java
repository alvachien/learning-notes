package com.alvachien;

import java.util.List;

public class TreeParentTest {
    public static void main(String[] args) {
        // System.out.println("Hello world");
        TreeParent<String> tp = new TreeParent<String>("root");
        TreeParent.Node root = tp.root();

        System.out.println(root);

        tp.addNode("Child1", root);
        System.out.println("Tree Level: " + tp.deep());

        tp.addNode("Child2", root);

        List<TreeParent.Node<String>> nodes = tp.children(root);
        System.out.println("Root children:" + nodes);
        System.out.println("Root first child:" + nodes.get(0));

        tp.addNode("Child1.1", nodes.get(0));
        System.out.println("Tree level: " + tp.deep());
    }
    
}
