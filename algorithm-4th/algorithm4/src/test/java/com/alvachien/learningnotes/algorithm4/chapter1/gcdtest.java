package com.alvachien.learningnotes.algorithm4.chapter1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class gcdtest {
    @Test
    public void case1() {
        gcd v1 = new gcd();
        int rst = v1.execute(12, 9);
        assertEquals(3, rst);
    }
}
