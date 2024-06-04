package com.example.asadmin.api.mobile;

import com.example.asadmin.dto.DiningSessionDTO;
import com.example.asadmin.dto.PaymentMethodDTO;
import com.example.asadmin.service.DiningSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/mobile/api/dining-session")
public class MobileDiningSessionController {

    @Autowired
    DiningSessionService service;

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_GUEST')")
    @GetMapping("")
    public List<DiningSessionDTO> getDiningSessions(){
        return service.getCurrentUserSessions();
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

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_GUEST')")
    @PostMapping("/close-session/{id}")
    public DiningSessionDTO closeDiningSession(@PathVariable Long id, @RequestBody PaymentMethodDTO paymentMethodDTO){
        return service.closeDiningSessionByCustomer(id, paymentMethodDTO);
    }

}
