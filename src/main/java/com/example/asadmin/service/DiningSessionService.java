package com.example.asadmin.service;

import com.example.asadmin.dto.DiningSessionDTO;
import com.example.asadmin.mapper.DiningSessionMapper;
import com.example.asadmin.model.DiningSession;
import com.example.asadmin.repository.DiningSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiningSessionService {

    private DiningSessionRepository repository;

    private DiningSessionMapper mapper;

    public DiningSession save(DiningSession diningSession){
        return repository.save(diningSession);
    }

    public DiningSessionDTO create(){
        DiningSession diningSession = new DiningSession();
        return mapper.toDTO(repository.save(diningSession));
    }
}
