package com.software.ascontroller.status.services;

import com.software.ascontroller.status.entities.Status;
import com.software.ascontroller.status.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<Status> findAll() {
        return this.statusRepository.findAll();
    }
}
