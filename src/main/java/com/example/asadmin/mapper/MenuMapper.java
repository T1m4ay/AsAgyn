package com.example.asadmin.mapper;

import com.example.asadmin.dto.MenuDTO;
import com.example.asadmin.model.Menu;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {

    private EstablishmentMapper establishmentMapper;

    private ProductItemMapper productItemMapper;

    public MenuMapper(
            EstablishmentMapper establishmentMapper,
            ProductItemMapper productItemMapper
    ){
        this.establishmentMapper = establishmentMapper;
        this.productItemMapper = productItemMapper;
    }

    public MenuDTO toDTO(Menu menu){
        if(menu == null){
            return null;
        }

        MenuDTO menuDTO = new MenuDTO();

        menuDTO.setId(menu.getId());
        if(menu.getProductItems() != null){
            menuDTO.setProductItemDTOs(productItemMapper.toDtos(menu.getProductItems()));
        }

        return menuDTO;
    }

    public Menu toEntity(MenuDTO menuDTO){
        if(menuDTO == null){
            return null;
        }

        Menu menu = new Menu();

        menu.setId(menuDTO.getId());

        return menu;
    }
}
