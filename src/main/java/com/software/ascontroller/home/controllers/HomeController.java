package com.software.ascontroller.home.controllers;

import com.software.ascontroller.language.LanguageEnum;
import com.software.ascontroller.user.customUserDetails.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping
    public String index(Model model,
                      Authentication authentication) {
        loadHomeScreen(model, authentication);

        return "index";
    }

    private void loadHomeScreen(Model model, Authentication authentication) {
        List<LanguageEnum> languages = new ArrayList<>(EnumSet.allOf(LanguageEnum.class));
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        model.addAttribute("languages",languages);
        model.addAttribute("username", user.getName());
        model.addAttribute("navLink","home");
    }

    @GetMapping("/home")
    public String login (Model model, Authentication authentication) {
        loadHomeScreen(model, authentication);
        return "/home/index";
    }


}
