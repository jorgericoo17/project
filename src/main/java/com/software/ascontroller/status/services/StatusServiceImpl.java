package com.software.ascontroller.status.services;

import com.software.ascontroller.status.entities.Status;
import com.software.ascontroller.status.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Optional<Status> findById(Long idStatus) {
        return this.statusRepository.findById(idStatus);
    }

    @Override
    public List<Status> findAll() {
        return this.statusRepository.findAll();
    }
}
