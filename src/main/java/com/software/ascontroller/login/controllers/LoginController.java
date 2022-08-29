package com.software.ascontroller.login.controllers;

import com.software.ascontroller.language.LanguageEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String login(Model model,
                        @RequestParam(value = "error", required = false, defaultValue = "false") Boolean loginError) {
        List<LanguageEnum> languages = new ArrayList<>(EnumSet.allOf(LanguageEnum.class));
        model.addAttribute("languages",languages);
        if(loginError) {
            model.addAttribute("messageError", true);
        }
        return "/login/index";
    }
}
