package com.example.asadmin.mapper;

import com.example.asadmin.dto.ProductItemDTO;
import com.example.asadmin.model.ProductItem;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class ProductItemMapper {
    private final CategoryMapper categoryMapper;

    public ProductItemMapper(CategoryMapper categoryMapper){
        this.categoryMapper = categoryMapper;
    }

    public ProductItemDTO toDto(ProductItem productItem){
        if(productItem == null){
            return null;
        }

        ProductItemDTO productItemDTO = new ProductItemDTO();
        productItemDTO.setId(productItem.getId());
        productItemDTO.setNameRu(productItem.getNameRu());
        productItemDTO.setNameEn(productItem.getNameEn());
        productItemDTO.setNameKz(productItem.getNameKz());
        productItemDTO.setReadyDuration(productItem.getReadyDuration().toSeconds());
        productItemDTO.setMinAge(productItem.getMinAge());
        productItemDTO.setCost(productItem.getCost());
        productItemDTO.setDescription(productItem.getDescription());
        if (productItem.getStartAvailableTime() != null) {
            productItemDTO.setStartAvailableTime(productItem.getStartAvailableTime().toString());
        }
        if (productItem.getEndAvailableTime() != null) {
            productItemDTO.setEndAvailableTime(productItem.getEndAvailableTime().toString());
        }
        productItemDTO.setCategoryDTOS(categoryMapper.toDtos(productItem.getCategories()));
        productItemDTO.setImageUrl(productItem.getImageUrl());

        return productItemDTO;
    }

    public ProductItem toEntity(ProductItemDTO productItemDTO){
        if(productItemDTO == null){
            return null;
        }

        ProductItem productItem = new ProductItem();
        productItem.setId(productItemDTO.getId());
        productItem.setNameRu(productItemDTO.getNameRu());
        productItem.setNameEn(productItemDTO.getNameEn());
        productItem.setNameKz(productItemDTO.getNameKz());
        productItem.setReadyDuration(Duration.ofSeconds(productItemDTO.getReadyDuration()));
        productItem.setMinAge(productItemDTO.getMinAge());
        productItem.setCost(productItemDTO.getCost());
        productItem.setDescription(productItemDTO.getDescription());
        if (productItemDTO.getStartAvailableTime() != null) {
            productItem.setStartAvailableTime(ZonedDateTime.parse(productItemDTO.getStartAvailableTime()));
        }
        if (productItemDTO.getEndAvailableTime() != null) {
            productItem.setEndAvailableTime(ZonedDateTime.parse(productItemDTO.getEndAvailableTime()));
        }
        productItem.setCategories(categoryMapper.toEntities(productItemDTO.getCategoryDTOS()));
        productItem.setImageUrl(productItemDTO.getImageUrl());

        return productItem;
    }

    public Set<ProductItemDTO> toDtos(Set<ProductItem> productItems){
        if(productItems == null){
            return null;
        }

        Set<ProductItemDTO> productItemDTOs = new HashSet<>();
        for(ProductItem productItem : productItems) {
            ProductItemDTO productItemDTO = new ProductItemDTO();
            productItemDTO.setId(productItem.getId());
            productItemDTO.setNameRu(productItem.getNameRu());
            productItemDTO.setNameEn(productItem.getNameEn());
            productItemDTO.setNameKz(productItem.getNameKz());
            productItemDTO.setReadyDuration(productItem.getReadyDuration().toSeconds());
            productItemDTO.setMinAge(productItem.getMinAge());
            productItemDTO.setCost(productItem.getCost());
            productItemDTO.setDescription(productItem.getDescription());
            if (productItem.getStartAvailableTime() != null) {
                productItemDTO.setStartAvailableTime(productItem.getStartAvailableTime().toString());
            }
            if(productItem.getEndAvailableTime() != null) {
                productItemDTO.setEndAvailableTime(productItem.getEndAvailableTime().toString());
            }
            productItemDTO.setCategoryDTOS(categoryMapper.toDtos(productItem.getCategories()));
            productItemDTO.setImageUrl(productItem.getImageUrl());
            productItemDTOs.add(productItemDTO);
        }

        return productItemDTOs;
    }
}
