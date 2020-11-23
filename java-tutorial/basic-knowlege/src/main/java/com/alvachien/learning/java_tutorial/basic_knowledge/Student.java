package com.alvachien.learning.java_tutorial.basic_knowledge;

public final class Student extends Person {
    private int score;

    public Student(String name, int age, int score) {
        super(name, age);
        this.score = score;
    }
    
    public int getScore() { 
        return this.score;
    }
    public void setScore(int score) { 
        this.score = score;
    }

    @Override
    public void run() {
        System.out.println("Student.run");
    }
}
