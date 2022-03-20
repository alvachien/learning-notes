package com.alvachien;

public class LinkStackTest {
    public static void main(String[] args) {
        // System.out.println("Hello world");
        LinkStack<String> stack = new LinkStack<String>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        System.out.println(stack);
        System.out.println("Stack top is " + stack.peek());

        System.out.println("Pop one: " + stack.pop());
        System.out.println("Pon another one: " + stack.pop());
        System.out.println(stack);
    }
    
}
