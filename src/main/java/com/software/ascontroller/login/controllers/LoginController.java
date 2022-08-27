package com.software.ascontroller.login.controllers;

import com.software.ascontroller.language.LanguageEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String login(Model model) {
        List<LanguageEnum> languages = new ArrayList<>(EnumSet.allOf(LanguageEnum.class));
        model.addAttribute("languages",languages);
        return "/login/index";
    }
}
