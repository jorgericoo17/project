package com.software.ascontroller.status.services;

import com.software.ascontroller.status.entities.Status;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface StatusService {

    Optional<Status> findById(Long idStatus);
    List<Status> findAll();
}
