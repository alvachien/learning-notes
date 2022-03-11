class Person {
    String name;
    int age;
    static int eyeNum;
    public void info() {
        System.out.println("Name is: " + name + ", age: " + age);
    }
}

public class FieldTest {
    public static void main(String[] args) {
        Person.eyeNum = 2;

        System.out.println("Person.eyeNum = " + Person.eyeNum);

        Person p = new Person();
        p.name = "TestA";
        p.age = 20;
        System.out.println("p.eyeNum = " + p.eyeNum);
        p.info();
    }
}