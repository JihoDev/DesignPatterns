package jihogrammer.design_patterns.factory_method.abstract_way;

class Client {

    public static void main(String[] args) {

        Client client = new Client();

        // Dependency Injection
        client.concrete(new WhiteShipFactory(), "WhiteShip", "jih@gram.er");
        client.concrete(new BlackShipFactory(), "BlackShip", "jih@gram.er");

    }

    private void concrete(ShipFactory factory, String name, String email) {
        System.out.println(factory.orderShip(name, email));
    }

}
