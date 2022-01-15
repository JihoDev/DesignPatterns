###### [Design Patterns of GoF](../README.md)

# Factory Method

###### 팩토리 메서드 패턴 - 구체적으로 어떤 인스턴스를 만들지는 서브 클래스가 정한다.

다양한 구현체(Product)가 있고,
그 중에서 특정한 구현체를 만들 수 있는 다양한 팩토리(Creator)를 제공할 수 있다.

```
,----------------------------.
|      << interface >>       |             ,-----------------.
|          Creator           |     Use     | << interface >> |
+----------------------------+ - - - - - > |     Product     |
| + templateMethod()         |             `-----------------'
| + createProduct(): Product |                      ^
`----------------------------'                      |
              ^                                     |
              |                                     |
,----------------------------.                      |
|       ConcreteCreator      |             ,-----------------.
+----------------------------+             | ConcreteProduct |
| + createProduct(): Product |             `-----------------'
`----------------------------'
```

>   싱글톤은 이론 반 실습 반이었다면,
>   팩토리는 실습 위주의 방식이라 정리할 내용이 적은데,,,

>   팩토리 패턴은 사실 막 엄청나게 어렵지는 않다.
>   추상화를 얼추 해두고, 기존 코드를 수정하지 않고
>   최대한 확장성을 보장하게끔 하는 방식이다.
>   다만 상속의 맥락을 잘 이해하고, 추상화와 구체화를 알아야 실제 적용할 수 있는 개념이다.

>   ### ***수정은 막고, 확장은 연다.***

## Question

-   팩토리 메서드 패턴을 적용했을 때의 장단점은?
-   "확장에 열려있고, 변경에 닫혀있는 객체지향 원칙"을 설명하라.
-   Java 8에 추가된 `defuault` 메서드에 대해 설명하라.

### Answer

#### 장단점은?

>   기존에 인스턴스를 만드는 과정의 로직을 건들이지 않고,
>   새로운 유사한 인스턴스를 만드는 방식으로 언제든지 확장이 가능하다.
>   `Product`와 `Creator`의 Coupling을 느슨(loose)하게 설계했기 때문에 가능하다.
>   더불어 유지보수 및 복잡성을 줄일 수 있다.
> 
>   반면, 팩토리 패턴을 적용하게 될 경우 관리해야 하는 Class의 수가 상당히 증가한다.

#### *"확장에 열려있고, 변경에 닫혀있는 객체지향 원칙"*

>   ***OCP, Open-Closed Principle***
>   (*[Wikipedia](https://en.wikipedia.org/wiki/Open%E2%80%93closed_principle)*)

#### `default` of Java 8

>   Java 8부터 interface에 abstract class의 기능을 담게 되면서,
>   abstract class를 잘 사용하지 않게 되었다.

## 실무에서는 어떻게 쓰이나

-   단순한 팩토리 패턴
    -   매개변수의 값에 따라 또는 메서드에 따라 각기 다른 인스턴스를 리턴하는 단순한 버전의 팩토리 패턴
    -   `java.lang.Calendar` 또는 `java.lang.NumberFormat`
-   Spring `BeanFactory`
    -   `Object` 타입의 `Product`를 만드는 `BeanFactory`라는 `Creator`
