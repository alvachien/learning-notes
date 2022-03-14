package com.alvachien;

import java.util.HashMap;
import java.util.TreeMap;

public class MapValueTest {
    public static void main(String[] args) {
        HashMap<String, Double> scores = new HashMap<String, Double>();
        scores.put("Chinese", 89.0);
        scores.put("Math", 83.0);
        scores.put("English", 80.0);

        System.out.println(scores.values());
        System.out.println(scores.values().getClass());

        TreeMap<String, Double> health = new TreeMap<String, Double>();
        health.put("Height", 173.0);
        health.put("Weight", 71.2);
        
        System.out.println(health.values());
        System.out.println(health.values().getClass());
    }
}
