# lesson2_object_memory

Maven project build with:
- Java 11 (OpenJDK 11)
- JUnit 5


# Key takeaway


## Static Field VS Non-static Field

静态变量将该Field加载类的**定义**中；   
非静态变量将该Field加载在类的**实例**中；

## 类定义中的**非静态初始化代码块**

Java中允许在类定义中增加非静态的初始化代码。这段逻辑会在Constructor之前执行。

事实上，这种初始化代码块等同于将变量直接赋值。其执行顺序取决于在代码中的定义顺序。

```java
public class TestClass {
    public String field1;

    // Non-static initialize codes
    {
        field2 = "a";
    }
    public String field2;

    /// SAME as following:
    /// public String field2 = "a";
    ///

    public TestClass(String f1) {
        this.field1 = f1;
    }
}
TestClass cls = new TestClass("b");
// cls.field2 = "a"
// cls.field1 = "b"
```

注意，如果有多个代码段对同一个变量赋值，后面的会覆盖前面的值。

```java
public class TestClass {
    public String field1;

    // Non-static initialize codes
    {
        field2 = "a";
    }
    public String field2 = "b";

    /// SAME as following:
    /// public String field2 = "b";
    ///

    public TestClass(String f1) {
        this.field1 = f1;
    }
}
TestClass cls = new TestClass("b");
// cls.field2 = "b"
// cls.field1 = "b"
```

## 类定义中的**静态初始化代码块**

Java中还允许在类定义中增加静态的初始化代码。


```java
public class TestClass {
    public String field1;

    // Non-static initialize codes
    static {
        field2 = "a";
    }
    public static String field2;

    /// SAME as following:
    /// public static String field2 = "a";
    ///

    public TestClass(String f1) {
        this.field1 = f1;
    }
}
TestClass cls = new TestClass("b");
// cls.field2 = "a"
// cls.field1 = "b"
```

同样的，多个代码块对同一变量赋值，后面的会覆盖前面的。


## 创建Java对象的初始化

创建任意Java对象时，Java会依次调用：    
- 父类非静态初始化块；
- 父类构造函数；
- 自身的非静态初始化块；
- 自身的构造函数
来进行初始化。    

```java
public class Parent {
    {
        System.out.println("Parent, non-static Initialize");
    }
    public Parent() {
        System.out.println("Parent, constructor");
    }
}
public class Child extends Parent {
    {
        System.out.println("Child, non-static Initialize");
    }
    public Child() {
        System.out.println("Child, constructor");
    }
}
```

运行结果为：   
```log
Parent, non-static Initialize
Parent, constructor
Child, non-static Initialize 
Child, constructor
```

另外一个比较高级一点的示例：   
```java
class Base {
    private int i = 2;
    public Base() {
        // System.out.println("Now the logic in " + this.getClass());
        System.out.println("Base i = " + i);
        this.Display();
    }
    public void Display() {
        System.out.println(i);
    }
}

class Derive extends Base {
    private int i = 22;
    public Derive() {
        // System.out.println("Now the logic in " + this.getClass());
        System.out.println("Child i = " + i);
        i = 222;
    }
    public void Display() {
        System.out.println(i);
    }
}

public class BaseDeriveTest {
    public static void main(String[] args) {
        new Derive();
    }
}
```

这里输出是：   
```log
Base i = 2
0
Child i = 22
```

这是因为，虽然Derive类会调用Base的构造函数，这时候的*this*还是指向Derive类。换言之，Base()中调用的Display其实调用的是Derive的Display，这时Derive中的i并没有执行初始化，所以输出0。

## **final** 修饰符

被**final**修饰的变量，一旦被赋值，其值将不会再发生改变。

注意，如果被**final**修饰的变量，在编译时就能确定其值的话，那么该**final**的变量其实就是常量（**“宏变量”**），会执行**宏替换**。

```java
final String str = "aaa"; // str在编译过程就当做了常量；
```

如果定义的**final**修饰的变量在非静态初始化逻辑或者构造函数中，那么系统不会对该变量进行**宏替换**。


## JVM字符串

JVM会对字符串进行缓存。譬如：   

```java
String a = "test
```

JVM会在字符串池中缓存该字符串，如果后续再有字符串同样设置为"Test"，    

```java
String b = "test
```

那么，变量a和变量b指定统一地址，所以： 

```java
a == b // true
```

## 匿名内部类的局部变量必须要用**final**修饰

匿名内部类的局部变量必须要用**final**修饰，因为局部变量的作用域在包含内部类的函数类。当函数执行结束，该局部变量也随之消失。但内部类可能产生隐式的“闭包Closure”，使得局部变量脱离了函数而继续存在。

```java
interface IntArrayProductor {
    int product();
}


public class CommandTest2 {
    public int[] process(IntArrayProductor cmd, int length) {
        int[] result = new int[length];
        for(int i = 0; i < length; i ++) {
            result[i] = cmd.product();
        }
        return result;
    }

    public static void main(String[] args){

        CommandTest2 ct = new CommandTest2();
        final int seed = 5;

        class IntArrayProductorImpl implements IntArrayProductor {
            public int product() {
                return (int)Math.round(Math.random() * seed);
            }
        }

        int[] result = ct.process(new IntArrayProductorImpl(), 6);

        System.out.println(Arrays.toString(result));
    } 
}
```
