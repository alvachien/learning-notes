# lesson3_common_collections

Maven project build with:
- Java 11 (OpenJDK 11)
- JUnit 5

# Key takeaways

## Set和Map

Set是一种其中元素无序但不重复的集合。Map是由多个key/value对组成的集合。

### Set的继承体系

- Set
    - EnumSet
    - SortedSet
        - NavigableSet
            - TreeSet
    - HashSet   
        - LinkedHashSet

### Map的继承体系

- Map
    - IdentityHashMap
    - EnumMap
    - SortedMap
        - NavigableMap
            - TreeMap
    - HashMap
        - LinkedHashMap
    - Hashtable
        - Properties
    - WeakHashMap

### Set和Map的对应关系

- Set <-> Map   
- EnumSet <-> EnumMap   
- SortedSet <-> SortedMap  
- TreeSet <-> TreeMap
- NavigableSet <-> NavigableMap
- HashSet <-> HashMap
- LinkedHashSet <-> LinkedHashMap

逻辑上来说，Map的所有的Key就是一个Set。或者说，Map可以通过Set的方式扩展而成。


### HashMap和HashSet


当系统开始初始化一个HasMap时，系统会创建一个Entry数组（使用默认长度capactity）。这个数组可以存储元素的位置被称为bucket，每个bucket都有其指定索引，系统可以通过索引快速访问该bucket中的元素。
虽然每个bucket只能存储一个元素，但因为每个Entry可以包含一个引用对象，然后指向另外一个Entry对象，即形成了Entry链路，通常用来存储HashCode相同的Entry。

HashMap还有一个默认的负载因子Load Factor，其默认值为0.75。
- 增大Load Factor，会减少空间，但是时间开销会增加；
- 减少Load Factor，空间变大，但时间开销会减少；


HashSet是Set的一种实现，它是基于HashMap实现的。HashSet底层用HashMap来保存所有元素。

### TreeMap和TreeSet

TreeSet也是基于TreeMap实现的。TreeSet底层采用一个NavigableMap接口来保存TreeSet集合的元素。

TreeMap底层采用了“红黑树”（排序二叉树）来保存Map中的每个Entry——每个Entry都被当成该排序二叉树的一个节点。

### Map.values()

Map的values方法返回一个关于value的集合。该values函数返回的数据类型是Collection接口，实现层面，它返回了内部类。

HashMap的内部类：

```java
// HashMap
private final class Values extends AbstractCollection<V> {
    public Iterator<V> iterator() {
        return new ValueIterator();
    }
}
private final class ValueIterator extends HashIterator<V> {
    public V next() {
        return nextEntry().value;
    }
}
```

与HashMap类似，TreeMap的values()方法同样返回了一个Values对象，该对象是TreeMap的内部私有类。

```java
// TreeMap
private final class Values extends AbstractCollection<V> {
    public Iterator<V> iterator() { ... }
    public int size() { ... }
    public boolean contains(Object o) { ... }
    public boolean remove(Object o) { ... }
    final Entry<K, V> getFirstEntry() { ... }
    static <K, V> TreeMap.Entry<K, V> successor(Entry<K, V>) { ... }
}
```

其中：
- getFirstEntry，获取TreeMap底层红黑树中最左边的“叶子节点”，也就是该树中最小的节点，即TreeMap中第一个节点；
- successor，获取红黑树中指定Entry的下一个节点，也就是红黑树中大于输入节点的最小节点；


## List

List类的层次结构：
- List
    - ArrayList
    - Vector
        - Stack
    - LinkedList

### Stack与Dequeue

Java不再推荐使用Stack类，而是推荐Dequeue的实现类（ArrayDequeue）。ArrayDequeue底层也是基于Java数组实现的。


### Vector与ArrayList

Vector和ArrayList的底层是基于数组实现的。其实Vector其实是ArrayList的线程安全版本。Vector的方法中增加了synchronized修饰。多线程编程下，除了使用Vector类之外，Java提供了一个Collections工具类，可以通过该工具类的synchronizedList方法将ArrayList包装成线程安全的ArrayList。

每次创建的ArrayList时传入的int参数就是它包含的数组的长度。ArrayList使用transient来修饰该数组，保证序列化时不会直接序列化数组，而是通过ArrayList的writeObject, readObject等方法实现。

```java
// ArrayList
private transient Object[] elementData;
```

### ArrayList和LinkedList

LinkedList是链式存储的线性表，其本质是双向链表。其每个Entry对象代表双向链表中一个节点，该对象中next变量指向下一个节点，previous指向上一个节点。


## Iterator

Iterator是迭代器接口，专门用于迭代各种Collection集合。
- Set集合对应的Iterator是其对应的Map类的内部类KeyIterator；
- ArrayList和Vector的Iterator是AbstractList类的Itr。
- LinkedList对应的Iterator是内部类的ListItr。
- ArrayDequeue对应的Iterator是DeqIterator。

通常，用Iterator对Collection进行遍历时，不应该删除集合元素，否则引发ConcurrentModificationException异常。


# JDK的源码

JDK通常会附带JDK的源码，文件为src.zip，该源码文件通常在lib文件夹下。


