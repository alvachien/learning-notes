package com.alvachien;

public class SequenceQueueTest {
    public static void main(String[] args) {
        // System.out.println("Hello world");
        SequenceQueue<String> stack = new SequenceQueue<String>();
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
