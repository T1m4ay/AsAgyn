package com.example.asadmin.mapper;

import com.example.asadmin.dto.MenuDTO;
import com.example.asadmin.model.Menu;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {

    private EstablishmentMapper establishmentMapper;

    public MenuMapper(EstablishmentMapper establishmentMapper){
        this.establishmentMapper = establishmentMapper;
    }

    public MenuDTO toDTO(Menu menu){
        if(menu == null){
            return null;
        }

        MenuDTO menuDTO = new MenuDTO();

        menuDTO.setId(menu.getId());
        menuDTO.setEstablishmentDTO(establishmentMapper.toDTO(menu.getEstablishment()));

        return menuDTO;
    }

    public Menu toEntity(MenuDTO menuDTO){
        if(menuDTO == null){
            return null;
        }

        Menu menu = new Menu();

        menu.setId(menuDTO.getId());
        menu.setEstablishment(establishmentMapper.toEntity(menuDTO.getEstablishmentDTO()));

        return menu;
    }
}
