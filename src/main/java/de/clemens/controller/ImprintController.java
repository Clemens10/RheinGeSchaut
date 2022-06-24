package de.clemens.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ImprintController {

    @GetMapping("imprint")
    public String get() {
        return "imprint/imprint";
    }
}
