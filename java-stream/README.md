# Java Lambda and Stream

## Lamba express

Example of Lambda express for writing a comparator:  

```java
Comparator<String> comparator = (s1, s2) -> Integer.compare(s1.length(), s2.length());
```

### Parameter of lambda express

Modifiers allowed on the paraemters of lambda express:
- The **final** keyword
- Annotations


### Method Reference

Method reference is an alternative syntax for lambda expressions.

The following two examples are equivalent:   
```java
// Without mehtod reference
Function<Person, Integer> f = person -> person.getAge();
// With Method reference
Function<Person, Integer> f2 = Person::getAge;
// System.out::println
Consumer<String> printer = System.out::println;
```

### Default method in Interface, since Java 8

Example of default method: 

```java
public interface Iterable<T> {
    default void forEach(Consumer<? super T> action) {
        Object.requireNonNull(action);
        for(T t : this) {
            action.accept(t);
        }
    }
}
```

### Static methods in interface, since Java 8

Example of static methods:

```java
@FunctionalInterface
public interface Functino<T, R> {
    R apply(T t);

    static <T> Function<T, T> identity() { return t -> t; }
}
```

### Example Comparator using Default method and Static methods

The method *thenCOmparing* can be used to chain the method calls.

```java
@FunctionalInterface
public interface Comparator<T> {
    public int compare(T t1, T t2);

    public static <U> Comparator<U> comparing(Function<U, Comparable> f) {
        return (p1, p2) -> f.apply(p1).compareTo(f.apply(p2));
    }

    public default Comparator<T> thenComparing(Comparator<T> cmp) {
        return (p1, p2) -> compare(p1, p2) == 0? cmp.compare(p1, p2) : compare(p1, p2);
    }
    public default Comparator<T> thenComparing(Function<T, Comparable> f) {
        return thenComparing(comparing(f));
    }
}
```

### Functional interfaces

A **lambda expression** is an instance of a **functional interface**.

And a *functional interface* is an interface:
- With only one abstract method;
- Can define several Default methods;
- Can define several Static methods;
- Shall (not mandatory) be defined by annotation '@FunctionalInterface'
 
JAVA Compiler will use Function Interface method's signature (parameter and return types) to check lambda expression.

```java
// JDK 7
Predicate<String> p = new Predicate<String>() {
    public boolean test(String s) {
        return s.length() < 20;
    }
}
// JDK 8
Predicate<String> p = s -> s.length() < 20;
```

### Package java.util.function

There are 43 classes introduced in JDK 8, with four categories:
- Consumers
- Supplier
- Functions
- Predicates

#### Consumers

A consumer consumes an object ,and doesn't return anything.

```java
public interface Consumer<T> {
    public void accept(T t);
}
public interface BiConsumer<T, V> {
    public void accept(T t, V v);
}
```

#### Suppliers

A supplier provides an object, take no parameter.

```java
public interface Supplier<T> {
    public T get();
}
```

#### Functions

A function takes an object and returns another object.

```java
public interface Function<T, R> {
    public R apply(T, t);
}
public interface BiFunction<T, V, R> {
    public R apply(T t, V v);
}
public interface UnaryOperator<T> extends Function<T, T> {}
public interface BinaryOperator<T> extends BiFunction<T, T, T> {}
```

#### Predicates

A predicate takes an object and return a boolean.

```java
public interface Predicate<T> {
    public boolean test(T t);
}
public interface BiPredicate<T, U> {
    public boolean test(T t, U u);
}
```

## New method in Collections 

### Iterable

New method *forEach*.

```java
boolean forEach(Consumer<? super E> consumer);
```

### Collection

New method *removeIf*.

```java
boolean removeIf(Predicate<? super E> filter);
```

### List

New mthod *replaceAll*, *sort*.

```java
boolean replaceAll(UnaryOperator<? super E> operator);
boolean sort(Comparator<? super E> comparator);
```

### Map

New method *forEach*, *getOrDefault*, *putIfAbsent*, *replace*, *replaceAll*, *remove*.
And a new family of methods: compute*();

```java
void forEach(BiConsumer<? super K, ? super V> consumer);
V getOrDefault(Object key, V defaultValue);
V putIfAbsent(K key, V value);
V replace(K key, V newValue);
boolean replace(K key, V existingValue, V newValue);
void replaceAll(Bifunction<? super K, ? super V, ? extends V> function);
void remove(Object key, Object value);
V compute(K key, Bifunction<? super K, ? super V, ? extends V> function);
V computeIfAbsent(K key, Function<? superK,? extendsV> mapping);
V computeIfPresent(K key, BiFunction<? superK,? superV, ? extendsV> remapping);
V merge(K key, V newValue, BiFunction<? superV,? superV, ? extendsV> remapping);
```

An example to build maps of maps
```java
Map<String, Map<String, Person>> map = ...; 

// Key, new value
map.computeIfAbsent("one", key -> new HasMap<String, Person>()).put("two", john);
```

Another example to build maps of list:
```java
Map<String, List<Person>> map = ...; 

// Key, new value
map.computeIfAbsent("one", key -> new ArrayList<Person>()).add(john);
```

Example to merge two maps: 

```java
Map<City, List<Person>> map1= newHashMap<>();
Map<City, List<Person>> map2= newHashMap<>();
map2.forEach(
    (key, value) ->
        map1.merge(
            key, value,
            (existingPeople, newPeople) -> {
                existingPeople.addAll(newPeople);
                return existingPeople;
            }
        )
);
```

## Map/Filter/Reduce

### Associativity

```ini
Red(a, Red(b, c)) = Red(Red(a, b), c)
```

```java
// Associative
BinaryOperator<Integer> op1 = (i1, i2) -> i1 + i2;
// Associative
BinaryOperator<Integer> op2 = (i1, i2) -> Integer.max(i1, i2);
// NOT Associative
BinaryOperator<Integer> op3 = (i1, i2) -> i1 * i1 + i2 * i2;
// Associative
BinaryOperator<Integer> op4 = (i1, i2) -> i1;
// NOT Associative
BinaryOperator<Integer> op4 = (i1, i2) -> (i1 + i2) / 2;
```
### Optional

A new concept *Optional*, a wrapper type that maybe empty.

## Stream API

From a technical point of view, Stream is a typed interface.

```java
public interface Stream<T> extends BaseStream<T, Stream<T>> {
    // ...
}
```

A Stream does not hold any data, it **pulls** the data processes from a source, and a Stream does not modifiy the data it processes, and the size of source is not known at the build time.


### Build stream

Several patterns to build stream: *stream()*, *empty()*, *of()*, *generate()*, *iterate()*, *current()*, *chars()*, *Stream.builder()*

Example: 
```java
List<Person> people = ...;
Stream<Person> stream = people.stream();
Stream.empty();
Stream.of("one", "two", "three");
Stream.generate(() -> "one");
Stream.iterate("+", s -> s + "+");
ThreadLocalRandom.current().ints();
IntStream stream = "hello".chars();
Stream<String> words = Pattern.compile("[^\\p{javaLetter}]").splitAsStream(book);
Stream<String> lines = Files.lines(path);
Stream.Builder<String> builder = Stream.builder();
builder.add("one").add("two").add("three");
builder.accept("four");
Stream<String> stream = builder.build();
stream.forEach(System.out::println);
```

### Map/filter/reduce

Example:
```java
persons.stream()                // Stream<Person>
    .map(p -> p.getAge())       // Stream<Integer>
    .filter(age -> age > 20)    // Stream<Integer>
    .forEach(System.out::println);
```

The map() call can change the type of a stream;
The filter() call does not change the type of a stream;

### Intermediate and Terminal calls

A termnial operation must be called to trigger the processing of a Stream.
If there is no terminal operation, then no data will be proceed.

Normally:
- A call that returns a Stream is an intermediate call;
- A call that returns something else, or void is a terminal call that triggers the processing.

Example:
```java
persons.stream()
    .map(p -> p.getAge())
    .forEach(System.out::println) // !!! DOES NOT COMPILE !!!
    .filter(age -> age > 20)
    .forEach(System.out::println);
```

### Method: skip() and limit()

Example:

```java
persons.stream()
    .skip(2)
    .limit(3)
    .filter(person -> person.getAge() > 20)
    .forEach(System.out::println); // triggers the computation
```

### Match Reductions

Match reduction are terminal operations that return a boolean.

Three types of matchers: *anyMatch(), allMatch() and noneMatch()*.

```java
booleanb = people.stream()
    .anyMatch(p -> p.getAge() > 20);
```

### Find Reduction

There are two types of find reduction: findAll() and findAny().

They may have nothing to return so it returns an Optional:
- If Stream is empty;
- Or there is no value that matches the predicate

```java
List<Person> people= ...;
Optional<Person> opt = people.stream()
    .findFirst(p -> p.getAge() > 20);
```

### Reduce Reduction

There are three types of reduce reduction.

First one, returns the result:   
```java
intmaxOfAges= people.stream()
    .reduce(0, (p1, p2) -> Integer.max(p1.getAge(), p2.getAge()));
```

Second version returns Optional: 
```java
List<Person> people= ...;
Optional<Integer> opt= people.stream()
    .reduce((p1, p2) -> Integer.max(p1.getAge() + p2.getAge()));
```

Third one, used in parallel operations:   

```java
List<Person> people= ...;
List<Integer> ages= people.stream()
    .reduce(
        newArrayList<Integer>(),
        (list, p) -> { list.add(p.getAge()) ; returnlist;},
        (list1, list2) -> { list1.addAll(list2) ; returnlist1 ; }
        );
```

### Spliterator

The **Spliterator** is the object which a Stream is built, and it models the access to the data for a Stream.

```java
default Stream<E> stream() {
    return StreamSupport.stream(spliterator(), false);
}
default Spliterator<E> spliterator() { 
    retrun Spliterators.spliterator(this, 0);
}
```

The Spliterator is different is different collection. For instance: 
```java
// ArrayList
public Spliterator<E> spliterator() {
    return new ArrayListSpliterator<>(this, 0, -1, 0);
}
// Hashset
public Spliterator<E> spliterator() {
    return new HashMap.KeySpliterator<E, Object>(map, 0, -1, 0, 0);
}
```

A *Stream* is divived into two things:
- An object to access data (Spliterator). And it can be overriden to suit needs.
- An object to handle the processing of the data. That is the ReferencedPipeline. It is a very complex object, normally won't override it.

To build own Spliterator, examine the following interface:
```java
public interface Spliterator<T> Spliterator {
    public static final intORDERED= 0x00000010;
    public static final intDISTINCT= 0x00000001;
    public static final intSORTED= 0x00000004;
    public static final intSIZED= 0x00000040;
    public static final intNONNULL= 0x00000100;
    public static final intIMMUTABLE= 0x00000400;
    public static final intCONCURRENT = 0x00001000;
    public static final intSUBSIZED = 0x00004000;

    boolean tryAdvance(Consumer<? super T> action);
    Spliterator trySplit();
    long estimateSize();
    int characteristics();
    defaultvoidforEachRemaining(Consumer<? superT> action) {
        do{ } while(tryAdvance(action));
    }
    defaultlonggetExactSizeIfKnown() {
        return(characteristics() & SIZED) == 0 ? -1L : estimateSize();
    }
    defaultbooleanhasCharacteristics(intcharacteristics) {
        return(characteristics() & characteristics) == characteristics;
    }
}
```

The implementation of Spliterator in ArrayList.

```java
staticfinal class ArrayListSpliterator<E> implementsSpliterator<E> {
    private final ArrayList<E> list;
    private int index; // current index, modified on advance/split
    private int fence; // -1 until used; then one past last index
    private int expectedModCount; // initialized when fence set
    public long estimateSize() {
        return(long) (getFence() -index);
    }
    public boolean tryAdvance(Consumer<? superE> action) {
        int hi = getFence(), i = index;
        if(i < hi) {
            index = i+ 1;
            E e = (E)list.elementData[i];
            action.accept(e);
            return true;
        }
        return false;
    }
    public ArrayListSpliterator<E> trySplit() {
        int hi = getFence(), lo = index, mid = (lo+ hi) >>> 1;
        return (lo >= mid) ? null: // divide range in half unless too small
            new ArrayListSpliterator<E>(list, lo, index = mid,expectedModCount);
    }
}
```

### Concatenating Streams

