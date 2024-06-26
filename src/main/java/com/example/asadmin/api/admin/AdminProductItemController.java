package com.example.asadmin.api.admin;

import com.example.asadmin.criteria.ProductItemCriteria;
import com.example.asadmin.dto.PageResponse;
import com.example.asadmin.dto.ProductItemDTO;
import com.example.asadmin.dto.ResponseDTO;
import com.example.asadmin.service.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/admin/api/product-item")
public class AdminProductItemController {

    @Autowired
    ProductItemService productItemService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/in-menu/{menuId}")
    public PageResponse<ProductItemDTO> getAllProductItemsPage(
            @PathVariable Long menuId,
            ProductItemCriteria productItemCriteria,
            @RequestParam(required = false) Set<String> sort){
        return productItemService.getAll(productItemCriteria, sort, menuId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/in-menu/{menuId}")
    public ResponseEntity<ProductItemDTO> create(@PathVariable Long menuId,@RequestBody ProductItemDTO productItemDTO){
        ResponseDTO<ProductItemDTO> responseDTO = productItemService.create(menuId, productItemDTO);
        if (responseDTO.getHasErrors()) {
            return ResponseEntity.badRequest().body(responseDTO.getObject());
        }
        return ResponseEntity.ok(responseDTO.getObject());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ProductItemDTO getProductById(
            @PathVariable Long id){
        return productItemService.getById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(
            @PathVariable Long id){
        return productItemService.deleteById(id);
    }
}
