package jihogrammer.design_patterns.factory_method.abstract_way;

class WhiteShipFactory extends DefaultShipFactory {

    @Override
    public Ship createShip() {
        return new WhiteShip();
    }

}
