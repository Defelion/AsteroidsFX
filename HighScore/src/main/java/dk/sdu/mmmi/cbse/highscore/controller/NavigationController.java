package dk.sdu.mmmi.cbse.highscore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @GetMapping("/index-page")
    public String getHomepage() {
        return "index";
    }
}
