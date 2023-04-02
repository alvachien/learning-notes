package chapter1;

import edu.princeton.cs.algs4.StdOut;

public class Fibonacci {
    public static long[] knownResult = new long[200];    

    public static long F(int N) {
        if (N == 0) {
            knownResult[0] = 0;
            return knownResult[N];
        } else if (N == 1) {
            knownResult[1] = 1;
            return knownResult[N];
        }

        if (knownResult[N] == 0) {
            long nrst = F(N - 1) + F(N - 2);
            knownResult[N] = nrst;
        }

        return knownResult[N];
    }

    public static void main (String[] args) {
        for (int N = 0; N < 100; N ++) 
            StdOut.println(N + " " + F(N));
    }
}
