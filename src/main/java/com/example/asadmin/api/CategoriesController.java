package com.example.asadmin.api;

import com.example.asadmin.dto.CategoryDTO;
import com.example.asadmin.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @GetMapping("")
    public Set<CategoryDTO> getAllCategories(){
        return categoriesService.getAll();
    }
 }
