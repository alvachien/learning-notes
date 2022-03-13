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

逻辑上来说，Map的所有的Key就是一个Set。


