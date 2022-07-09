package de.clemens.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReadingSampleController {

    @GetMapping("sample")
    public String a() {
        return "sample/sample";
    }
}
