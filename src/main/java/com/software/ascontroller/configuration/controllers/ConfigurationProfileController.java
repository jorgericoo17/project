package com.software.ascontroller.configuration.controllers;

import com.software.ascontroller.language.LanguageEnum;
import com.software.ascontroller.user.customUserDetails.CustomUserDetails;
import com.software.ascontroller.user.dtos.UserDTO;
import com.software.ascontroller.user.entites.User;
import com.software.ascontroller.user.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Controller
@RequestMapping("/configuration")
public class ConfigurationProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile/options")
    public String profileOptions(Model model,
                                 Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        model.addAttribute("idUser", userDetails.getIdUser());
        this.loadCommonAtributtesNavbar(model, authentication);
        return "/configuration/profile/options";
    }

    @GetMapping("/nameProfile")
    public String nameProfileConfiguration(Model model,
                                       Authentication authentication) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UserDTO userDTO = new UserDTO();
        User user = userService.findById(userDetails.getIdUser()).get();
        BeanUtils.copyProperties(user, userDTO);
        model.addAttribute("userDTO", userDTO);
        this.loadCommonAtributtesNavbar(model, authentication);

        return "/configuration/profile/nameProfile";
    }

    @GetMapping("/passwdProfile/{idUser}")
    public String passwdProfileConfiguration(Model model,
                                             @PathVariable("idUser") Long idUser,
                                             Authentication authentication) {

        UserDTO userDTO = new UserDTO();
        User user = userService.findById(idUser).get();
        BeanUtils.copyProperties(user, userDTO);
        model.addAttribute("userDTO", userDTO);
        this.loadCommonAtributtesNavbar(model, authentication);

        return "/configuration/profile/passwdProfile";
    }

    private void loadCommonAtributtesNavbar(Model model,
                                            Authentication authentication) {
        List<LanguageEnum> languages = new ArrayList<>(EnumSet.allOf(LanguageEnum.class));
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        model.addAttribute("idUser", user.getIdUser());
        model.addAttribute("languages",languages);
        model.addAttribute("navLink", "configuration");
    }
}
