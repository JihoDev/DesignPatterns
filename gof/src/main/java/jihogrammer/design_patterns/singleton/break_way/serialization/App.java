package jihogrammer.design_patterns.singleton.break_way.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.springframework.util.Assert;

public class App {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

        Settings settings1 = Settings.getInstance();
        Settings settings2 = null;

        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("settings1.obj"))) {
            out.writeObject(settings1);
        }

        try (ObjectInput in = new ObjectInputStream(new FileInputStream("settings1.obj"))) {
            settings2 = (Settings) in.readObject();
        }

        Assert.isTrue(settings1 != settings2, "These are same instances.");

    }

}
