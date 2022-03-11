package com.alvachien.learning.java_tutorial.basic_knowledge;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PersonBeanInfoTest {
    @Test
    public void test1() {
        PersonBeanInfo pbi = new PersonBeanInfo();

        try {
            String str = pbi.getProperties();
            assertEquals("age,class,name", str);
        }
        catch(Exception exp) {
        }
    }    

    @Test
    public void test2() {
        PersonBeanInfo pbi = new PersonBeanInfo();

        try {
            String[] strs = pbi.getReadMethods();
            assertEquals(2, strs.length);

            assertEquals("public int Person.getAge()", strs[0]);
        }
        catch(Exception exp) {
        }
    }    
}
