package com.example.asadmin.mapper;

import com.example.asadmin.dto.ProductItemDTO;
import com.example.asadmin.model.ProductItem;
import org.springframework.stereotype.Component;

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
        productItemDTO.setReadyDuration(productItem.getReadyDuration());
        productItemDTO.setMinAge(productItemDTO.getMinAge());
        productItemDTO.setCost(productItem.getCost());
        productItemDTO.setDescription(productItem.getDescription());
        productItemDTO.setStartAvailableTime(productItemDTO.getStartAvailableTime());
        productItemDTO.setEndAvailableTime(productItem.getEndAvailableTime());
        productItemDTO.setCategories(categoryMapper.toDtos(productItem.getCategories()));
        productItemDTO.setImageUrl(productItemDTO.getImageUrl());

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
        productItem.setReadyDuration(productItemDTO.getReadyDuration());
        productItem.setMinAge(productItemDTO.getMinAge());
        productItem.setCost(productItemDTO.getCost());
        productItem.setDescription(productItemDTO.getDescription());
        productItem.setStartAvailableTime(productItemDTO.getStartAvailableTime());
        productItem.setEndAvailableTime(productItemDTO.getEndAvailableTime());
        productItem.setCategories(categoryMapper.toEntities(productItemDTO.getCategories()));
        productItem.setImageUrl(productItemDTO.getImageUrl());

        return productItem;
    }
}
