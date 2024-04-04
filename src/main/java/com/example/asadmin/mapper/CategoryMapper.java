package com.example.asadmin.mapper;

import com.example.asadmin.dto.CategoryDTO;
import com.example.asadmin.model.Categories;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CategoryMapper {

    public Set<CategoryDTO> toDtos(Set<Categories> categories){
        if(categories == null){
            return null;
        }
        Set<CategoryDTO> dtos = new HashSet<>();
        for(Categories cat : categories){
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(cat.getId());
            categoryDTO.setName(cat.getName());

            dtos.add(categoryDTO);
        }
        return dtos;
    }

    public Set<Categories> toEntities(Set<CategoryDTO> categoryDTOS){
        if(categoryDTOS == null){
            return null;
        }
        Set<Categories> entities = new HashSet<>();
        for(CategoryDTO categoryDTO : categoryDTOS){
            Categories categories = new Categories();
            categories.setId(categoryDTO.getId());
            categories.setName(categoryDTO.getName());

            entities.add(categories);
        }
        return entities;
    }
}
