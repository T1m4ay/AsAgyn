package com.example.asadmin.service;

import com.example.asadmin.dto.MenuDTO;
import com.example.asadmin.mapper.MenuMapper;
import com.example.asadmin.model.Menu;
import com.example.asadmin.repository.MenuRepository;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final MenuRepository repository;

    private final EstablishmentService establishmentService;

    private final MenuMapper mapper;

    public MenuService(MenuRepository repository, EstablishmentService establishmentService, MenuMapper mapper) {
        this.repository = repository;
        this.establishmentService = establishmentService;
        this.mapper = mapper;
    }

    public MenuDTO findByEstablishmentId(Long establishmentId){
        return mapper.toDTO(repository.findAllByEstablishmentId(establishmentId));
    }

    public MenuDTO findById(Long id){
        return mapper.toDTO(repository.findById(id).orElse(null));
    }

    public MenuDTO create(Long establishmentId){
        Menu menu = new Menu();
        menu.setEstablishment(establishmentService.findById(establishmentId));
        return mapper.toDTO(repository.save(menu));
    }
}
