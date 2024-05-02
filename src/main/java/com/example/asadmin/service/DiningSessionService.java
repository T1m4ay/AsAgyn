package com.example.asadmin.service;

import com.example.asadmin.dto.DiningSessionDTO;
import com.example.asadmin.mapper.DiningSessionMapper;
import com.example.asadmin.model.DiningSession;
import com.example.asadmin.repository.DiningSessionRepository;
import com.pusher.rest.Pusher;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiningSessionService {

    private final DiningSessionRepository repository;

    private final EstablishmentService establishmentService;

    private final DiningSessionMapper mapper;

    private final UserService userService;

    public DiningSessionService(
            DiningSessionRepository repository,
            EstablishmentService establishmentService,
            DiningSessionMapper mapper,
            UserService userService) {
        this.repository = repository;
        this.establishmentService = establishmentService;
        this.mapper = mapper;
        this.userService = userService;
    }

    public DiningSession save(DiningSession diningSession){
        return repository.save(diningSession);
    }

    public DiningSessionDTO create(Long establishmentId){
        DiningSession diningSession = new DiningSession();
        diningSession.setClose(false);
        diningSession.setEstablishment(establishmentService.findById(establishmentId));
        diningSession.setStartDateTime(ZonedDateTime.now());
        DiningSessionDTO dto = mapper.toDTO(repository.save(diningSession));
        Pusher pusher = new Pusher("1783988", "1b1aac5f3ed531c5e179", "d6eafbe9223fb0184c85");
        pusher.setCluster("ap2");
        pusher.setEncrypted(true);

        pusher.trigger(
                "AsAgyn-channel",
                "dining-session-create-event",
                Collections.singletonMap("dining-session", dto));
        return dto;
    }

    public List<DiningSessionDTO> getCurrentSessions(){
        List<DiningSession> diningSessions = repository
                .findAllByUserId(userService.getCurrentUser().getId());
        return diningSessions.stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    public DiningSessionDTO getSessionById(Long id){
        return mapper.toDTO(repository.findById(id).orElse(null));
    }

    public DiningSessionDTO closeDiningSession(Long id){
        DiningSession diningSession = repository.getById(id);
        diningSession.setClose(true);
        Pusher pusher = new Pusher("1783988", "1b1aac5f3ed531c5e179", "d6eafbe9223fb0184c85");
        pusher.setCluster("ap2");
        pusher.setEncrypted(true);

        pusher.trigger(
                "AsAgyn-channel",
                "close-dining-session",
                Collections.singletonMap("closed-session", mapper.toDTO(diningSession)));
        return mapper.toDTO(diningSession);
    }

    public List<DiningSessionDTO> getAllDiningSessionByEstablishmentId(Long establishmentId){
        return repository.findAllByEstablishment_Id(establishmentId).stream().map(mapper::toDTO).collect(Collectors.toList());
    }
}
