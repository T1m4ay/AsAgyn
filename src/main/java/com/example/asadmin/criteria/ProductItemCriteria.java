package com.example.asadmin.criteria;

import com.example.asadmin.model.Categories;

public class ProductItemCriteria extends GeneralCriteria{

    private Categories categories;

    private String query;

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
