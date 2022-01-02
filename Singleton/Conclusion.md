###### [Design Patterns of GoF](../README.md) / [Singleton](./README.md)

# Conclusion of Singleton pattern

## Exercise

-   Java에서 `enum`을 사용하지 않고 Singleton 패턴을 구현하는 방법은?
-   `private` 생성자와 `static` 메서드를 사용하는 방법의 단점은?
-   `enum`을 사용해 Singleton 패턴을 구현하는 장단점은?
-   `static inner` 클래스를 사용해 Singleton 패턴을 구현하라.

## Conclusion

### 1. `Runtime`

대표적으로 Java의 Runtime 객체가 싱글톤 패턴으로 구성되어 있다.
Eager Initialize 방식으로 되어 있다.

### 2. in Spring

Bean 또한 내부적으로 싱글톤 패턴으로 구성된다.

##### SpringConfig.java
```java
@Configuration
public class SpringConfig {

    @Bean
    public String hello() {
        return "Hello";
    }

}
```

##### App.java
```java
public class App {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        String hello1 = context.getBean("hello", String.class);
        String hello2 = context.getBean("hello", String.class);

        Assert.isTrue(hello1 == hello2, "There are not same.");

        ((ConfigurableApplicationContext) context).close();

    }

}
```

`String` 타입이라 찝찝하긴 하지만 실제로 동일한 인스턴스를 반환한다.
하지만 Bean 방식은 엄밀히 말하자면 Singleton으로 볼 수 없다.
Spring 환경에서 Singleton을 간단하게 사용할 때 사용하는 정도로 보면 된다.

-   Spring에서 Bean의 Scope 중 하나로 Singleton Scope
-   `java.lang.Runtime`
-   다른 Design Patterns(Builder, Facade, Abstract Factory)
    구현체의 일부로 쓰임

---

##### *Index*

1.  [개요](README.md)
2.  [구현 방법](Implementation.md)
3.  [싱글톤 패턴이 깨지는 경우와 막는 방법](BreakingPrevention.md)
4.  [결론](#conclusion)
