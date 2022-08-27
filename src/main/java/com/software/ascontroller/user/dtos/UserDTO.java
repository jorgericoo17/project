package com.software.ascontroller.user.dtos;

import com.software.ascontroller.role.entities.Role;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    Long idUser;
    private String username;
    private String password;
    private String name;
    private String lastName;
    private Role role;
}
