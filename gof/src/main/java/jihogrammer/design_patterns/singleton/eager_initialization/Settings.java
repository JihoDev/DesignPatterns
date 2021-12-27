package jihogrammer.design_patterns.singleton.eager_initialization;

public class Settings {

    private static Settings instance = new Settings();

    private Settings() {}

    public static Settings getInstance() {
        return instance;
    }

}
