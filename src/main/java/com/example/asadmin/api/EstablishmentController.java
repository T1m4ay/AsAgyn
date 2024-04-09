package com.example.asadmin.api;

import com.example.asadmin.criteria.BetweenDatesCriteria;
import com.example.asadmin.criteria.GeneralCriteria;
import com.example.asadmin.dto.*;
import com.example.asadmin.service.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/establishment")
public class EstablishmentController {

    @Autowired
    EstablishmentService service;

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("")
    public EstablishmentDTO getEstablishment(){
        return service.getEstablishment();
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/{id}")
    public EstablishmentDTO getEstablishmentById(@PathVariable Long id){
        return service.findEstablishmentDTOById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PostMapping("")
    public EstablishmentDTO createEstablishment(EstablishmentDTO establishmentDTO){
        return service.create(establishmentDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public PageResponse<EstablishmentDTO> ordersPageResponse(GeneralCriteria generalCriteria) {
        return service.getAll(generalCriteria);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/order-count-period/{id}")
    public NumberOrderDTO orderCountPeriod(
            @PathVariable Long id,
            BetweenDatesCriteria betweenDatesCriteria) {
        return service.getNumberOfOrders(id, betweenDatesCriteria);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/revenue-period/{id}")
    public RevenueDTO revenuePeriod(
            @PathVariable Long id,
            BetweenDatesCriteria betweenDatesCriteria) {
        return service.getRevenue(id, betweenDatesCriteria);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/revenue-every-month-period/{id}")
    public List<MonthRevenueDTO> getRevenueEveryMonthPeriod(@PathVariable Long id){
        return service.getMonthRevenue(id);
    }
}

