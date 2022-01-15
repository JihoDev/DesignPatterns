package jihogrammer.design_patterns.factory_method.factory;

class WhiteShipFactory implements ShipFactory {

    @Override
    public Ship createShip() {
        return new WhiteShip();
    }

}
