package jihogrammer.design_patterns.factory_method.factory;

interface ShipFactory {

    default Ship orderShip(String name, String email) {
        validate(name, email);
        prepareFor(name);
        Ship ship = createShip();
        sendEmailTo(email, ship);
        return ship;
    }

    Ship createShip();

    private void validate(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("배 이름을 지어주세요.");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("연락처를 남겨주세요.");
        }
    }

    private static void prepareFor(String name) {
        System.out.println(name + " 제작 중");
    }

    private static void sendEmailTo(String email, Ship ship) {
        System.out.println(ship.getName() + " 제작이 완료되었습니다.");
    }

}

// class ShipFactory {

//     static Ship orderShip(String name, String email) {
//         // validate
//         if (name == null || name.isBlank()) {
//             throw new IllegalArgumentException("배 이름을 지어주세요.");
//         }
//         if (email == null || email.isBlank()) {
//             throw new IllegalArgumentException("연락처를 남겨주세요.");
//         }

//         prepareFor(name);

//         Ship ship = new Ship();
//         ship.setName(name);

//         // Customizing for specific name
//         if (name.equalsIgnoreCase("whiteship")) {
//             ship.setLogo("\uD83D\uDEE5");
//         } else if (name.equalsIgnoreCase("blackship")) {
//             ship.setLogo("⚓️");
//         }

//         // coloring
//         if (name.equalsIgnoreCase("whiteship")) {
//             ship.setColor("white");
//         } else if (name.equalsIgnoreCase("blackship")) {
//             ship.setColor("black");
//         }

//         // notify
//         sendEmailTo(email, ship);

//         return ship;
//     }

//     private static void prepareFor(String name) {
//         System.out.println(name + " 제작 중");
//     }

//     private static void sendEmailTo(String email, Ship ship) {
//         System.out.println(ship.getName() + " 제작이 완료되었습니다.");
//     }

// }
