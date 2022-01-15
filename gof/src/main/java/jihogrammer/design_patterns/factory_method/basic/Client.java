package jihogrammer.design_patterns.factory_method.basic;

class Client {

    public static void main(String[] args) {

        Ship whiteShip = ShipFactory.orderShip("Whiteship", "jih@gram.er");
        Ship blackShip = ShipFactory.orderShip("Blackship", "jih@gram.er");

        System.out.println(whiteShip);
        System.out.println(blackShip);

    }

}
