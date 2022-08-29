package com.software.ascontroller.configuration.controllers;

import com.software.ascontroller.language.LanguageEnum;
import com.software.ascontroller.role.entities.Role;
import com.software.ascontroller.role.services.RoleService;
import com.software.ascontroller.user.customUserDetails.CustomUserDetails;
import com.software.ascontroller.user.dtos.UserDTO;
import com.software.ascontroller.user.entites.User;
import com.software.ascontroller.user.search.UserFilter;
import com.software.ascontroller.user.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/configuration/users")
public class ConfigurationUsersController {

    //LOGS
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationUsersController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String usersList(Model model,
                            Pageable pageable,
                            Authentication authentication) {
        this.loadUsersListScreen(model,authentication, pageable);
        return "/configuration/users/list";
    }

    private void loadUsersListScreen(Model model,
                                     Authentication authentication,
                                     Pageable pageable) {
        LOGGER.info("Beginning of the userList screen load");
        this.loadCommonAtributtesNavbar(model, authentication);
        this.showAlertMessages(model);
        this.loadAttributtesUsersListScreen(model, pageable);
        LOGGER.info("End of the usersList screen load");
    }

    private void loadAttributtesUsersListScreen(Model model,
                                               Pageable pageable) {
        this.loadRoleAttributtes(model);
        model.addAttribute("users", this.userService.findAll(pageable));
        model.addAttribute("userFilter", new UserFilter());
    }

    private void loadRoleAttributtes(Model model) {
        List<Role> roles = this.roleService.findAll();
        model.addAttribute("roles",roles);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String searchUsers(Model model,
                              @ModelAttribute("userFilter") UserFilter userFilter,
                              Authentication authentication,
                              @SortDefault("username") Pageable pageable) {
        this.loadAttributtesSearchScreen(model, userFilter, authentication, pageable);
        return "/configuration/users/list";
    }

    private void loadAttributtesSearchScreen(Model model,
                                        UserFilter userFilter,
                                        Authentication authentication,
                                        Pageable pageable) {
        LOGGER.info("Beginning of the advanced user search with params {}", userFilter);
        this.loadRoleAttributtes(model);
        this.loadCommonAtributtesNavbar(model, authentication);
        model.addAttribute("users",this.userService.search(userFilter, pageable));
        LOGGER.info("End of the advanced user search with params");
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

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addUser(Model model,
                          @ModelAttribute("userDTO") UserDTO userDTO,
                          Authentication authentication) {
        this.loadAddUserScreen(model, authentication);
        return "/configuration/users/user";
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveUser(@ModelAttribute("userDTO") UserDTO userDTO,
                           RedirectAttributes ra) {
        return this.saveAndRedirect(userDTO, ra);
    }

    private String saveAndRedirect(UserDTO userDTO,
                                   RedirectAttributes ra) {
        LOGGER.info("Beginning of the user save");
        User user = this.userService.getUserFromDTO(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User userSaved = this.userService.save(user);
        LOGGER.info("End of the user save [idUser={}]", user.getIdUser());
        ra.addFlashAttribute("messageOK","Se ha guardado el usuario "+userSaved.getName());
        return "redirect:/configuration/users/list";
    }

    private void loadAddUserScreen(Model model,
                                   Authentication authentication) {
        LOGGER.info("Beginning of the userAdd screen load");
        this.loadCommonAtributtesNavbar(model, authentication);
        List<Role> roles = this.roleService.findAll();
        model.addAttribute("roles",roles);
        LOGGER.info("End of the the userAdd screen load");
    }

    @GetMapping("/edit/{idUser}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editUser(Model model,
                           @PathVariable(required = true, value = "idUser") Long idUser,
                           Authentication authentication) {
        this.loadEditUserScreen(model, idUser, authentication);
        return "/configuration/users/editUser";
    }

    private void loadEditUserScreen(Model model,
                                    Long idUser,
                                    Authentication authentication) {
        LOGGER.info("Beginning of the user edit for user [idUser={}]", idUser);
        this.loadCommonAtributtesNavbar(model, authentication);
        this.loadRoleAttributtes(model);
        User user = this.userService.findById(idUser).get();
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO, "password");
        model.addAttribute("userDTO", userDTO);
        LOGGER.info("End of the user edit for user [idUser={}]", idUser);
    }

    @GetMapping("delete/{idUser}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(@PathVariable("idUser") Long idUser,
                             RedirectAttributes ra) {
        return this.deleteAndRedirect(idUser, ra);
    }

    private String deleteAndRedirect(Long idUser, RedirectAttributes ra) {
        Optional<User> userOptional = this.userService.findById(idUser);
        if (userOptional.isPresent()) {
            this.userService.delete(userOptional.get());
            ra.addFlashAttribute("messageOK", "Se ha borrado el usuario "+userOptional.get().getName());
        } else {
            ra.addFlashAttribute("messageError", "Error borrando el usuario");
        }
        return "redirect:/configuration/users/list";
    }
    @GetMapping("/getNewUsername")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public String getNewUsername() {
        return this.userService.getNewUsername();
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
