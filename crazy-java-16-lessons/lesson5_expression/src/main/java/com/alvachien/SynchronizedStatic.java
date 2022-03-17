package com.alvachien;

public class SynchronizedStatic implements Runnable {
    static boolean staticFlag = true;

    public static synchronized void test0() {
        for(int i = 0; i < 100; i ++) {
            System.out.println("test0: " + Thread.currentThread().getName() + " " + i);
        }
    }

    public void test1() {
        synchronized(this) {
            for(int i = 0; i < 100; i ++) {
                System.out.println("test1: " + Thread.currentThread().getName() + " " + i);
            }
        }
    }

    public void run() {
        if(staticFlag) {
            staticFlag = false;
            test0();
        } else {
            staticFlag = true;
            test1();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedStatic ss = new SynchronizedStatic();
        new Thread(ss).start();

        Thread.sleep(10);
        
        new Thread(ss).start();
    }
}
