package com.example.asadmin.api.admin;

import com.example.asadmin.dto.CategoryDTO;
import com.example.asadmin.service.CategoriesService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/admin/api/categories")
public class AdminCategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("")
    @ApiOperation("Получение всех категориев")
    public Set<CategoryDTO> getAllCategories(){
        return categoriesService.getAll();
    }
 }
