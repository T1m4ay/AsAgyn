package com.example.asadmin.api;

import com.example.asadmin.dto.DiningSessionDTO;
import com.example.asadmin.service.DiningSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/dining-session")
public class DiningSessionController {

    @Autowired
    DiningSessionService service;

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_GUEST')")
    @PostMapping("/{establishmentId}")
    public DiningSessionDTO createDiningSession(@PathVariable Long establishmentId){
        return service.create(establishmentId);
    }
}
