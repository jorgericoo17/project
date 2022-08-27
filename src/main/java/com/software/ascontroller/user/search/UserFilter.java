package com.software.ascontroller.user.search;

import com.software.ascontroller.role.entities.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilter {

    private String username;
    private String name;
    private String lastName;
    private Long idRole;
}
