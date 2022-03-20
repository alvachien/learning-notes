package com.alvachien;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ExitHook {
    public static void main(String[] args) throws FileNotFoundException {
        final FileOutputStream fos;
        fos = new FileOutputStream("a.bin");
        System.out.println("Open the file");

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (fos != null) {
                    try {
                        fos.close();
                    }
                    catch(Exception exp) {
                        exp.printStackTrace();
                    }                    
                }
                System.out.println("Closed file");
            }
        });
        System.exit(0);
    }
}
