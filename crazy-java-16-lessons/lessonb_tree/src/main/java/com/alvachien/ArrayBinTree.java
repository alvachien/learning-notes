package com.alvachien;

import java.util.Arrays;

public class ArrayBinTree<T> {
    private Object[] elementData;
    private int DEFAULT_DEEP = 8;
    private int deep;
    private int arraySize;

    public ArrayBinTree() {
        this.deep = DEFAULT_DEEP;
        this.arraySize = (int)Math.pow(2, deep) - 1;
        elementData = new Object[arraySize];
    }

    public ArrayBinTree(int deep) {
        this.deep = deep;
        this.arraySize = (int)Math.pow(2, deep) - 1;
        elementData = new Object[arraySize];
    }

    public ArrayBinTree(int deep, T data) {
        this(deep);
        elementData[0] = data;
    }

    /**
     * Add new node
     * @param parentIndex Parent Index
     * @param data Node data
     * @param left Add as left node if true.
     */
    public void add(int parentIndex, T data, boolean left) {
        if (elementData[parentIndex] == null) {
            throw new RuntimeException("Invalid parent index");
        }
        if (2 * parentIndex + 1 >= arraySize) {
            throw new RuntimeException("Tree is full");
        }

        if (left) {
            elementData[2 * parentIndex + 1] = data;
        } else {
            elementData[2 * parentIndex + 2] = data;
        }
    }
    
    public boolean empty() {
        return elementData[0] == null;
    }

    public T root() {
        return (T)elementData[0];
    }

    public T parent(int index) {
        return (T)elementData[(index - 1) / 2];
    }

    public T left(int index) {
        if (2  * index + 1 >= arraySize) {
            throw new RuntimeException("No child nodes");
        }
        return (T)elementData[index * 2 + 1];
    }

    public T right(int index) {
        if (2 * index + 2 >= arraySize) {
            throw new RuntimeException("No child nodes");
        }
        return (T)elementData[index * 2 + 2];
    }

    public int deep() {
        return deep;
    }
    public int findIndex(T data) {
        for(int i = 0; i < arraySize; i ++) {
            if (elementData[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public String toString() {
        return Arrays.toString(this.elementData);
    }
}
