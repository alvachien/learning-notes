package com.alvachien.learningnotes.algorithm4.chapter1;

public class gcd {
    public int execute(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return execute(q, r);
    }
}
