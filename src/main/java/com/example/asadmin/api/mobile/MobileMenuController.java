package com.example.asadmin.api.mobile;

import com.example.asadmin.dto.MenuDTO;
import com.example.asadmin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/mobile/api/menu")
public class MobileMenuController {

    @Autowired
    MenuService menuService;

    @PreAuthorize("hasAnyRole('ROLE_GUEST', 'ROLE_CUSTOMER')")
    @GetMapping("/{establishmentId}")
    public MenuDTO getMenu(@PathVariable Long establishmentId){
        return menuService.findByEstablishmentId(establishmentId);
    }
}
