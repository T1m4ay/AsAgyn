package com.example.asadmin.service;

import com.example.asadmin.criteria.BetweenDatesCriteria;
import com.example.asadmin.criteria.GeneralCriteria;
import com.example.asadmin.dto.*;
import com.example.asadmin.mapper.EstablishmentMapper;
import com.example.asadmin.model.Establishment;
import com.example.asadmin.model.Order;
import com.example.asadmin.model.OrderItem;
import com.example.asadmin.repository.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstablishmentService {

    private EstablishmentRepository repository;

    private EstablishmentMapper mapper;

    public Establishment save(Establishment establishment){
        return repository.save(establishment);
    }

    public PageResponse<EstablishmentDTO> getAll(GeneralCriteria generalCriteria){
        Page<Establishment> establishments = repository.findAll(
                PageRequest.of(
                        generalCriteria.getPage() > 0
                                ? generalCriteria.getPage() - 1
                                : 0, generalCriteria.getSize()
                )
        );

        PageResponse<EstablishmentDTO> pageResponse = new PageResponse<>();
        pageResponse.setItems(establishments.get().map(mapper::toDTO).collect(Collectors.toList()));
        pageResponse.setTotalPages(establishments.getTotalPages());
        pageResponse.setTotalCount(establishments.getNumber());

        return pageResponse;

    }

    public NumberOrderDTO getNumberOfOrders(
            Long id,
            BetweenDatesCriteria betweenDatesCriteria){
        Establishment establishment = repository.findById(id).orElse(null);
        NumberOrderDTO numberOrderDTO = new NumberOrderDTO();
        Set<Order> orders = establishment.getDiningSessions()
                .stream()
                .flatMap(d -> d.getOrders()
                        .stream()
                        .filter(o -> o.getDateOfCreation().isBefore(betweenDatesCriteria.getEndDate())
                                && o.getDateOfCreation().isAfter(betweenDatesCriteria.getStartDate())))
                .collect(Collectors.toSet());
        numberOrderDTO.setCount(orders.size());
        return numberOrderDTO;

    }

    public RevenueDTO getRevenue(
            Long id,
            BetweenDatesCriteria betweenDatesCriteria){
        Establishment establishment = repository.findById(id).orElse(null);
        RevenueDTO revenueDTO = new RevenueDTO();
        Set<Order> orders = establishment.getDiningSessions()
                .stream()
                .flatMap(d -> d.getOrders()
                        .stream()
                        .filter(o -> o.getDateOfCreation().isBefore(betweenDatesCriteria.getEndDate())
                                && o.getDateOfCreation().isAfter(betweenDatesCriteria.getStartDate())))
                .collect(Collectors.toSet());
        Set<Integer> costs = orders
                .stream()
                .flatMap(o -> o.getOrderItems()
                        .stream()
                        .map(OrderItem::getCost))
                .collect(Collectors.toSet());
        int sum = costs.stream()
                .mapToInt(Integer::intValue)
                .sum();
        revenueDTO.setSum(sum);
        return revenueDTO;

    }

    public List<MonthRevenueDTO> getMonthRevenue(Long id){
        Establishment establishment = repository.findById(id).orElse(null);
        List<MonthRevenueDTO> monthRevenueDTOS = new ArrayList<>();
        for(Month month : Month.values()){
            Set<Order> orders = establishment.getDiningSessions()
                    .stream()
                    .flatMap(d -> d.getOrders()
                            .stream()
                            .filter(o -> o.getDateOfCreation().getMonth() == month))
                    .collect(Collectors.toSet());
            if (!ObjectUtils.isEmpty(orders)) {
                Set<Integer> costs = orders
                        .stream()
                        .flatMap(o -> o.getOrderItems()
                                .stream()
                                .map(OrderItem::getCost))
                        .collect(Collectors.toSet());
                int sum = costs.stream()
                        .mapToInt(Integer::intValue)
                        .sum();
                MonthRevenueDTO monthRevenueDTO = new MonthRevenueDTO();
                monthRevenueDTO.setSum(sum);
                monthRevenueDTO.setMonth(month.toString());

                monthRevenueDTOS.add(monthRevenueDTO);
            }
        }

        return monthRevenueDTOS;
    }

}
