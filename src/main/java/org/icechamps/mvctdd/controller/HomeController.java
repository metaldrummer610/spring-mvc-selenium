package org.icechamps.mvctdd.controller;

import lombok.extern.slf4j.Slf4j;
import org.icechamps.mvctdd.model.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        log.info("index");
        model.addAttribute("greeting", new Greeting());
        return "index";
    }

    @PostMapping("/")
    public String greeting(@ModelAttribute Greeting greeting) {
        log.info("greeting: {}", greeting);
        return "index";
    }
}
