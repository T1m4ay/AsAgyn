package com.example.asadmin.api;

import com.example.asadmin.dto.DiningSessionDTO;
import com.example.asadmin.model.DiningSession;
import com.example.asadmin.service.DiningSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/dining-session")
public class DiningSessionController {

    @Autowired
    DiningSessionService service;

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_GUEST')")
    @GetMapping("")
    public List<DiningSessionDTO> getDiningSessions(){
        return service.getCurrentSessions();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/all-session/{establishmentId}")
    public List<DiningSessionDTO> getAllDiningSessionId(@PathVariable Long establishmentId){
        return service.getAllDiningSessionByEstablishmentId(establishmentId);
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_GUEST')")
    @GetMapping("/{id}")
    public DiningSessionDTO getDiningSessionById(@PathVariable Long id){
        return service.getSessionById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_GUEST')")
    @PostMapping("/{establishmentId}")
    public DiningSessionDTO createDiningSession(@PathVariable Long establishmentId){
        return service.create(establishmentId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/close-session/{id}")
    public DiningSessionDTO closeDiningSession(@PathVariable Long id){
        return service.closeDiningSession(id);
    }
}
