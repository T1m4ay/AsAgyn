package com.example.asadmin.api;

import com.example.asadmin.criteria.BetweenDatesCriteria;
import com.example.asadmin.criteria.GeneralCriteria;
import com.example.asadmin.dto.*;
import com.example.asadmin.service.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/establishment")
public class EstablishmentController {

    @Autowired
    EstablishmentService service;

    @GetMapping("")
    public PageResponse<EstablishmentDTO> orderPageResponse(GeneralCriteria generalCriteria) {
        return service.getAll(generalCriteria);
    }

    @GetMapping("/order-count-period/{id}")
    public NumberOrderDTO orderCountPeriod(
            @PathVariable Long id,
            BetweenDatesCriteria betweenDatesCriteria) {
        return service.getNumberOfOrders(id, betweenDatesCriteria);
    }

    @GetMapping("/revenue-period/{id}")
    public RevenueDTO revenuePeriod(
            @PathVariable Long id,
            BetweenDatesCriteria betweenDatesCriteria) {
        return service.getRevenue(id, betweenDatesCriteria);
    }

    @GetMapping("/revenue-every-month-period/{id")
    public List<MonthRevenueDTO> getRevenueEveryMonthPeriod(@PathVariable Long id){
        return service.getMonthRevenue(id);
    }
}

