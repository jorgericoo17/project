package com.software.ascontroller.sells.controllers;

import com.software.ascontroller.language.LanguageEnum;
import com.software.ascontroller.sells.dtos.SellDTO;
import com.software.ascontroller.sells.entities.Sell;
import com.software.ascontroller.sells.services.SellService;
import com.software.ascontroller.status.entities.Status;
import com.software.ascontroller.status.enums.StatusEnum;
import com.software.ascontroller.status.services.StatusService;
import com.software.ascontroller.user.customUserDetails.CustomUserDetails;
import com.software.ascontroller.user.services.UserService;
import com.software.ascontroller.vehicles.newVehicle.entities.NewVehicle;
import com.software.ascontroller.vehicles.newVehicle.services.NewVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Controller
@RequestMapping("/sells")
public class SellController {

    @Autowired
    private NewVehicleService newVehicleService;
    @Autowired
    private UserService userService;
    @Autowired
    private SellService sellService;
    @Autowired
    private StatusService statusService;

    @GetMapping
    public String sells(Model model,
                        Authentication authentication) {

        this.loadSellsScreen(model, authentication);
        return "/sells/sells";
    }

    @GetMapping("/{idNewVehicle}")
    public String addSell(Model model,
                   @PathVariable("idNewVehicle") Long idNewVehicle,
                   Authentication authentication) {
        this.loadAddSellScreen(model, idNewVehicle, authentication);

        return "/sells/addSell";
    }

    @GetMapping("/user")
    public String userSells(Model model,
                            Authentication authentication) {

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        model.addAttribute("sellsList",this.sellService.findByIdUser(user.getIdUser()));
        this.loadCommonAtributtesNavbar(model, authentication);

        return "sells/sells";
    }

    @PostMapping("/save")
    public String saveSell(Model model,
                           @ModelAttribute("sellDTO") SellDTO sellDTO,
                           RedirectAttributes ra) {
        Sell sell = this.sellService.getSellFromDTO(sellDTO);
        NewVehicle newVehicle = this.newVehicleService.findById(sellDTO.getNewVehicle().getIdNewVehicle()).get();
        Status status = statusService.findById(StatusEnum.VENDIDO.getIdStatus()).get();
        newVehicle.setStatus(status);
        Sell sellSaved = this.sellService.save(sell);
        ra.addFlashAttribute("messageOK","Se ha guardado la venta del veículo "+sell.getNewVehicle().getModel().toString());

        return "redirect:/sells";
    }

    private void loadAddSellScreen(Model model,
                              Long idNewVehicle,
                              Authentication authentication) {
        this.loadCommonAtributtesNavbar(model, authentication);
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        NewVehicle newVehicle = this.newVehicleService.findById(idNewVehicle).get();
        model.addAttribute("newVehicle", newVehicle);
        model.addAttribute("seller", user);
        model.addAttribute("sellDTO", new SellDTO());
    }
    private void loadSellsScreen(Model model,
                            Authentication authentication) {

        model.addAttribute("sellsList",this.sellService.findAll());
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
