package jihogrammer.design_patterns.factory_method.java_example;

import org.springframework.beans.factory.FactoryBean;

import jihogrammer.design_patterns.factory_method.factory.Ship;
import jihogrammer.design_patterns.factory_method.factory.WhiteShip;

public class ShipFactory implements FactoryBean<Ship> {

    @Override
    public Ship getObject() throws Exception {
        Ship ship = new WhiteShip();
        ship.setName("whiteship");
        return ship;
    }

    @Override
    public Class<?> getObjectType() {
        return Ship.class;
    }
}
