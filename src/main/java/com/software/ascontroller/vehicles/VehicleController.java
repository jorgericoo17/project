package com.software.ascontroller.vehicles;

import com.software.ascontroller.configuration.controllers.ConfigurationController;
import com.software.ascontroller.language.LanguageEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    //LOGS
    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);

    @GetMapping
    public String vehicles(Model model) {
        this.loadVehicleScreen(model);
        return "/vehicles/index";
    }

    private void loadVehicleScreen(Model model) {
        LOGGER.info("Beginning of the vehicles screen load");
        this.loadCommonAtributtesNavbar(model);
        LOGGER.info("End of the vehicles screen load");
    }

    private void loadCommonAtributtesNavbar(Model model) {
        List<LanguageEnum> languages = new ArrayList<>(EnumSet.allOf(LanguageEnum.class));
        model.addAttribute("languages",languages);
        model.addAttribute("navLink", "vehicles");
    }

}
