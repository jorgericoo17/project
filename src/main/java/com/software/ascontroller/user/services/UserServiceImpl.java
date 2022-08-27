package com.software.ascontroller.user.services;

import com.software.ascontroller.user.dtos.UserDTO;
import com.software.ascontroller.user.entites.User;
import com.software.ascontroller.user.repositories.UserRepository;
import com.software.ascontroller.user.search.UserFilter;
import com.software.ascontroller.user.search.UserSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserFromDTO(UserDTO userDTO) {
        User user = new User();
        if(userDTO.getIdUser() != null) {
            user.setIdUser(userDTO.getIdUser());
        }
        user.setUsername(userDTO.getUsername());
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setRole(userDTO.getRole());

        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public Page<User> search(UserFilter userFilter, Pageable pageable) {
        return this.userRepository.findAll(UserSpecifications.filterBy(userFilter), pageable);
    }

    @Override
    public Optional<User> findById(Long idUser) {
        return this.userRepository.findById(idUser);
    }

    @Override
    @Transactional(readOnly = false)
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public String getNewUsername() {
        String lastUsername = this.userRepository.findLastUsername().get(0);
        String numUsuario = lastUsername.split("s")[1];
        int numero = Integer.parseInt(numUsuario);
        String totalCeros = String.valueOf(numero+1);
        int numCeros = 4-totalCeros.length();

        StringBuilder builder = new StringBuilder();
        builder.append("as");
        builder.append("0".repeat(numCeros));
        builder.append(numero+1);

        return builder.toString();
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(User user) {
        this.userRepository.delete(user);
    }

}
