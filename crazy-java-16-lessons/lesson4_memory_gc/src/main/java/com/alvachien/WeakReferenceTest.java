package com.alvachien;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {
    public static void main(String[] args) {
        String str = new String("Test1");
        WeakReference<String> wr = new WeakReference<String>(str);

        str = null;

        System.out.println(wr.get());

        System.gc();
        System.runFinalization();
        System.out.println(wr.get());
    }
}
