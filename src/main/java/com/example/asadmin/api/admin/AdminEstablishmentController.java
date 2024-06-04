package com.example.asadmin.api.admin;

import com.example.asadmin.criteria.BetweenDatesCriteria;
import com.example.asadmin.criteria.GeneralCriteria;
import com.example.asadmin.dto.*;
import com.example.asadmin.service.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.Map;

@RestController
@RequestMapping(value = "/admin/api/establishment")
public class AdminEstablishmentController {

    @Autowired
    EstablishmentService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("")
    public ResponseDTO<EstablishmentDTO> getEstablishment(){
        return service.getEstablishment();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public EstablishmentDTO getEstablishmentById(@PathVariable Long id){
        return service.findEstablishmentDTOById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("")
    public EstablishmentDTO createEstablishment(@RequestBody EstablishmentDTO establishmentDTO){
        return service.create(establishmentDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public PageResponse<EstablishmentDTO> establishmentsPageResponse(GeneralCriteria generalCriteria) {
        return service.getAll(generalCriteria);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/order-count-period/{id}")
    public NumberOrderDTO orderCountPeriod(
            @PathVariable Long id,
            BetweenDatesCriteria betweenDatesCriteria) {
        return service.getNumberOfOrders(id, betweenDatesCriteria);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/revenue-period/{id}")
    public RevenueDTO revenuePeriod(
            @PathVariable Long id,
            BetweenDatesCriteria betweenDatesCriteria) {
        return service.getRevenue(id, betweenDatesCriteria);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/revenue-every-month-period/{id}")
    public Map<Month, Integer> getRevenueEveryMonthPeriod(@PathVariable Long id){
        return service.getMonthRevenue(id);
    }
}

