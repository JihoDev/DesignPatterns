package jihogrammer.design_patterns.factory_method.factory;

class BlackShipFactory implements ShipFactory {

    @Override
    public Ship createShip() {
        return new BlackShip();
    }

}
