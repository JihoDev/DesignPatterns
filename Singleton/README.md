###### [Design Patterns of GoF](../README.md)

# Singleton

###### 싱글톤 패턴 - 인스턴스를 오직 한 개만 제공하는 클래스

시스템 런타임, 환경 설정에 대한 정보 등,
인스턴스가 여러 개일 때 문제가 생길 수 있는 경우가 있다.
인스턴스를 오직 한 개만 만들어 제공하는 클래스가 필요하다.

```
,------------------------.
|Singleton               |
|------------------------|
|Singleton instance;     |
|Singleton getInstance();|
`------------------------'
```

>   설정과 같이 매번 새로운 객체가 생성되면 안 될 경우에 사용한다.
>   생성자 메서드를 명시적으로 private로 바꿔서 사용한다.

##### App.java
```java
public class App {
    public static void main(String[] args) {
        Settings settings1 = new Settings();
        Settings settings2 = new Settings();
        System.out.println(settings1 == settings2); // false
    }
}
```

위와 같이 작성할 경우 당연히 `settings1`과 `settings2`는 다른 객체이기 때문에
false를 출력한다.

>   객체를 얻을 때는 다음과 같이 사용한다.

##### Settings.java
```java
public class Settings {
    private Settings() {}
    public static Settings getInstance() {
        return new Settings();
    }
}
```

##### App.java
```java
public class App {
    public static void main(String[] args) {
        Settings settings1 = Settings.getInstance();
        Settings settings2 = Settings.getInstance();
        System.out.println(settings1 == settings2); // false
    }
}
```

그러나 위처럼 사용하더라도 매번 새로운 객체를 생성해서 반환하기 때문에
둘은 다른 객체이다.

프로세스 상에서 전역으로 오직 하나의 객체를 사용하려면 다음과 같이 작성한다.

##### Settings.java
```java
public class Settings {
    private static Settings instance;
    private Settings() {}
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
}
```

##### App.java
```java
public class App {
    public static void main(String[] args) {
        Settings settings1 = Settings.getInstance();
        Settings settings2 = Settings.getInstance();
        System.out.println(settings1 == settings2); // true
    }
}
```

이렇게 사용할 경우 오직 하나의 객체만 생성하고 접근할 수 있다.

하지만, 이 경우에는 멀티스레드 환경에서 문제가 될 수 있다.
이 방법은 멀티스레드 환경에서 안전하지 않다.

>   위 방법은 ***Thread Safe***가 보장되지 않는다.

---

##### *Index*

1.  [개요](#singleton)
2.  [구현 방법](Implementation.md)
3.  [싱글톤 패턴이 깨지는 경우와 막는 방법](BreakingPrevention.md)
4.  [결론](Conclusion.md)
