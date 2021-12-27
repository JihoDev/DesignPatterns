package jihogrammer.design_patterns.singleton.break_way.reflection_api.avoid;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.springframework.util.Assert;

public class App {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Settings settings1 = Settings.INSTANCE;
        Settings settings2 = null;

        Constructor<?>[] constructors = Settings.class.getConstructors();
        for (Constructor<?> constructor : constructors) {
            constructor.setAccessible(true);
            settings2 = (Settings) constructor.newInstance("INSTANCE");
        }

        Assert.isTrue(settings1 != settings2, "These are same instances.");

    }

}
