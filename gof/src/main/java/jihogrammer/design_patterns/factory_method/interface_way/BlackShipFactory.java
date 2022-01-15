package jihogrammer.design_patterns.factory_method.interface_way;

class BlackShipFactory implements ShipFactory {

    @Override
    public Ship createShip() {
        return new BlackShip();
    }

}
