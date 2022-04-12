# spring-boot-fundamentals

It is an example project for Spring Boot Fundamentasl.

# Key Takeaways

## Auto-configuration

**Auto-configuration** is a very useful and powerful feature of Spring Boot, which takes a "convention-over-configuration" approach.

### Auto-configuration insights

Check auto-configuration:
- Start application with --debug switch.
- Add a simply property to application.properties

```ini
logging.level.org.springframework = DEBUG
```

- Use the Spring Boot Actuator

## Common application properties

[Spring Boot Docs](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html)


## @SpringBootApplication Annotation

SpringBootApplication annotation is a combination as following:
- @SpringBootConfiguration
- @EnableAutoConfiguration
- @ComponentScan

## Spring Boot Profiles

To enable profile, add following line:

```ini
spring.profiles.active=dev
```

## H2 Defaults

H2 is a Java based in-memory database.

```ini
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=false
```

## Spring Data JPA

Spring Data JPA (with Hibernate)
    ||
Dava Presistence API (JPA)
    ||
Java Database Connectivity (JDBC)
    ||
Real Database


### Entities

Entities are objects lives in database table, and they are mapping to the databse.



