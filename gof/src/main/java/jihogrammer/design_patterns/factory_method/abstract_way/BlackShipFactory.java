package jihogrammer.design_patterns.factory_method.abstract_way;

class BlackShipFactory extends DefaultShipFactory {

    @Override
    public Ship createShip() {
        return new BlackShip();
    }

}
