package com.software.ascontroller.vehicles.newVehicle.controllers;

import com.software.ascontroller.language.LanguageEnum;
import com.software.ascontroller.model.services.ModelService;
import com.software.ascontroller.status.entities.Status;
import com.software.ascontroller.status.services.StatusService;
import com.software.ascontroller.vehicles.newVehicle.dtos.NewVehicleDTO;
import com.software.ascontroller.vehicles.newVehicle.entities.NewVehicle;
import com.software.ascontroller.vehicles.newVehicle.search.NewVehicleFilter;
import com.software.ascontroller.vehicles.newVehicle.services.NewVehicleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    private NewVehicleService newVehicleService;

    @GetMapping("/list")
    public String newVehicleList(Model model) {
        this.loadNewVehicleListScreen(model);
        return "/vehicles/newVehicle/newVehicleList";
    }

    @GetMapping("/search")
    public String searchNewVehicless(Model model,
                                     @ModelAttribute("newVehicleFilter") NewVehicleFilter newVehicleFilter,
                                     Pageable pageable) {

        this.loadSearchScreen(model, newVehicleFilter, pageable);

        return "/vehicles/newVehicle/newVehicleList";
    }

    private void loadSearchScreen(Model model,
                                  NewVehicleFilter newVehicleFilter,
                                  Pageable pageable) {
        this.loadCommonAtributtesNavbar(model);
        this.loadModelAndStatusAtributtes(model);
        model.addAttribute("newVehicleList", this.newVehicleService.findAll(newVehicleFilter,pageable));
    }
    @GetMapping("/edit/{idNewVehicle}")
    public String editNewVehicle(Model model,
                                 @PathVariable("idNewVehicle") Long idNewVehicle) {
        this.loadEditNewVehicleScreen(model,idNewVehicle);
        return "/vehicles/newVehicle/editNewVehicle";
    }

    @PostMapping("/save")
    public String saveNewVehicle(@ModelAttribute("newVehicleDTO") NewVehicleDTO newVehicleDTO,
                                 RedirectAttributes ra) {
        NewVehicle newVehicle = this.newVehicleService.getNewVehicleFromDTO(newVehicleDTO);
        return this.saveAndRedirect(newVehicle, ra);
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public String addNewVehicle(Model model) {
        this.loadAddNewVehicleScreen(model);
        return "vehicles/newVehicle/addNewVehicle";
    }

    @GetMapping("/delete/{idNewVehicle}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')")
    public String deleteNewVehicle(@PathVariable("idNewVehicle") Long idNewVehicle,
                                   RedirectAttributes ra) {
        NewVehicle newVehicle = this.newVehicleService.findById(idNewVehicle).get();
        this.newVehicleService.delete(newVehicle);
        ra.addFlashAttribute("messageOK", "Se ha borrado el vehiculo " + newVehicle.getModel().toString() );

        return "redirect:/vehicles/newVehicle/list";
    }

    private void loadAddNewVehicleScreen(Model model) {
        this.loadCommonAtributtesNavbar(model);
        this.loadModelAndStatusAtributtes(model);
        model.addAttribute("newVehicleDTO", new NewVehicleDTO());
    }

    private String saveAndRedirect(NewVehicle newVehicle,
                                   RedirectAttributes ra) {
        NewVehicle newVehicleSaved = this.newVehicleService.save(newVehicle);
        ra.addFlashAttribute("messageOK","Se ha guardado el vehiculo "+newVehicleSaved.getModel().getName() + " " + newVehicleSaved.getModel().getFinish());
        return "redirect:/vehicles/newVehicle/list";
    }

    private void loadEditNewVehicleScreen(Model model,
                                          Long idNewVehicle) {

        NewVehicle newVehicle = this.newVehicleService.findById(idNewVehicle).get();
        NewVehicleDTO newVehicleDTO = new NewVehicleDTO();
        BeanUtils.copyProperties(newVehicle,newVehicleDTO);
        model.addAttribute("newVehicleDTO", newVehicleDTO);
        this.loadCommonAtributtesNavbar(model);
        this.loadModelAndStatusAtributtes(model);
        this.showAlertMessages(model);
    }

    private void loadNewVehicleListScreen(Model model) {
        model.addAttribute("newVehicleFilter", new NewVehicleFilter());
        List<NewVehicle> newVehicleList = this.newVehicleService.findAllInStock();
        model.addAttribute("newVehicleList", newVehicleList);
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
