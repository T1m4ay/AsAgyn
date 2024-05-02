package com.example.asadmin.api;

import com.example.asadmin.dto.EstablishmentDTO;
import com.example.asadmin.dto.MenuDTO;
import com.example.asadmin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_GUEST', 'ROLE_CUSTOMER')")
    @GetMapping("/{establishmentId}")
    public MenuDTO getMenu(@PathVariable Long establishmentId){
        return menuService.findByEstablishmentId(establishmentId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("")
    public MenuDTO createMenu(@RequestBody EstablishmentDTO establishmentDTO){
        return menuService.create(establishmentDTO);
    }
}
