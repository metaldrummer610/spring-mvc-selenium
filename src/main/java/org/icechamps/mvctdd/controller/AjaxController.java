package org.icechamps.mvctdd.controller;

import org.icechamps.mvctdd.model.Greeting;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by metal on 9/11/16.
 */
@RestController
public class AjaxController {
    @PostMapping("/ajax")
    public Greeting ajaxGreeting(@RequestBody Greeting greeting) {
        return greeting;
    }
}
