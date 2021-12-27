package jihogrammer.design_patterns.singleton.synchronized_keyword;

import org.springframework.util.Assert;

public class App {

    public static void main(String[] args) {

        Settings settings1 = Settings.getInstance();
        Settings settings2 = Settings.getInstance();

        Assert.isTrue(settings1 == settings2, "These are not same");

    }

}
