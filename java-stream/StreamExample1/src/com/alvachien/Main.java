package com.alvachien;

public class Main {
    public static void main(String... args) {
        Predicate<String> p1 = s -> s.length() < 20;
        Predicate<String> p2 = s -> s.length() > 5;

        boolean b = p1.test("Hello");
        System.out.println("Hello is smaller than 20 chars: " + b);

        Predicate<String> p3 = p1.and(p2);

        System.out.println("P3 for yes: " + p3.test("Yes"));
        System.out.println("P3 for Good morning: " + p3.test("Good morning"));
        System.out.println("P3 for Good morning gentlemen: " + p3.test("Good morning gentleman"));

        Predicate<String> p4 = p1.or(p2);

        System.out.println("P4 for yes: " + p4.test("Yes"));
        System.out.println("P4 for Good morning: " + p4.test("Good morning"));
        System.out.println("P4 for Good morning gentlemen: " + p4.test("Good morning gentleman"));

        Predicate<String> p5 = Predicate.isEqualsTo("Yes");
        System.out.println("P5 for yes: " + p5.test("Yes"));
        System.out.println("P5 for no: " + p5.test("No"));
    }
}
