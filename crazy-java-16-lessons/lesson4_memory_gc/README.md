# lesson4_memory_gc

Maven project build with:
- Java 11 (OpenJDK 11)
- JUnit 5


# Key Takeaway

## GC的基本概念

基本上可以把JVM的内存引用对象理解成一种有向图，把引用变量、对象都当成有向图的顶点，将引用关系当成有向图的有向边，该有向边的方向总是从引用端指向被引用的对象。
对单线程程序来说，只有一条main线程，该线程就是有向图的一个顶点（只有出边）。

对垃圾回收机制来说，判断一个对象是否可以进行回收的标准在于该对象是否引用。从JDK 1.2开始，Java在java.lang.ref提供了三个类：
- SoftReference
- PhantomReference
- WeakReference

它们分别代表了系统对象的3中引用方式：软引用、虚引用和弱引用。

### 四种引用方式

#### 强引用

这是最常见的引用方式。创建一个对象，并将该对象赋给一个变量，这个引用变量就是强引用。

被强引用的Java对象绝不会给垃圾回收机制回收。所以，这是造成Java内存泄露的主要原因之一。

#### 软引用

软引用通过SoftReference类来实现。当一个对象只有一个软引用时，它有可能被垃圾回收机制回收（通常是系统内存空间不足时）。

#### 弱引用

弱引用跟软引用类似，但是引用级别更低。当系统垃圾回收机制运行时，不管系统内存是否足够，总是被回收。

#### 虚引用

软引用和弱引用可以单独使用，但是虚引用必须与引用队列联合使用。虚引用的主要作用就是跟踪对象被垃圾回收的状态，程序可以通过检查与虚作用关联的引用队列是否已经包括指定的虚引用，从而了解虚引用所引用对象是否即将被回收。

引用队列通过java.lang.ref.ReferenceQueue类表示，它用于保存被回收后对象的引用。
虚引用通过PhantomReference类实现。


## Java内存泄漏

Java内存泄漏是指内存对象没有及时释放，导致GC也不能回收。

ArrayList源码中的remove函数：
```java
public E remove(int index) {
    // ...
    elementData[--size] = null; // Ensure GC work
    // ...
}
```

## 垃圾回收机制

垃圾回收机制主要包括两件事情：
- 跟踪并监控每个Java对象；
- 清理内存分配；

现代的垃圾回收器用分代的方式采用不同的回收算法。分代回收的策略基于两个事实：绝大多数的对象不会长时间引用；生存时间很长和生存时间很短的对象之间很少互相引用。根据对象生存实际的长短，将堆内存分为3个代：
- Young。对Young代采用复制算法。只需遍历那些还有引用的对象并将其复制，这时复制成本不大。
    - Young代由1个Eden区和2个Survivor区构成。绝大多数对象先分配到Eden区（有一些大的对象可能被直接分配到Old代中），而Survivor区中的对象都至少在Young代中经历过一次垃圾回收，所以这些对象被转移到Old代之前都会先保留在Supervivor区中。同一时间2个Survivor空间中有一个用来保存对象，另一个为空，用来在下次垃圾回收时保存Young代的对象。
    - 每次复制就是将Eden和第一个Survivor的对象复制到第二个Survivor去中，然后清空Eden和第一个Survivor区。
    - Eden和Survivor的比例通过--XX:SurvivorRatio的附加选项来设定，默认为32。
- Old。Old代的空间要比Young代空间更大。同时Old代垃圾回收的频率无需太高，每次对Old代的垃圾回收需要更长的时间。通常Old代会使用标记压缩算法，这种算法避免复制，而且由于Old代对象不会很快死亡，不会大量产生内存碎片。
- Permanent。Permanent代主要用于装载Class、方法的信息，默认为64M。垃圾回收机制通常不会回收Permanent代中的对象。


### 垃圾回收的附加选项
- -Xmx: 设置JVM中堆内存的最大容量。
- -Xms：设置JVM中堆内存的初始容量。
- -XX:MinHeapFreeRatio=40: 设置Java堆内存最小的空闲百分比；
- -XX:MaxHeapFreeRatio=70: 设置Java堆内存最大的空闲百分比；
- -XX:NewRatio=2： 设置Young/Old内存的比例。
- -XX:NewSize=size: 设置Young代内存的默认容量。
- -XX:SurvivorRatio=8: 设置Young代中eden/survivor的比例；
- -XX:MaxNewSize=size: 设置Young代中内存的最大容量。
- -XX:PermSize=size: 设置Permanent代内存的默认容量。
- -XX:MaxPermSize=64M: 设置Permanent代的内存最大容量。

### 常见的垃圾回收器

#### 串行回收器 Serial Collector

串行回收器通过运行Java程序使用-XX:+UseSerialGC 附加选项启用。

串行回收器对Young代和Old代的回收都是串行的，而且垃圾回收执行期间会使得应用程序暂停。串行回收器对Old代的回收采用串行、标记压缩算法。这个算法有三个阶段：mark，sweep和compact。

#### 并行回收器 

并行回收器通过运行Java程序使用-XX:+UseParallelGC附加选项启用。

并行回收器对Young代采用与串行回收器相似的回收算法，只是增加了多CPU并行的能力。

#### 并行压缩回收器 Parallel Compacting Collector

并行压缩回收器与并行回收器最大的不同是，对Old代的回收使用了不同的算法，并行压缩回收器最终会取代并行回收器。

并行压缩回收器通过运行Java程序使用-XX:+UseParallelOldGC附加选项启用。

并行压缩回收器的改变主要体现在对Old代的回收上。


#### 并发标识-清理（Mark-Sweep)回收器（CMS）

并发标识-清理回收器通过运行Java程序使用-XX:+UseConcMarkSweepGC附加选项启用。
CMS回收器对Young代的回收与并行回收器的回收方式完全相同。CMS回收器对Old代多数是并发操作，而不是并行操作。


## 内存管理技巧

- 尽量使用直接量
```java
String str = "hello";
String str = new String("hello"); // 避免使用
```

- 使用StringBuilder和StringBuffer进行字符串连接
- 尽早释放无用对象的引用
```java
public void method1(){
    Object info = new Object();
    // ....
    info = null; // Clear it
}
```
- 尽量少使用静态变量。静态变量的生命周期与类同步，在该类未被卸载之前，类上的静态变量会常驻内存。
- 避免在经常调用的方法、循环中创建Java对象
- 缓存经常使用的对象
可以使用HashMap或其他开源的缓存项目（OSCache、Ehcache）等。
- 尽量不要使用finalize方法。虽然垃圾回收器准备回收对象前会先调用该对象上的finalize方法。但是这样会造成垃圾回收器的工作量变大。
- 考虑使用SoftReference。


