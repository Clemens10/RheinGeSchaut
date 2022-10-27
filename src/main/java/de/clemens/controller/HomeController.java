package de.clemens.controller;

import de.clemens.util.ServerInfoUtil;
import de.clemens.util.auth.RegistrationUser;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String main() {

        return "redirect:/home";
    }

    @GetMapping(value = "/home")
    public String home(@NotNull Model model) {

        int onlinePlayers = ServerInfoUtil.getOnlinePlayers("GommeHD.net", 25565);
        System.out.println(onlinePlayers);
        model.addAttribute("onlineCount", onlinePlayers);
        model.addAttribute("registrationUser", new RegistrationUser());

        return "home";
    }

    //Gema
}
