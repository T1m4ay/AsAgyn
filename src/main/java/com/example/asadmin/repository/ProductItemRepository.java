package com.example.asadmin.repository;

import com.example.asadmin.model.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem, Long>, JpaSpecificationExecutor<ProductItem> {
}
