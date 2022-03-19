# lesson7-object-oriented

Object oriented


# Key takeaways

## instanceof

**instanceof**操作符的前一个操作数通常是一个引用类型的变量，后一个操作数通常是一个类。它用于判断前面的对象是否时后面的类或者子类。

另外，instance操作符可以确保第一个操作数不是null，并且不会抛出NullPointerException。

```java
String s = null;
System.out.println(s instanceof String); // => false
```

## Constructor

Constructor不能带有返回值。所以，当Class中有方法名与类名相同时，编译器会抛出警告，但还是可以执行。

```java
public class ConstructorExample {

    private String name;

    // Constructor
    public ConstructorExample(String name) {
        // this.name = name;
    }
    public void ConstructorExample(String name) {
        this.name = name;
    }

    public ConstructorExample ConstructorExample() {
        return this;
    }
}
```

## 创建对象实例且不调用Constructor

以下两种方式创建的Java对象无需调用Constructor
- 使用反序列化的方式恢复Java对象 （Serializable 接口)；
- 使用Clone方法来复制Java对象  （Cloneable 接口）；


## readResolve

readResolve is used for replacing the object read from the stream. The only use for this is enforcing singletons; when an object is read, replace it with the singleton instance. This ensures that nobody can create another instance by serializing and deserializing the singleton.


## 非静态内部类

### 非静态内部类的Constructor

即便为内部类创建了无参数的构造函数，Java编译时还是会为非静态内部类的构造函数加上指向自身(外部类)的参数。这是因为内部类必须依存在外部类的实例中。
换言之，编译器会自动为内部类的Constructor添加一个形参（第一个形参），指向其外部类。

### 非静态内部类不能定义静态

因为内部类本身必须依存于外部类的实例中，所以不允许


## static

### static方法属于类

static方法是定义在类上的，所以下列代码可以运行（但是编译时会有警告：The static method info() from the type NullInvocation should be accessed in a static way）：

```java
public class A {
    public static void MethodA() {
        // ...
    }
}
A a = null;
a.MethodA(); // 虽然能work，但是不应该这么做。
```

### 静态内部类

如果需要使用内部类，那么尽量使用静态内部类。对静态内部类来说，外部类相当个一个包。但是，内部类不能访问外部类的非静态成员。

## native方法

Java允许定义native方法————只有定义，没有实现。native关键字跟abstract很像，但是native方法通常需要借助C语言来实现。
