package com.alvachien;

class Parent {
    {
        System.out.println("Parent, non-static Initialize");
    }
    public Parent() {
        System.out.println("Parent, constructor");
    }
}
class Child extends Parent {
    {
        System.out.println("Child, non-static Initialize");
    }
    public Child() {
        System.out.println("Child, constructor");
    }
}

public class InitTest {
    public static void main(String[] args) {
        Cat cat = new Cat("Kitty", 2);
        System.out.println(cat);

        Cat cat2 = new Cat("Jerfield", 3);
        System.out.println(cat2);

        new Child();
    }    
}
