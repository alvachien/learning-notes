package com.alvachien;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ArrayListRemove {

    public static void main(String[] args) {
        
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        //list1.add(5);
        for(Iterator<Integer> it = list1.iterator(); it.hasNext(); ) {
            Integer elem = it.next();
            if (elem.equals(2)) {
                list1.remove(elem);
            }
        }
    }
}
