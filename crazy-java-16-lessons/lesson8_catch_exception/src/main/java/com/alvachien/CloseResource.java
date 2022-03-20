package com.alvachien;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class ResourceToBeClose implements Serializable {
    private String name;
    public ResourceToBeClose(String name) {
        System.out.println("Entering ResourceToBeClose Constructor");
        this.name = name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj.getClass() == ResourceToBeClose.class) {
            ResourceToBeClose target = (ResourceToBeClose)obj;
            return target.name.equals(this.name);
        }
        return false;
    }
    public int hashCode() {
        return this.name.hashCode();
    }
}

public class CloseResource {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        ResourceToBeClose tbc = new ResourceToBeClose("Test 1");
        System.out.println("Variable tbc created");

        ResourceToBeClose tbc2 = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            oos = new ObjectOutputStream(new FileOutputStream("a.bin"));
            ois = new ObjectInputStream(new FileInputStream("a.bin"));

            oos.writeObject(tbc);
            oos.flush();

            tbc2 = (ResourceToBeClose)ois.readObject();
        }
        finally {
            if (oos != null)
                oos.close();
            if (ois != null)
                ois.close();
        }
    }    
}
