package com.alvachien.learning.java_tutorial.basic_knowledge;

// Previous feature
// public record Point(int x, int y) {
//     public Point {
//         if (x < 0 || y < 0) {
//             throw new IllegalArgumentException();
//         }
//     }    
// }
public final class Point extends Record {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public String toString() {
        return String.format("Point[x=%s, y=%s]", x, y);
    }
}