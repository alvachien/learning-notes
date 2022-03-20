package com.alvachien;

public class LinkListTest {
    public static void main(String[] args) {
        // System.out.println("Hello world");
        LinkList<String> list = new LinkList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.insert("ddd", 1);
        System.out.println(list);

        list.delete(2);
        System.out.println(list);

        System.out.println("ccc in the list index is: " + list.findIndex("ccc"));
        System.out.println("list with index == 2is: " + list.get(2));
    }
}
