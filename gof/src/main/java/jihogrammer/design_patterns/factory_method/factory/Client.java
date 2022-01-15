package jihogrammer.design_patterns.factory_method.factory;

class Client {

    public static void main(String[] args) {


        Ship whiteShip = new WhiteShipFactory().orderShip("Whiteship", "jih@gram.er");
        Ship blackShip = new BlackShipFactory().orderShip("Blackship", "jih@gram.er");

        System.out.println(whiteShip);
        System.out.println(blackShip);

    }

}
