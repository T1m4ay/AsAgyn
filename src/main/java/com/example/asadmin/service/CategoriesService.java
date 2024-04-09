package com.example.asadmin.service;

import com.example.asadmin.dto.CategoryDTO;
import com.example.asadmin.mapper.CategoryMapper;
import com.example.asadmin.model.Categories;
import com.example.asadmin.repository.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoriesRepository repository;

    private final CategoryMapper mapper;

    public Set<CategoryDTO> getAll(){
        List<Categories> categoriesList = repository.findAll();
        return mapper.toDtos(new HashSet<>(categoriesList));
    }
}
