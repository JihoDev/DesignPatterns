###### Design Patterns of GoF

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

##### Settings.java
```java
public class Settings {
    private Settings() {}
}
```

##### App.java
```java
public class App {
    public static void main(String[] args) {
        Settings settings1 = new Setting();
        Settings settings2 = new Setting();
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
    private Setting() {}
    public static Settings getInstance() {
        return new Settings();
    }
}
```

##### App.java
```java
public class App {
    public static void main(String[] args) {
        Settings settings1 = Setting.getInstance();
        Settings settings2 = Setting.getInstance();
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
    private Setting() {}
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
        Settings settings1 = Setting.getInstance();
        Settings settings2 = Setting.getInstance();
        System.out.println(settings1 == settings2); // true
    }
}
```

이렇게 사용할 경우 오직 하나의 객체만 생성하고 접근할 수 있다.

하지만, 이 경우에는 멀티스레드 환경에서 문제가 될 수 있다.
이 방법은 멀티스레드 환경에서 안전하지 않다.

>   위 방법은 ***Thread Safe***가 보장되지 않는다.

## How to implement Thread Safe Singleton instance

### 1. Using `synchronized` keyword.

###### 동기화: 제일 간단한 방법

##### Settings.java
```java
public class Settings {
    private static Settings instance;
    private Setting() {}
    // 한 번에 하나의 Thread만 접근할 수 있도록 메서드를 수정
    public static synchronized Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }
}
```

하지만 이 방법에도 단점이 있다.
동기화 처리로 인해 성능에 영향을 미친다.

### 2. Eager Initialization with `final` keyword

###### 이른 초기화

프로세스 구동 시부터 미리 초기화하는 방법

##### Settings.java
```java
public class Settings {
    // static field에 final 키워드까지 사용하여 미리 초기화
    // 상수가 되었기 때문에 상수 컨벤션에 맞게 대문자로 변수 이름을 변경
    private static final Settings INSTANCE = new Settings();
    private Setting() {}
    public static Settings getInstance() {
        return INSTANCE;
    }
}
```

단점은 미리 만드는 것 자체가 단점이 될 수 있다.
여러 기능들에 비해 잘 사용되지 않는 기능일 경우에 계속 메모리를 쥐고 있다.

### 3. Double Checked Locking with `volatile`

###### 두 번 검사하되 `synchronized`를 조금 더 구체적으로 사용

##### Settings.java
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

### 4. Static Inner Class

###### 기선 픽 1 - 권장하는 방법 중 하나

##### Settings.java
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
