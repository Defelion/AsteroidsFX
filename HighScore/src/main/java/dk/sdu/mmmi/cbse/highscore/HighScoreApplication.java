package dk.sdu.mmmi.cbse.highscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class HighScoreApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HighScoreApplication.class, args);
    }

}
