package ntukhpi.semit.dde.CommonSpring2023.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    // handler method to handle list students and return mode and view
    @GetMapping("/")
    public String helloPage(Model model) {

        return "hellopage";
    }
}
