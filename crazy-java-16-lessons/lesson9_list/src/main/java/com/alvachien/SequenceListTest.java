package com.alvachien;

public class SequenceListTest {
    public static void main(String[] args) {
        // System.out.println("Hello world");
        SequenceList<String> list = new SequenceList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.insert("ddd", 1);
        System.out.println(list);

        list.delete(2);
        System.out.println(list);

        System.out.println("ccc in the list index is: " + list.findIndex("ccc"));
    }
}
