package com.software.ascontroller.user.repositories;

import com.software.ascontroller.user.entites.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    @Query("SELECT u.username FROM User u ORDER BY u.idUser DESC")
    List<String> findLastUsername();

    Page<User> findAll(Specification<User> specification, Pageable pageable);
}
