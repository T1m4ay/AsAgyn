package com.example.asadmin.dto;

import java.math.BigDecimal;

public class EstablishmentDTO {

    private Long id;

    private String establishmentName;

    private BigDecimal servingPercentage;

    private String backgroundImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstablishmentName() {
        return establishmentName;
    }

    public void setEstablishmentName(String establishmentName) {
        this.establishmentName = establishmentName;
    }

    public BigDecimal getServingPercentage() {
        return servingPercentage;
    }

    public void setServingPercentage(BigDecimal servingPercentage) {
        this.servingPercentage = servingPercentage;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}
