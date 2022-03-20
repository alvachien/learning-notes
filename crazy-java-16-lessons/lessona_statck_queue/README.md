# lessona-stack-queue

Maven project:
- Java 16
- JUnit 5


# Key takeaways

## Stack

Stack是是一种后进先出的线性表。通常只提供下述方法：
- 初始化。
- 返回栈的长度。
- 入栈；
- 出栈；
- 访问栈顶元素；
- 判断栈是否为空。
- 清空栈。

Java中的Stack接口有两个实现类：
- Stack类，普通的顺序类；
- LinkedList类，双向链表。

## Queue

Queue是一种先进先出的线性表。只允许在表的前端进行删除操作，只允许在表的后端进行插入操作。

- 初始化。
- 返回队列的长度。
- 加入队列；
- 删除队列的前端元素；
- 访问队列的前端元素；
- 判断队列是否为空。
- 清空队列。

Java提供了一个Queue接口，并为该接口提供了众多的实现类：
- ArrayBlockingQueue; 线程安全。
- LinkedBlockingQueue; 线程安全。
- PriorityBlockingQueue;
- PriorityQueue;
- ConcurredLinkedQueue; 线程安全。
- SynchronousQueue;

## Dequeue

Dequeue，双向队列。它可以同时在两端进行插入、删除操作。某种程度上，可以说Dequeue这个接口同时派生自Queue和Stack。

Java为该接口提供了ArrayDequeue, LinkedBlockingDeque, LinkedList这三个实现类。


## 常用的方法

### Arrays.fill

用来将指定数组的元素用特定的值进行赋值：

```java
// Makes all elements of a[] equal to "val"
public static void fill(int[] a, int val)

// Makes elements from from_Index (inclusive) to to_Index
// (exclusive) equal to "val"
public static void fill(int[] a, int from_Index, int to_Index, int val)
```

使用示例：   

```java
int ar[] = {2, 2, 1, 8, 3, 2, 2, 4, 2};
  
// To fill complete array with a particular value
Arrays.fill(ar, 10);
System.out.println("Array completely filled with 10\n" + Arrays.toString(ar));
// [10, 10, 10, 10, 10, 10, 10, 10, 10]

// Fill from index 1 to index 4.
Arrays.fill(ar, 1, 5, 10);
// [2, 10, 10, 10, 10, 2, 2, 2, 2]
```

### Arrays.copyOf

用来复制数组。

```java
copyOf(int[] original, int newLength) 
```

```java
// initializing an array original
int[] org = new int[] {1, 2 ,3};
     
// copying array org to copy 
// Here, new array has 5 elements - two
// elements more than the original array
int[] copy = Arrays.copyOf(org, 5);
// [1,2,3,0,0]
```
