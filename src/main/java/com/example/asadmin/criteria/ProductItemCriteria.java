package com.example.asadmin.criteria;

import com.example.asadmin.dto.CategoryDTO;
import com.example.asadmin.model.Categories;

public class ProductItemCriteria extends GeneralCriteria{

    private CategoryDTO categoryDTO;

    private String query;

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
