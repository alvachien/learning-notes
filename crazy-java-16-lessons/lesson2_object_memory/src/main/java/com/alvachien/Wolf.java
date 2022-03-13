package com.alvachien;

class Animal {
    //private String desc;

    public Animal(){
        //this.desc = getDesc();
    }

    public String getDesc() {
        return "Animal";
    }
    public String toString() {
        return getDesc();
    }
}

public class Wolf extends Animal {
    private String name;
    private double weight;

    public Wolf(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String getDesc() {
        return "Wolf[name=" + name + ", weight = " + weight + "]";
    }

    public static void main(String[] args) {
        System.out.println(new Wolf("test", 32));
    }
}
