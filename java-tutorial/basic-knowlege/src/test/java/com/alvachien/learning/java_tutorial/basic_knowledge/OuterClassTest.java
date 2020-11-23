package com.alvachien.learning.java_tutorial.basic_knowledge;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class OuterClassTest {
    @Test
    public void Test1() {
        OuterClass outer = new OuterClass("Nested"); 
        OuterClass.InnerClass inner = outer.new InnerClass();
        var rst = inner.hello();
        assertEquals("Hello, Nested", rst);
    }    
}
