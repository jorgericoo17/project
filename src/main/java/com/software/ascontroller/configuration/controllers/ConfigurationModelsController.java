package com.software.ascontroller.configuration.controllers;

import com.software.ascontroller.language.LanguageEnum;
import com.software.ascontroller.model.dtos.ModelDTO;
import com.software.ascontroller.model.search.ModelFilter;
import com.software.ascontroller.model.search.ModelSpecifications;
import com.software.ascontroller.model.services.ModelService;
import com.software.ascontroller.user.customUserDetails.CustomUserDetails;
import com.software.ascontroller.user.entites.User;
import com.software.ascontroller.vehicles.newVehicle.entities.NewVehicle;
import com.software.ascontroller.vehicles.newVehicle.services.NewVehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/configuration/models")
public class ConfigurationModelsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationModelsController.class);

    @Autowired
    private ModelService modelService;
    @Autowired
    private NewVehicleService newVehicleService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public String modelList(Model model,
                            Authentication authentication) {
        this.loadModelListScreen(model, authentication);
        return "/configuration/models/list";
    }

    private void loadModelListScreen(Model model,
                                     Authentication authentication) {
        LOGGER.info("Beginning of the modelList screen load");
        this.loadCommonAtributtesNavbar(model, authentication);
        this.showAlertMessages(model);
        model.addAttribute("models", this.modelService.findAll());
        model.addAttribute("modelFilter", new ModelFilter());
        LOGGER.info("End of the modelList screen load");
    }
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public String search(Model model,
                         @ModelAttribute("modelFilter") ModelFilter modelFilter,
                         Pageable pageable,
                         Authentication authentication) {

        this.loadCommonAtributtesNavbar(model, authentication);
        model.addAttribute("models", this.modelService.findAll(modelFilter, pageable));
        return "/configuration/models/list";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public String addModel(Model model,
                           @ModelAttribute("modelDTO") ModelDTO modelDTO,
                           Authentication authentication) {
        this.loadCommonAtributtesNavbar(model, authentication);
        return "/configuration/models/addModel";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public String saveModel(@ModelAttribute("modelDTO") ModelDTO modelDTO,
                            RedirectAttributes ra) {

        com.software.ascontroller.model.entities.Model model = this.modelService.getModelFromDTO(modelDTO);
        return this.saveAndRedirect(model, ra);
    }

    private String saveAndRedirect(com.software.ascontroller.model.entities.Model model,
                                   RedirectAttributes ra) {

        com.software.ascontroller.model.entities.Model modelSaved = this.modelService.save(model);
        ra.addFlashAttribute("messageOK","Se ha guardado el modelo "+modelSaved.getName());
        LOGGER.info("End of the user save [name={}]", model.getName());

        return "redirect:/configuration/models/list";
    }

    @GetMapping("/delete/{idModel}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public String deleteModel(@PathVariable("idModel") Long idModel,
                              RedirectAttributes ra) {
        Optional<com.software.ascontroller.model.entities.Model> modelOptional = this.modelService.findById(idModel);
        if (modelOptional.isPresent()) {
            List<NewVehicle> newVehicleList = this.newVehicleService.findByIdModel(modelOptional.get().getIdModel());
            for(NewVehicle newVehicle : newVehicleList) {
                this.newVehicleService.delete(newVehicle);
            }
            this.modelService.delete(modelOptional.get());
            ra.addFlashAttribute("messageOK", "Se ha borrado el modelo");
        } else {
            ra.addFlashAttribute("messageError", "Error borrando el modelo");
        }

        return "redirect:/configuration/models/list";
    }

    @GetMapping("/edit/{idModel}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public String editModel(Model model,
                            @PathVariable("idModel") Long idModel,
                            Authentication authentication) {
        this.loadEditModelScreen(model, idModel, authentication);
        return "/configuration/models/editModel";
    }

    private void loadEditModelScreen(Model model,
                                    Long idModel,
                                     Authentication authentication) {
        this.loadCommonAtributtesNavbar(model, authentication);
        com.software.ascontroller.model.entities.Model objectModel = this.modelService.findById(idModel).get();
        ModelDTO modelDTO = new ModelDTO();
        BeanUtils.copyProperties(objectModel, modelDTO);
        model.addAttribute("modelDTO", modelDTO);
    }
    private void loadCommonAtributtesNavbar(Model model,
                                            Authentication authentication) {
        List<LanguageEnum> languages = new ArrayList<>(EnumSet.allOf(LanguageEnum.class));
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        model.addAttribute("idUser", user.getIdUser());
        model.addAttribute("languages",languages);
        model.addAttribute("navLink", "configuration");
    }

    private void showAlertMessages(Model model) {
        String messageOK = (String) model.asMap().get("messageOK");
        String messageError = (String) model.asMap().get("messageError");
        if (messageOK != null) {
            model.addAttribute("messageOK", messageOK);
        } else {
            model.addAttribute("messageError", messageError);
        }
    }
}
