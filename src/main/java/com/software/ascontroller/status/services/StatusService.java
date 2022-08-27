package com.software.ascontroller.status.services;

import com.software.ascontroller.status.entities.Status;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StatusService {

    List<Status> findAll();
}
