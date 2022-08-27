package com.software.ascontroller.user.entites;

import com.software.ascontroller.role.entities.Role;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="USER")
@NamedQuery(name="User.findAll", query = "SELECT u FROM User u")
@Getter
@Setter
public class User implements Serializable {

    @Id
    @SequenceGenerator(name="USER_IDUSER_GENERATOR", sequenceName = "User_idUsuario_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDUSER", unique = true, nullable = false)
    private Long idUser;

    @NotNull
    @Column(name = "USERNAME")
    private String username;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LASTNAME")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "IDROLE")
    private Role role;

}
