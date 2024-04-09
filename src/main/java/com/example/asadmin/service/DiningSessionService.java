package com.example.asadmin.service;

import com.example.asadmin.dto.DiningSessionDTO;
import com.example.asadmin.mapper.DiningSessionMapper;
import com.example.asadmin.model.DiningSession;
import com.example.asadmin.model.Establishment;
import com.example.asadmin.repository.DiningSessionRepository;
import org.springframework.stereotype.Service;

@Service
public class DiningSessionService {

    private final DiningSessionRepository repository;

    private final EstablishmentService establishmentService;

    private final DiningSessionMapper mapper;

    public DiningSessionService(DiningSessionRepository repository, EstablishmentService establishmentService, DiningSessionMapper mapper) {
        this.repository = repository;
        this.establishmentService = establishmentService;
        this.mapper = mapper;
    }

    public DiningSession save(DiningSession diningSession){
        return repository.save(diningSession);
    }

    public DiningSessionDTO create(Long establishmentId){
        DiningSession diningSession = new DiningSession();
        diningSession.setEstablishment(establishmentService.findById(establishmentId));
        return mapper.toDTO(repository.save(diningSession));
    }
}
