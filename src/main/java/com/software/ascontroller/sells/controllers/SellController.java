package com.software.ascontroller.sells.controllers;

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
@RequestMapping("/sells")
public class SellController {

    @GetMapping
    public String sells(Model model,
                        Authentication authentication) {

        return "/sells/sells";
    }

    private void loadSellsScreen(Model model,
                            Authentication authentication) {

        this.loadCommonAtributtesNavbar(model, authentication);

    }

    private void loadCommonAtributtesNavbar(Model model,
                                            Authentication authentication) {
        List<LanguageEnum> languages = new ArrayList<>(EnumSet.allOf(LanguageEnum.class));
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        model.addAttribute("idUser", user.getIdUser());
        model.addAttribute("languages",languages);
        model.addAttribute("navLink", "sells");
    }
}
