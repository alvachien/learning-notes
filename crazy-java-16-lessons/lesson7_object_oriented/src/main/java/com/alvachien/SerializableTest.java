package com.alvachien;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Wolf implements Serializable {
    private String name;
    public Wolf(String name) {
        System.out.println("Entering Wolf Constructor");
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj.getClass() == Wolf.class) {
            Wolf target = (Wolf)obj;
            return target.name.compareTo(this.name) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
public class SerializableTest {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello world");
        Wolf w = new Wolf("Wolf 1");
        System.out.println("Wolf 1 created");

        Wolf w2 = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream("a.bin"));
            ois = new ObjectInputStream(new FileInputStream("a.bin"));
            oos.writeObject(w);
            oos.flush();

            w2 = (Wolf)ois.readObject();
            System.out.println("w.equals(w2) : " + w.equals(w2));
            System.out.println("w == w2:" + (w == w2));
        }
        finally {
            if (oos != null) {
                oos.close();
            }
            if (ois != null) {
                ois.close();
            }
        }
    }

}
