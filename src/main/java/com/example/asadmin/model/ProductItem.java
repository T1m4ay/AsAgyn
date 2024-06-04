package com.example.asadmin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product_item")
public class ProductItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "name_kz")
    private String nameKz;

    @Column(name = "ready_duration")
    private Duration readyDuration;

    @Column(name = "min_age")
    private int minAge;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "description")
    private String description;

    @Column(name = "start_available_time")
    private ZonedDateTime startAvailableTime;

    @Column(name = "end_available_time")
    private ZonedDateTime endAvailableTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rel_category__product_item",
            joinColumns = @JoinColumn(name = "product_item_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonIgnoreProperties(value = "productItems", allowSetters = true)
    private Set<Categories> categories = new HashSet<>();

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

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

    public Duration getReadyDuration() {
        return readyDuration;
    }

    public void setReadyDuration(Duration readyDuration) {
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

    public Set<Categories> getCategories() {
        return categories;
    }

    public void setCategories(Set<Categories> categories) {
        this.categories = categories;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}