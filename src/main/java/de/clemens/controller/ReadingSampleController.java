package de.clemens.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReadingSampleController {

    @GetMapping("/reading-sample")
    public String getter() {
        return "sample";
    }
}
