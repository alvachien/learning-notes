package com.alvachien;

import java.util.ArrayList;
import java.util.List;

class Apple<T extends Number> {
    T size;
    public Apple() {        
    }
    public Apple(T size) {
        this.size = size;
    }
    public void setSize(T size) {
        this.size = size;
    }
    public T getSize() {
        return this.size;
    }

    public List<String> getApples() {
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < 3; i ++) {
            list.add(new Apple<Integer>(10 * i).toString());
        }
        return list;
    }

    public String toString() {
        return "Apple[size = " + size + "]";
    }
}

public class ErasureTest2 {
    public static void main(String[] args) {
        Apple<Integer> a = new Apple<Integer>(6);
        for(String apple: a.getApples()) {
            System.out.println(apple);
        }

        // Apple b = a;
        // for(String apple: b.getApples()) {
        //     System.out.println(apple);
        // }
    }
}
