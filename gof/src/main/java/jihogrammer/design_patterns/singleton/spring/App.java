package jihogrammer.design_patterns.singleton.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

public class App {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        String hello1 = context.getBean("hello", String.class);
        String hello2 = context.getBean("hello", String.class);

        Assert.isTrue(hello1 == hello2, "There are not same.");

        ((ConfigurableApplicationContext) context).close();

    }

}
