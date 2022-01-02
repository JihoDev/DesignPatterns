###### [Design Patterns of GoF](../README.md) / [Singleton](./README.md)

# Breaking & Prevention

## Breaking Singleton

### 1. Reflection API

###### Class 조작

##### App.java
```java
import java.lang.reflect.*;

public class App {

    public static void main(String[] args) throws Exception {

        Settings settings1 = Settings.getInstance();

        Constructor<Settings> constructor = Settings.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Settings settings2 = constructor.newInstance();

        System.out.println(settings1 == settings2); // false

    }

}
```

#### 2. Serialization

###### 직렬화/역직렬화

##### Settings.java
```java
public class Settings implements Serializable {}
```

##### App.java
```java
public class App {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

        Settings settings1 = Settings.getInstance();
        Settings settings2 = null;

        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("settings1.obj"))) {
            out.writeObject(settings1);
        }

        try (ObjectInput in = new ObjectInputStream(new FileInputStream("settings1.obj"))) {
            settings2 = (Settings) in.readObject();
        }

        Assert.isTrue(settings1 != settings2, "These are same instances.");

    }

}
```

위와 같은 방법들로 Singleton 패턴을 무마시킬 수 있다.
그래도 Serialization의 경우에는 비교적 쉽게 막을 수 있다.

## Prevention of breaking singleton

### 1. Serialization

###### Settings.java
```java
class Settings implements Serializable {

    // ...

    // 역직렬화 대응방안 - 직렬화 시 사용되는 내장 메서드
    protected Object readResolve() {
        return getInstance();
    }

}
```

### 2. Reflection API

###### enum

##### Settings.java
```java
public enum Settings {
    INSTANCE;
}
```

위처럼 사용하고 내부에 필요한 로직을 작성할 수 있다.
Reflection을 막을 수 있지만, Eager Initialization처럼 미리 객체를 생성하기 때문에
메모리를 원하는 시점에 생성할 수 없다. 또 다른 단점은 enum은 상속이 불가하다.

>   enum은 이미 `Serializable`을 상속하고 있어 역직렬화에 어차피 안전하다.

---

##### *Index*

1.  [개요](README.md)
2.  [구현 방법](Implementation.md)
3.  [싱글톤 패턴이 깨지는 경우와 막는 방법](#breaking--prevention)
4.  [결론](Conclusion.md)
