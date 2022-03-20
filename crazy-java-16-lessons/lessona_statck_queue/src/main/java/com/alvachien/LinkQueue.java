package com.alvachien;

public class LinkQueue<T> {
    private class Node {
        private T data;
        private Node next;
        public Node() {            
        }
        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node front;
    private Node tail;
    private int size;
    public LinkQueue() {
        front = null;
        tail = null;
    }
    public LinkQueue(T element) {
        front = new Node(element, null);
        tail = front;
        size++;
    }

    public int length() {
        return size;
    }
    public void add(T element) {
        if (front == null) {
            front = new Node(element, null);
            tail = front;
        }
        else {
            Node newNode = new Node(element, null);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    public T remove() {
        Node oldFront = front;
        front = front.next;
        oldFront.next = null;
        size --;
        return oldFront.data;
    }
    public T element() {
        return tail.data;
    }
    public boolean empty() {
        return size == 0;
    }
    public void clear() {
        front = null;
        tail = null;
        size = 0;
    }
    public String toString() {
        if (empty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for(Node curr = front; curr != null; curr = curr.next) {
            sb.append(curr.data.toString() + ", ");
        }
        int len = sb.length();
        return sb.delete(len - 2, len).append("]").toString();
    }
}
