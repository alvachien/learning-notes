package com.alvachien.learning.java_tutorial.basic_knowledge;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SQLStringBuilderTest {
    @Test
    public void test1() {
        SQLStringBuilder ssb = new SQLStringBuilder();
        String[] fields = { "name", "position", "salary" };
        String str = ssb.BuildInsertString("employee", fields);
        assertEquals("INSERT INTO employee (name,position,salary) VALUES (?,?,?)", str);
    }    

    @Test
    public void test2() {
        SQLStringBuilder ssb = new SQLStringBuilder();
        String[] fields = { "name", "position", "salary", "rank" };
        String str = ssb.BuildInsertString("employee", fields);
        assertEquals("INSERT INTO employee (name,position,salary,rank) VALUES (?,?,?,?)", str);
    }    
}
