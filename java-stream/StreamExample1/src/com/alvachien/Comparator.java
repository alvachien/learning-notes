package com.alvachien;

import java.util.function.Function;

@FunctionalInterface
public interface Comparator<T> {
    public int compare(T t1, T t2);

    // Version 1
//    public static Comparator<Person> comparing(Function<Person, Integer> f) {
//        return (p1, p2) -> f.apply(p1) - f.apply((p2));
//    }
    // Version 2. Using Comparable
//    public static Comparator<Person> comparing(Function<Person, Comparable> f) {
//        return (p1, p2) -> f.apply(p1).compareTo(f.apply(p2));
//    }

    // Version 3. Generic
    public static <U> Comparator<U> comparing(Function<U, Comparable> f) {
        return (p1, p2) -> f.apply(p1).compareTo(f.apply(p2));
    }

    // Version 1.
    public default Comparator<T> thenComparing(Comparator<T> cmp) {
        return (p1, p2) -> compare(p1, p2) == 0? cmp.compare(p1, p2) : compare(p1, p2);
    }
    public default Comparator<T> thenComparing(Function<T, Comparable> f) {
        //return (p1, p2) -> compare(p1, p2) == 0? f1.apply(p1).compareTo(f.apply(p2)) : compare(p1, p2);
        return thenComparing(comparing(f));
    }
}
