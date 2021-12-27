package jihogrammer.design_patterns.singleton.break_way.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.springframework.util.Assert;

class AvoidSerailizationSettings implements Serializable {

    private AvoidSerailizationSettings() {}

    private static class SettingsHolder {
        private static final AvoidSerailizationSettings INSTANCE = new AvoidSerailizationSettings();
    }

    public static AvoidSerailizationSettings getInstance() {
        return SettingsHolder.INSTANCE;
    }

    // 역직렬화 대응방안
    protected Object readResolve() {
        return getInstance();
    }

}

public class AvoidSerialization {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

        AvoidSerailizationSettings settings1 = AvoidSerailizationSettings.getInstance();
        AvoidSerailizationSettings settings2 = null;

        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("settings1.obj"))) {
            out.writeObject(settings1);
        }

        try (ObjectInput in = new ObjectInputStream(new FileInputStream("settings1.obj"))) {
            settings2 = (AvoidSerailizationSettings) in.readObject();
        }

        Assert.isTrue(settings1 == settings2, "These are not same instances.");

    }

}
