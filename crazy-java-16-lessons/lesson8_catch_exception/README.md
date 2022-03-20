# lesson8-catch-exception

Maven project with:
- Java 16
- JUnit 5


# Key takeaways

## 释放资源

推荐使用finally块来释放资源（物理资源：数据库链接，网络链接等）。


## RuntimeException

RuntimeException及其子类被称为Runtime异常。不是RuntimeException的Exception则被称为Checked异常。

Runtime异常是一种灵活的异常，无需显式地声明抛出。而Checked异常则必须显式声明。
常见的Runtime 异常有IndexOutofBoundsException, NullPointsException等等；
常见的Checked异常有IOException，ClassNotFoundException等。

## 继承与Exception

Java规定，子类重写父类方法时，不能声明抛出比父类方法类型更多、范围更大的异常。换言之，子类方法只能抛出父类方法声明的异常以及该异常的子类。

