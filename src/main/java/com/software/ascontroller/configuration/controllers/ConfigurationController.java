package com.software.ascontroller.configuration.controllers;

import com.software.ascontroller.language.LanguageEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


@Controller
@RequestMapping("/configuration")
public class ConfigurationController {

    //LOGS
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationController.class);

    @GetMapping
    public String configuration(Model model) {
        this.loadConfigurationScreen(model);
        return ("/configuration/index");
    }

    private void loadConfigurationScreen(Model model) {
        LOGGER.info("Beginning of the configuration screen load");
        this.loadCommonAtributtesNavbar(model);
        LOGGER.info("End if the configuration screen load");
    }

    private void loadCommonAtributtesNavbar(Model model) {
        List<LanguageEnum> languages = new ArrayList<>(EnumSet.allOf(LanguageEnum.class));
        model.addAttribute("languages",languages);
        model.addAttribute("navLink", "configuration");
    }
}
