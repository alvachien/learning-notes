# exec5-extend-junit

Maven project
- Java 11
- JUnit 5


# Key takeaway

## Extension points

### Maker Interface

### One extension point

TestInstanceFactory
TestInstancePostProcessor
TestInstancePreDestroyCallback
TestWatcher
InvocationInterceptor
TestTemplateInvocationContextProvider
ParameterResolver
TestExecutionExceptionHandler
Condition
    ExecutionCondition
Lifecycle Callbacks
    BeforeAllCallback/AfterAllCallback
    BeforeEachCallback/AfterEachCallback
    BeforeTestExecutionCallback/AfterTestExecutionCallback
Extensino Registration
    Declaratively: @ExtendWith
    Programmatically: @RegisteryExtension
    Automatically
        java.util.ServiceLoader mechanism
            /META-INF/services
            org.junit.jupiter.api.extension.Extension


### Called by Jupiter

## Parameter injection

Test information parameters
- RepetitionInfo
- TestInfo
- TestReporter

ParameterResolver
- RepetitionInfoParameterResolver
- TestInfoParameterResolver
- TestReporterParameterResolver

```java
boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException;
Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException;

```

### Mthods that can have parameters injected

@Test
@TestFactory
Lifcycle Methods
Constructor

## Meta-annotations

## keeping state

## Sample extensions

## Github repos for JUnit

Name: junit-team
Repo: junit5-samples

