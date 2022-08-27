package com.software.ascontroller.vehicles.newVehicle.controllers;

import com.software.ascontroller.language.LanguageEnum;
import com.software.ascontroller.model.services.ModelService;
import com.software.ascontroller.status.entities.Status;
import com.software.ascontroller.status.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Controller
@RequestMapping("/vehicles/newVehicle")
public class NewVehicleController {

    @Autowired
    private ModelService modelService;
    @Autowired
    private StatusService statusService;

    @GetMapping("/list")
    public String newVehicleList(Model model) {
        this.loadNewVehicleListScreen(model);
        return "/vehicles/newVehicle/newVehicleList";
    }

    private void loadNewVehicleListScreen(Model model) {
        this.loadModelAndStatusAtributtes(model);
        this.loadCommonAtributtesNavbar(model);
    }

    private void loadModelAndStatusAtributtes(Model model) {
        List<com.software.ascontroller.model.entities.Model> modelList = this.modelService.findAll();
        List<Status> statusList = this.statusService.findAll();
        model.addAttribute("modelList", modelList);
        model.addAttribute("statusList", statusList);

    }

    private void loadCommonAtributtesNavbar(Model model) {
        List<LanguageEnum> languages = new ArrayList<>(EnumSet.allOf(LanguageEnum.class));
        model.addAttribute("languages",languages);
        model.addAttribute("navLink", "vehicles");
    }
}
