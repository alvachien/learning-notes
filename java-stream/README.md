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

## Stream

