# lesson6-flow-control

Flow control


# Key takeaways

## Switch

switch语句指定一个表达式。这个表达式的值只能是：
- byte
- short
- int
- char
- enum

swtich语句表达式的类型不可以是String类型。 

## foreach循环

foreach循环：

```java
List<String> ars = new ArrayList<String>();
// ...
for(String str: ars) {
    System.out.println(str);
}
```
