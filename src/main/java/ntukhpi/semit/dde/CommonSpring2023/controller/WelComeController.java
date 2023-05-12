package ntukhpi.semit.dde.CommonSpring2023.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelComeController {

//    @GetMapping("/welcome")
//    public String greeting() {
//        return "welcome";
//    }

    @GetMapping("/hellopage")
    public String homepage() {
        return "hellopage";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
