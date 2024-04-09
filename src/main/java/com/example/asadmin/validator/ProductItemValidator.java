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
        if(ObjectUtils.isEmpty(dto.getCategories())){
            dto.getFieldErrorInfos()
                    .add(
                            new FieldErrorInfo("category",
                                    getLocalizedMessage("Нужно выбрать один или несколько категориев")));
            validatorResponse.setHasErrors(true);
        }
        if(ObjectUtils.isEmpty(dto.getCost())){
            dto.getFieldErrorInfos()
                    .add(
                            new FieldErrorInfo("cost",
                                    getLocalizedMessage("Поле стоимость должно быть заполненым")));
            validatorResponse.setHasErrors(true);
        }
        if(ObjectUtils.isEmpty(dto.getReadyDuration())){
            dto.getFieldErrorInfos()
                    .add(
                            new FieldErrorInfo("readyDuration",
                                    getLocalizedMessage("Время готовки должно быть заполненым")));
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
