package com.alvachien.learning.java_tutorial.basic_knowledge;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    System.out.println("Hello World!");

    // String
    String s = "ABC\n\u4e2d\u6587"; // 包含6个字符: A, B, C, 换行符, 中, 文
    System.out.println(s);

    String t = """
        SELECT * FROM
          users
        WHERE id > 100
        ORDER BY name DESC
        """;
    System.out.println(t);

    // Array
    int[] ns = new int[5];
    System.out.println(ns.length); // 5

    int[] ns2;
    ns2 = new int[] { 68, 79, 91, 85, 62 };
    System.out.println(ns2.length); // 5
    ns = new int[] { 1, 2, 3 };
    System.out.println(ns.length); // 3

    // Printf
    double d = 3.1415926;
    System.out.printf("%.2f\n", d); // 显示两位小数3.14
    System.out.printf("%.4f\n", d); // 显示4位小数3.1416

    // Scanner
    Scanner scanner = new Scanner(System.in); // 创建Scanner对象
    System.out.print("Input your name: "); // 打印提示
    String name = scanner.nextLine(); // 读取一行输入并获取字符串
    System.out.print("Input your age: "); // 打印提示
    int age = scanner.nextInt(); // 读取一行输入并获取整数
    System.out.printf("Hi, %s, you are %d\n", name, age); // 格式化输出
    scanner.close();

    // Anonmous class
    HashMap<String, String> map1 = new HashMap<>();
    HashMap<String, String> map2 = new HashMap<>() {
    }; // 匿名类!
    HashMap<String, String> map3 = new HashMap<>() {
      {
        put("A", "1");
        put("B", "2");
      }
    };
    System.out.println(map3.get("A"));
  }
}
