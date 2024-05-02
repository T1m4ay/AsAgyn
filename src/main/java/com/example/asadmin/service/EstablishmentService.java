package com.example.asadmin.service;

import com.example.asadmin.criteria.BetweenDatesCriteria;
import com.example.asadmin.criteria.GeneralCriteria;
import com.example.asadmin.dto.*;
import com.example.asadmin.mapper.EstablishmentMapper;
import com.example.asadmin.model.*;
import com.example.asadmin.repository.EstablishmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstablishmentService {

    private final EstablishmentRepository repository;

    private final EstablishmentMapper mapper;

    private final UserService userService;

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
        log.info("Between " + betweenDatesCriteria.getStartDate() + " and " + betweenDatesCriteria.getEndDate());
        Establishment establishment = repository.findAllById(id);
        NumberOrderDTO numberOrderDTO = new NumberOrderDTO();
        Set<OrderEntity> orderEntities = establishment.getDiningSessions()
                .stream()
                .flatMap(d -> d.getOrderEntities()
                        .stream()
                        .filter(o -> o.getDateOfCreation().isBefore(betweenDatesCriteria.getEndDate())
                                && o.getDateOfCreation().isAfter(betweenDatesCriteria.getStartDate())))
                .collect(Collectors.toSet());
        numberOrderDTO.setCount(orderEntities.size());
        return numberOrderDTO;

    }

    public RevenueDTO getRevenue(
            Long id,
            BetweenDatesCriteria betweenDatesCriteria){
        log.info("Between " + betweenDatesCriteria.getStartDate() + " and " + betweenDatesCriteria.getEndDate());
        Establishment establishment = repository.findAllById(id);
        RevenueDTO revenueDTO = new RevenueDTO();
        Set<OrderEntity> orderEntities = establishment.getDiningSessions()
                .stream()
                .flatMap(d -> d.getOrderEntities()
                        .stream()
                        .filter(o -> o.getDateOfCreation().isBefore(betweenDatesCriteria.getEndDate())
                                && o.getDateOfCreation().isAfter(betweenDatesCriteria.getStartDate())))
                .collect(Collectors.toSet());
        Set<Integer> costs = orderEntities
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

    public Map<Month, Integer> getMonthRevenue(Long id) {
        Establishment establishment = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Establishment not found"));

        Map<Month, Integer> monthRevenueMap = new HashMap<>();

        for (Month month : Month.values()) {
            int sum = establishment.getDiningSessions().stream()
                    .flatMap(diningSession -> diningSession.getOrderEntities().stream())
                    .filter(order -> order.getDateOfCreation().getMonth() == month)
                    .flatMapToInt(order -> order.getOrderItems().stream().mapToInt(OrderItem::getCost))
                    .sum();

            monthRevenueMap.put(month, sum);
        }

        return monthRevenueMap;
    }

    public Establishment findById(Long id){
        Establishment establishment = repository.findAllById(id);
        return establishment;
    }

    public EstablishmentDTO findEstablishmentDTOById(Long id){
        return mapper.toDTO(repository.findAllById(id));
    }

    public Long create(EstablishmentDTO establishmentDTO){
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        System.out.println("String currentUser: " + currentUser);
        Establishment establishment = mapper.toEntity(establishmentDTO);
        establishment.setUser(userService.findByUsername(currentUser).orElse(null));
        return repository.save(establishment).getId();
    }

    public EstablishmentDTO getEstablishment(){
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        System.out.println("String currentUser: " + currentUser);
        return mapper.toDTO(
                repository.findAllByUser(
                        userService.findByUsername(currentUser).orElse(null)
                ));
    }
}
