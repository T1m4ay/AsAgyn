package com.example.asadmin.api.mobile;

import com.example.asadmin.dto.CategoryDTO;
import com.example.asadmin.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/mobile/api/categories")
public class MobileCategoriesController {

    @Autowired
    CategoriesService categoriesService;

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_GUEST')")
    @GetMapping("")
    public Set<CategoryDTO> getAllCategories(){
        return categoriesService.getAll();
    }
}
