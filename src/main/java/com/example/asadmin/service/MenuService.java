package com.example.asadmin.service;

import com.example.asadmin.dto.EstablishmentDTO;
import com.example.asadmin.dto.MenuDTO;
import com.example.asadmin.mapper.MenuMapper;
import com.example.asadmin.model.Establishment;
import com.example.asadmin.model.Menu;
import com.example.asadmin.repository.MenuRepository;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final MenuRepository repository;

    private final MenuMapper mapper;

    public MenuService(MenuRepository repository, MenuMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public MenuDTO findByEstablishmentId(Long establishmentId){
        return mapper.toDTO(repository.findByEstablishmentId(establishmentId));
    }

    public MenuDTO findById(Long id){
        return mapper.toDTO(repository.findAllById(id));
    }

    public MenuDTO create(Establishment establishment){
        Menu menu = new Menu();
        menu.setEstablishment(establishment);
        return mapper.toDTO(repository.save(menu));
    }
}
