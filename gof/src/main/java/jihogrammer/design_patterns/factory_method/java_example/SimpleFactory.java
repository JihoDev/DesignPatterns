package jihogrammer.design_patterns.factory_method.java_example;

import jihogrammer.design_patterns.factory_method.factory.BlackShip;
import jihogrammer.design_patterns.factory_method.factory.WhiteShip;

public class SimpleFactory {

    public Object createProduct(String name) {
        if (name.equals("whiteship")) {
            return new WhiteShip();
        } else if (name.equals("blackship")) {
            return new BlackShip();
        }

        throw new IllegalArgumentException();
    }
}
