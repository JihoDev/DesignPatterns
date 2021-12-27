package jihogrammer.design_patterns.singleton.break_way.reflection_api;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.springframework.util.Assert;

public class App {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Settings settings1 = Settings.getInstance();

        Constructor<Settings> constructor = Settings.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Settings settings2 = constructor.newInstance();

        Assert.isTrue(settings1 != settings2, "These are same instances.");

    }

}
