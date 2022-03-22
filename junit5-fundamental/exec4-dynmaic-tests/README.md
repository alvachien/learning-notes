# exec4-dynamic-tests

Maven project:
- Java 11
- JUnit 5


# Key takeaway

## Dynamic Tests

### Annotation @TestFactory

Factory of tests.

The method must not private or static.

### Sources

Collection
Iterable
Iterator
Stream
Array

DynamicNode (Abstract)
- DynamicContainer. A container of Dynamic Test.
- DynamicTest

@BeforeEach and @AfterEach won't be executed for dynamic tests.

## Parameterized Tests

### Setup

### Argument sources

### Argument conversion


