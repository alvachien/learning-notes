# lesson1_array_memory

Maven project with following:    
- Java 11
- JUnit 5

# Key takeaway

## 数组的初始化    

### 静态初始化

静态初始化是指，初始化时，程序显式指定每个数组元素的初始值。系统决定数组长度；

示例代码：    
```java
    String[] names = {
        "name1",
        "name2",
        "name3"
    };

    int[] nums = new int[]{ 3, 5, 20, 12 };
```

### 动态初始化

动态初始化是指，初始化时，程序只指定数组长度，由系统自动为每个数组元素分配初始值。   

示例代码：    
```java
    String[] arArr = new String[5];
```

### 数组可以初始化为null

数组可以初始化为null。

```java
    int[] arr = null;
    System.out.println(arr); // Print out 'null'
```
