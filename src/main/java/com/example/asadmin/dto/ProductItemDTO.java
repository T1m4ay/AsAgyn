package com.example.asadmin.dto;

import com.example.asadmin.web.rest.errors.FieldErrorInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProductItemDTO {

    private Long id;

    private String nameRu;

    private String nameEn;

    private String nameKz;

    private ZonedDateTime readyDuration;

    private int minAge;

    private BigDecimal cost;

    private String description;

    private ZonedDateTime startAvailableTime;

    private ZonedDateTime endAvailableTime;

    private Set<CategoryDTO> categoryDTOS;

    private String imageUrl;

    @JsonProperty("errorFields")
    private List<FieldErrorInfo> fieldErrorInfos = new ArrayList<>();

    private MenuDTO menuDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameKz() {
        return nameKz;
    }

    public void setNameKz(String nameKz) {
        this.nameKz = nameKz;
    }

    public ZonedDateTime getReadyDuration() {
        return readyDuration;
    }

    public void setReadyDuration(ZonedDateTime readyDuration) {
        this.readyDuration = readyDuration;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getStartAvailableTime() {
        return startAvailableTime;
    }

    public void setStartAvailableTime(ZonedDateTime startAvailableTime) {
        this.startAvailableTime = startAvailableTime;
    }

    public ZonedDateTime getEndAvailableTime() {
        return endAvailableTime;
    }

    public void setEndAvailableTime(ZonedDateTime endAvailableTime) {
        this.endAvailableTime = endAvailableTime;
    }

    public Set<CategoryDTO> getCategories() {
        return categoryDTOS;
    }

    public void setCategories(Set<CategoryDTO> categoryDTOS) {
        this.categoryDTOS = categoryDTOS;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<FieldErrorInfo> getFieldErrorInfos() {
        return fieldErrorInfos;
    }

    public void setFieldErrorInfos(List<FieldErrorInfo> fieldErrorInfos) {
        this.fieldErrorInfos = fieldErrorInfos;
    }

    public Set<CategoryDTO> getCategoryDTOS() {
        return categoryDTOS;
    }

    public void setCategoryDTOS(Set<CategoryDTO> categoryDTOS) {
        this.categoryDTOS = categoryDTOS;
    }

    public MenuDTO getMenuDTO() {
        return menuDTO;
    }

    public void setMenuDTO(MenuDTO menuDTO) {
        this.menuDTO = menuDTO;
    }

    @Override
    public String toString() {
        return "ProductItemDTO{" +
                "id=" + id +
                ", nameRu='" + nameRu + '\'' +
                ", nameEn='" + nameEn + '\'' +
                ", nameKz='" + nameKz + '\'' +
                ", readyDuration=" + readyDuration +
                ", minAge=" + minAge +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", startAvailableTime=" + startAvailableTime +
                ", endAvailableTime=" + endAvailableTime +
                ", categoryDTOS=" + categoryDTOS +
                ", imageUrl='" + imageUrl + '\'' +
                ", fieldErrorInfos=" + fieldErrorInfos +
                '}';
    }
}
