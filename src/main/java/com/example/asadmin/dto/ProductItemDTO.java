package com.example.asadmin.dto;

import com.example.asadmin.web.rest.errors.FieldErrorInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class ProductItemDTO {

    private Long id;

    private String nameRu;

    private String nameEn;

    private String nameKz;

    private Long readyDuration;

    private int minAge;

    private BigDecimal cost;

    private String description;

    private String startAvailableTime;

    private String endAvailableTime;

    private Set<CategoryDTO> categoryDTOS;

    private String imageUrl;

    @JsonProperty("errorFields")
    private List<FieldErrorInfo> fieldErrorInfos = new ArrayList<>();

    private MenuDTO menuDTO;

    @NotNull
    MultipartFile file;

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
