package com.alvachien;

import java.util.Arrays;

//interface IntArrayProductor {
//    int product();
//}


public class CommandTest2 {
    public int[] process(IntArrayProductor cmd, int length) {
        int[] result = new int[length];
        for(int i = 0; i < length; i ++) {
            result[i] = cmd.product();
        }
        return result;
    }

    public static void main(String[] args){

        CommandTest2 ct = new CommandTest2();
        final int seed = 5;

        class IntArrayProductorImpl implements IntArrayProductor {
            public int product() {
                return (int)Math.round(Math.random() * seed);
            }
        }

        int[] result = ct.process(new IntArrayProductorImpl(), 6);

        System.out.println(Arrays.toString(result));
    } 
}
