# lesson5_expression

Maven project build with:
- Java 11 (OpenJDK 11)
- JUnit 5


# Key Takeaway

## String

当程序中需要使用字符串（以及基本类型包装类实例）时，尽量使用字符串直接量、基本类型值的直接量，避免通过newString()、newInteger()的形式来创建字符串（基本类型）等。

String类似一个典型的不可变类。一个String对象创建完成后，该String类包含的字符序列就固定了，而且不能再修改了。
对于需要修改字符串的逻辑，需要使用StringBuilder类。StringBuffer类也可以达到同样效果，不过因为其是线程安全的，性能会比较差。所以在没有线程安全时，使用StringBuilder。

如果比较两个字符串是否为同一个，直接使用==操作符。如果比较两个字符串包含的字符序列是否相同，则应该使用String类重写过的equals()方法。同时，String类还实现了Comparable接口，可以通过compareTo()方法来进行比较。

## 数值类型的自动提升

Java中算术表达式中，数值类型的提升规则：
- 所有byte, short和char类型将自动提升为int类型；
- 整个算术表达式的数据类型会自动提升到表达式中最高等级操作数的类型；

Java中，复合赋值运算符自动包含了一个隐式的类型转换。所以，下面两条语句并不等价：

```java
a = a + 5;
a += 5; // a = (typeof a)(a + 5);
```

## 转义字符

Java提供了三种方式来表示字符：
- 直接使用单引号括起来的字符，如'a'；
- 使用转义字符，如'\n';
- 使用Unicode转义字符，如'\u000a'（相当于\n）；
