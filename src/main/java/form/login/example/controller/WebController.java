package form.login.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {

    @GetMapping("/")
    public String handleMainPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        String message = "Ho do you do, mister " + userName + "? "
                + "Your authorities: " + auth.getAuthorities();
        model.addAttribute("message", message);
        return "index";
    }

    @GetMapping("/admin")
    public String handleAdmin(Model model) {
        String message = "Hello, master " +
                SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("message", message);
        return "admin";
    }

    @GetMapping("/login")
    public String handleSignIn() {
        return "login";
    }
}