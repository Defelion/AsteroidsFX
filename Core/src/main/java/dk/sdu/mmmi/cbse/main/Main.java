package dk.sdu.mmmi.cbse.main;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static java.util.stream.Collectors.toList;

/**
 * The type Main.
 */
public class Main extends Application {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AsteroidsServiceConfig.class);
        for (String beanName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }
        mainAsteroids mainAsteroids = ctx.getBean(mainAsteroids.class);
        mainAsteroids.start(window);
        mainAsteroids.render();
    }
}
