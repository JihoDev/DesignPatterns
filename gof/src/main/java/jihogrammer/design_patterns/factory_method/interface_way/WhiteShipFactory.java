package jihogrammer.design_patterns.factory_method.interface_way;

class WhiteShipFactory implements ShipFactory {

    @Override
    public Ship createShip() {
        return new WhiteShip();
    }

}
