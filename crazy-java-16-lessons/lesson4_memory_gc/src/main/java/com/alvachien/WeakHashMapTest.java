package com.alvachien;

import java.util.WeakHashMap;

class CrazyKey {
    String name;
    public CrazyKey(String name) {
        this.name = name;
    }

    public int hashCode() {
        return name.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == CrazyKey.class) {
            return name.equals(((CrazyKey)obj).name);
        }
        return false;
    }

    public String toString() {
        return "Crazykey[name = " + name + "]";
    }
}

public class WeakHashMapTest {
    public static void main(String[] args) throws InterruptedException {
        WeakHashMap<CrazyKey, String> map = new WeakHashMap<CrazyKey, String>();
        for(int i = 0; i < 10; i ++) {
            map.put(new CrazyKey(i + 1 + ""), "value" + (i + 11));
        }

        System.out.println(map);
        System.out.println(map.get(new CrazyKey("2")));

        System.gc();

        Thread.sleep(50);

        System.out.println(map);
        System.out.println(map.get(new CrazyKey("2")));
    }
}
