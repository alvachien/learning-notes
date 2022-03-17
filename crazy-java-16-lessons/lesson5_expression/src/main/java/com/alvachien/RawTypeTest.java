package com.alvachien;

import java.util.ArrayList;
import java.util.List;

public class RawTypeTest {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("test1");
        list.add("test2");
        list.add("test3");

        List<Integer> listInt = list;

        for(int i = 0; i < listInt.size(); i ++) {
            System.out.println(listInt.get(i));
        }

    }

}
