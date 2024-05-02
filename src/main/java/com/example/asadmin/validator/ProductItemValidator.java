package com.example.asadmin.validator;

import com.example.asadmin.dto.ProductItemDTO;
import com.example.asadmin.web.rest.errors.FieldErrorInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
public class ProductItemValidator extends BaseValidator<ProductItemDTO>{

    @Override
    public ValidatorResponse<ProductItemDTO> validate(ProductItemDTO dto){
        ValidatorResponse<ProductItemDTO> validatorResponse = new ValidatorResponse<>();
        validatorResponse.setObject(dto);
        if(ObjectUtils.isEmpty(dto.getCategoryDTOS())){
            dto.getFieldErrorInfos()
                    .add(
                            new FieldErrorInfo("category",
                                    getLocalizedMessage("error.validation.productitem.category")));
            validatorResponse.setHasErrors(true);
        }
        if(ObjectUtils.isEmpty(dto.getCost())){
            dto.getFieldErrorInfos()
                    .add(
                            new FieldErrorInfo("cost",
                                    getLocalizedMessage("error.validation.productitem.cost")));
            validatorResponse.setHasErrors(true);
        }
        if(ObjectUtils.isEmpty(dto.getReadyDuration())){
            dto.getFieldErrorInfos()
                    .add(
                            new FieldErrorInfo("readyDuration",
                                    getLocalizedMessage("error.validation.productitem.readyDuration")));
            validatorResponse.setHasErrors(true);
        }
        if(ObjectUtils.isEmpty(dto.getNameRu())){
            dto.getFieldErrorInfos()
                    .add(
                            new FieldErrorInfo("productName",
                                    getLocalizedMessage("error.validation.productitem.name")));
            validatorResponse.setHasErrors(true);
        }
        validatorResponse.setObject(dto);
        List<FieldErrorInfo> requestErrors = validatorResponse.getObject().getFieldErrorInfos();
        validatorResponse.setHasErrors(!requestErrors.isEmpty());
        return validatorResponse;
    }
}
