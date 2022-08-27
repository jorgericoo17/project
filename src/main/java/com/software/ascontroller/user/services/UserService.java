package com.software.ascontroller.user.services;

import com.software.ascontroller.user.dtos.UserDTO;
import com.software.ascontroller.user.entites.User;
import com.software.ascontroller.user.search.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User getUserFromDTO(UserDTO userDTO);

    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    Page<User> search(UserFilter userFilter, Pageable pageable);

    Optional<User> findById(Long idUser);

    User save(User user);

    String getNewUsername();

    void delete(User user);

}
