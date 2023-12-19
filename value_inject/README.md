# springboot value annotation explore


## 1. 简介

Spring Boot 2.0 引入了 `@Value` 注解，可以直接在 `@ConfigurationProperties` 中使用，可以直接将属性值注入到属性中。

## 2. 示例

```java
@ConfigurationProperties(prefix = "person")
public class Person {

    private String name;

    private int age;

    public String getName() {
        return name;
    }
}

## 3. 自定义类型编辑器
