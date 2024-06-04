package com.example.asadmin.api.admin;

import com.example.asadmin.dto.DiningSessionDTO;
import com.example.asadmin.service.DiningSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/api/dining-session")
public class AdminDiningSessionController {

    @Autowired
    DiningSessionService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/all-session/{establishmentId}")
    public List<DiningSessionDTO> getAllDiningSessionId(@PathVariable Long establishmentId){
        return service.getAllDiningSessionByEstablishmentId(establishmentId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public DiningSessionDTO getDiningSessionById(@PathVariable Long id){
        return service.getSessionById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/close-session/{id}")
    public DiningSessionDTO closeDiningSession(@PathVariable Long id){
        return service.closeDiningSession(id);
    }
}
