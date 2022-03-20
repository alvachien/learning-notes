package com.alvachien;

public class LinkList<T> {
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

    private Node header;
    private Node tail;
    private int size;

    public LinkList() {
        header = null;
        tail = null;
        size = 0;
    }
    public LinkList(T element) {
        header = new Node(element, null);
        tail = header;
        size = 1;
    }

    public int length() {
        return size;
    }
    public T get(int index) {
        return getNodeByIndex(index).data;
    }
    private Node getNodeByIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index Out of Bound");            
        }

        Node current = header;
        for(int i = 0; i < size && current != null; i ++) {
            if (i == index) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public int findIndex(T element) {
        Node current = header;
        for(int i = 0; i < size && current != null; i ++) {
            if (current.data.equals(element)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }
    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bound");
        }
        if (header == null) {
            add(element);
        }
        else {
            if (index == 0) {
                addAtHeader(element);
            }
            else {
                Node prev = getNodeByIndex(index - 1);
                prev.next = new Node(element, prev.next);
                size++;
            }
        }
    }
    public void add(T element) {
        if (header == null) {
            header = new Node(element, null);
            tail = header;
        }
        else {
            Node newNode = new Node(element, null);
            tail.next = newNode;
            tail = newNode;
        }
        size ++;
    }
    public void addAtHeader(T element) {
        header = new Node(element, header);
        if (tail == null)
            tail = header;
        size ++;
    }
    public T delete(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("Index out of bound");
        }
        Node del = null;
        if (index == 0) {
            del = header;
            header = header.next;
        }
        else {
            Node prev = getNodeByIndex(index - 1);
            del = prev.next;
            prev.next = del.next;
            del.next = null;
        }
        size --;
        return del.data;
    }
    public T remove() {
        return delete(size - 1);
    }
    public boolean empty() {
        return size == 0;
    }
    public void clear() {
        header = null;
        tail = null;
        size = 0;
    }
    public String toString() {
        if (empty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for(Node current = header; current != null; current = current.next) {
            sb.append(current.data.toString() + ", ");
        }
        int len = sb.length();
        return sb.delete(len - 2, len).append("]").toString();
    }
}
