package com.alvachien;

import java.util.HashSet;
import java.util.Set;

class TestableName {
    private String first;
    private String last;

    public TestableName(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o.getClass() == TestableName.class ) {
            TestableName tn = (TestableName)o;
            return tn.first.equals(first)
                && tn.last.equals(last);
        }
        return false;
    }

    public int hashCode() {
        return this.first.hashCode();
    }
    public String toString() {
        return "Testable[first = " + first + ", last = " + last + "]";
    }
}

public class HashSetTest {
 
    
    public static void main(String[] args) {
        Set<TestableName> s = new HashSet<TestableName>();
        s.add(new TestableName("ABC", "123"));
        s.add(new TestableName("abc", "123"));
        s.add(new TestableName("abc", "123"));
        System.out.println(s);
        System.out.println(s.contains(new TestableName("ABC", "123")));
    }    
}
