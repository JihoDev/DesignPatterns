package jihogrammer.design_patterns.factory_method.interface_way;

class Client {

    public static void main(String[] args) {

        Client client = new Client();

        // Dependency Injection
        client.concrete(new WhiteShipFactory(), "whiteShip", "jih@gram.er");
        client.concrete(new BlackShipFactory(), "blackShip", "jih@gram.er");

    }

    private void concrete(ShipFactory factory, String name, String email) {
        System.out.println(factory.orderShip(name, email));
    }

}
