###### [Design Patterns of GoF](../README.md) / [Singleton](./README.md)

# How to implement a Thread Safe Singleton instance?

-   [Lazy Initialization](#1-lazy-initialization-using-synchronized-keyword) <!-- -->
-   [Eager Initialization](#2-eager-initialization-with-final-keyword)
-   [Double-checked Locking](#3-double-checked-locking-with-volatile)
-   [Holder Idiom](#4-holder-idiom---static-inner-class)

## 1. *Lazy Initialization* using `synchronized` keyword

##### 동기화: 제일 간단한 방법

#### Settings.java
```java
public class Settings {
    private static Settings instance;
    private Settings() {}
    // 한 번에 하나의 Thread만 접근할 수 있도록 메서드를 수정
    public static synchronized Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
}
```

간단하지만, 동기화 처리로 성능에 영향을 미친다.

## 2. *Eager Initialization* with `final` keyword

##### 이른 초기화

프로세스 구동 시부터 미리 초기화하는 방법

#### Settings.java
```java
public class Settings {
    // static field에 final 키워드까지 사용하여 미리 초기화
    // 상수가 되었기 때문에 상수 컨벤션에 맞게 대문자로 변수 이름을 변경
    private static final Settings INSTANCE = new Settings();
    private Settings() {}
    public static Settings getInstance() {
        return INSTANCE;
    }
}
```

단점은 미리 만드는 것 자체가 단점이 될 수 있다.
여러 기능에 비해 잘 사용되지 않는 기능일 경우에 계속 메모리를 쥐고 있다.

## 3. Double-checked Locking with `volatile`

##### 두 번 검사하되 `synchronized`를 조금 더 구체적으로 사용

#### Settings.java
```java
public class Settings {
    // volatile 키워드를 붙여주어야만 실질적으로 동작한다(Java 1.5 이상).
    private static volatile Settings instance;
    private Settings() {}
    public static Settings getInstance() {
        if (instance == null) {
            synchronized (Settings.class) {
                if (instance == null) {
                    instance = new Settings();
                }
            }
        }
        return instance;
    }
}
```

1번 동기화 방식과의 차이점은 처음 진입 시부터 lock이 걸리지 않고,
객체가 없을 경우에만 lock에 걸리게 함으로써
1, 2번에서 언급한 두 단점을 극복할 수 있다.

정리하면 Thread Safe를 보장하고, 인스턴스가 필요한 시점에서야 새로운 객체를 생성한다.

하지만 이 방법은 굉장히 복잡하다. `volatile`를 사용하는 이유를 정리하기 힘들다.
또한 1.4 이하 버전을 사용할 경우 위와 같은 방식으로 구현할 수 없다.

>   현 시점에서 1.4 이하 버전을 사용할 경우는 극히 드물겠지만.

## 4. Holder idiom - Static Inner Class

##### 기선 픽 - 권장하는 방법

#### Settings.java
```java
public class Settings {

    private Settings() {}

    private static class SettingsHolder {
        private static final Settings INSTANCE = new Settings();
    }

    public static Settings getInstance() {
        return SettingsHolder.INSTANCE;
    }

}
```

1~3 방법의 장점들을 모두 흡수했다고 볼 수 있다.

>   사실 아직 정확한 원리는 모르겠다.

하지만 위와 같은 구현 방식을 깨트리게 하는 경우가 있다.

---

<p align="center">
    <a href="README.md">prev</a>
    &nbsp; &nbsp; &nbsp; | &nbsp; &nbsp; &nbsp;
    <a href="#how-to-implement-a-thread-safe-singleton-instance">
        How to implement a Singleton class
    </a>
    &nbsp; &nbsp; &nbsp; | &nbsp; &nbsp; &nbsp;
    <a href="PreventBreaking.md">next</a>
</p>

---

##### *Index*

1.  [개요](README.md)
2.  [구현 방법](#how-to-implement-a-thread-safe-singleton-instance)
3.  [싱글톤 패턴이 깨지는 경우와 막는 방법](BreakingPrevention.md)
4.  [결론](Conclusion.md)
