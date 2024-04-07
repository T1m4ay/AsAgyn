package com.example.asadmin.api;

import com.example.asadmin.criteria.ProductItemCriteria;
import com.example.asadmin.dto.PageResponse;
import com.example.asadmin.dto.ProductItemDTO;
import com.example.asadmin.dto.ResponseDTO;
import com.example.asadmin.service.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/api/product-item")
public class ProductItemController {

    @Autowired
    ProductItemService productItemService;

    @GetMapping("")
    public PageResponse<ProductItemDTO> getAllProductItemsPage(
            ProductItemCriteria productItemCriteria,
            @RequestParam(required = false) Set<String> sort){
        return productItemService.getAll(productItemCriteria, sort);
    }

    @PostMapping("")
    public ResponseEntity<ProductItemDTO> create(@RequestBody ProductItemDTO productItemDTO){
        ResponseDTO<ProductItemDTO> responseDTO = productItemService.create(productItemDTO);
        if (responseDTO.getHasErrors()) {
            return ResponseEntity.badRequest().body(responseDTO.getObject());
        }
        return ResponseEntity.ok(responseDTO.getObject());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductItemDTO> partialUpdate(
            @PathVariable Long id,
            @RequestBody ProductItemDTO productItemDTO){
        ResponseDTO<ProductItemDTO> responseDTO = productItemService.update(productItemDTO, id);
        if (responseDTO.getHasErrors()) {
            return ResponseEntity.badRequest().body(responseDTO.getObject());
        }
        return ResponseEntity.ok(responseDTO.getObject());
    }
}
