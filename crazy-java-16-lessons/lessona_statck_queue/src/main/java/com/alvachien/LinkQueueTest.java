package com.alvachien;

public class LinkQueueTest {
    public static void main(String[] args) {
        // System.out.println("Hello world");
        LinkQueue<String> stack = new LinkQueue<String>();
        stack.add("a");
        stack.add("b");
        stack.add("c");
        stack.add("d");
        System.out.println(stack);
        System.out.println("Queue front element: " + stack.element());

        System.out.println("Remove the front element: " + stack.remove());
        System.out.println("Remove the front element again: " + stack.remove());
        System.out.println(stack);
    }    
}
