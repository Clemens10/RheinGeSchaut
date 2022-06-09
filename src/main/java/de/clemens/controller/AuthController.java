package de.clemens.controller;

import de.clemens.util.auth.RegistrationUser;
import de.clemens.util.auth.RegistrationUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {

    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("registrationUser") RegistrationUser registrationUser) {

        RegistrationUtil.register(registrationUser);
        return "redirect:/home";
    }
}
