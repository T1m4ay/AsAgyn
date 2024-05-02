package com.example.asadmin.service;

import com.example.asadmin.criteria.ProductItemCriteria;
import com.example.asadmin.dto.MenuDTO;
import com.example.asadmin.dto.PageResponse;
import com.example.asadmin.dto.ProductItemDTO;
import com.example.asadmin.dto.ResponseDTO;
import com.example.asadmin.mapper.CategoryMapper;
import com.example.asadmin.mapper.MenuMapper;
import com.example.asadmin.mapper.ProductItemMapper;
import com.example.asadmin.model.Categories;
import com.example.asadmin.model.Menu;
import com.example.asadmin.model.ProductItem;
import com.example.asadmin.repository.ProductItemRepository;
import com.example.asadmin.validator.ProductItemValidator;
import com.example.asadmin.validator.ValidatorResponse;
import com.example.asadmin.web.rest.confirm.AppMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductItemService {

    private final ProductItemRepository repository;

    private final ProductItemMapper mapper;

    private final ProductItemValidator productItemValidator;

    private final CategoryMapper categoryMapper;

    private final MenuService menuService;
    private final MenuMapper menuMapper;

    public ProductItem save(ProductItem productItem){
        return repository.save(productItem);
    }

    public PageResponse<ProductItemDTO> getAll(ProductItemCriteria criteria, Set<String> sort, Long menuId){
        Sort sortBy = createSortFromStringSet(sort);
        Specification<ProductItem> specification = createSpecification(criteria, menuId);

        Page<ProductItem> productItemPage = repository.findAll(
                specification,
                PageRequest.of(criteria.getPage() > 0 ? criteria.getPage() - 1 : 0, criteria.getSize(), sortBy));

        PageResponse<ProductItemDTO> pageResponse = new PageResponse<>();
        pageResponse.setItems(productItemPage.stream().map(mapper::toDto).collect(Collectors.toList()));
        pageResponse.setTotalPages(productItemPage.getTotalPages());
        pageResponse.setTotalCount(productItemPage.getNumber());

        return pageResponse;
    }

    public Specification<ProductItem> createSpecification(ProductItemCriteria criteria, Long menuId){
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.getCategoryDTO() != null) {
                log.info("criteria.getCategoryDTO() not null");
                Join<ProductItem, Categories> categoriesJoin = root.join("categories", JoinType.INNER);
                predicates.add(criteriaBuilder.equal(categoriesJoin.get("name") , criteria.getCategoryDTO().getName()));
            }
            if (criteria.getQuery() != null) {
                log.info("criteria.getQuery() not null");
                if (!criteria.getQuery().trim().isEmpty()) {
                    log.info("criteria.getQuery().trim().isEmpty() not null");
                    Predicate nameRuPredicate = criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("nameRu")),
                            "%" + criteria.getQuery().toLowerCase() + "%");
                    Predicate descriptionPredicate = criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("description")),
                            "%" + criteria.getQuery().toLowerCase() + "%");
                    predicates.add(criteriaBuilder.or(nameRuPredicate,descriptionPredicate));
                }
            }
            Join<ProductItem, Menu> menuJoin = root.join("menu");
            predicates.add(criteriaBuilder.and(menuJoin.get("id").in(menuId)));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Sort createSortFromStringSet(Set<String> sortStrings) {
        Sort sortBy = Sort.unsorted();
        if (sortStrings == null) {
            sortStrings = new HashSet<>();
            sortStrings.add("id_desc");
        }

        if (sortStrings.contains("id")) {
            sortBy = sortBy.and(Sort.by("id"));
        } else if (sortStrings.contains("id_asc")) {
            sortBy = sortBy.and(Sort.by("id").ascending());
        } else if (sortStrings.contains("id_desc")) {
            sortBy = sortBy.and(Sort.by("id").descending());
        }

        if (sortStrings.contains("nameRu")) {
            sortBy = sortBy.and(Sort.by("nameRu"));
        } else if (sortStrings.contains("nameRu_asc")) {
            sortBy = sortBy.and(Sort.by("nameRu").ascending());
        } else if (sortStrings.contains("nameRu_desc")) {
            sortBy = sortBy.and(Sort.by("nameRu").descending());
        }

        if (sortStrings.contains("imageUrl")) {
            sortBy = sortBy.and(Sort.by("imageUrl"));
        } else if (sortStrings.contains("imageUrl_asc")) {
            sortBy = sortBy.and(Sort.by("imageUrl").ascending());
        } else if (sortStrings.contains("imageUrl_desc")) {
            sortBy = sortBy.and(Sort.by("imageUrl").descending());
        }

        if (sortStrings.contains("description")) {
            sortBy = sortBy.and(Sort.by("description"));
        } else if (sortStrings.contains("description_asc")) {
            sortBy = sortBy.and(Sort.by("description").ascending());
        } else if (sortStrings.contains("description_desc")) {
            sortBy = sortBy.and(Sort.by("description").descending());
        }

        if (sortStrings.contains("cost")) {
            sortBy = sortBy.and(Sort.by("cost"));
        } else if (sortStrings.contains("cost_asc")) {
            sortBy = sortBy.and(Sort.by("cost").ascending());
        } else if (sortStrings.contains("cost_desc")) {
            sortBy = sortBy.and(Sort.by("cost").descending());
        }

        return sortBy;
    }

    public ResponseDTO<ProductItemDTO> create(Long menuId, ProductItemDTO productItemDTO){
        productItemDTO.setId(null);
        ValidatorResponse<ProductItemDTO> validatorResponse = productItemValidator.validate(productItemDTO);
        ResponseDTO<ProductItemDTO> responseDTO = new ResponseDTO<>();

        productItemDTO = validatorResponse.getObject();
        responseDTO.setObject(validatorResponse.getObject());
        responseDTO.setHasErrors(validatorResponse.isHasErrors());

        if(!responseDTO.getHasErrors()) {
            ProductItem productItem = mapper.toEntity(productItemDTO);
            productItem.setMenu(menuMapper.toEntity(menuService.findById(menuId)));

            repository.save(productItem);
        }

        return responseDTO;
    }

    public ResponseDTO<ProductItemDTO> update(ProductItemDTO productItemDTO, Long id){
        ResponseDTO<ProductItemDTO> responseDTO = new ResponseDTO<>();
        ProductItem productItem = repository.findById(id).orElse(null);
        updateProduct(productItemDTO, productItem);
        responseDTO.setObject(productItemDTO);
        responseDTO.setHasErrors(false);

        if(!responseDTO.getHasErrors()) {
            repository.save(productItem);
        }

        return responseDTO;
    }

    public void updateProduct(ProductItemDTO productItemDTO, ProductItem productItem){
        if(!ObjectUtils.isEmpty(productItemDTO.getNameRu())){
            productItem.setNameRu(productItemDTO.getNameRu());
        }
        if(!ObjectUtils.isEmpty(productItemDTO.getNameEn())){
            productItem.setNameEn(productItemDTO.getNameEn());
        }
        if(!ObjectUtils.isEmpty(productItemDTO.getNameKz())){
            productItem.setNameKz(productItemDTO.getNameKz());
        }
        if(!ObjectUtils.isEmpty(productItemDTO.getReadyDuration())){
            productItem.setReadyDuration(productItemDTO.getReadyDuration());
        }
        if(!ObjectUtils.isEmpty(productItemDTO.getMinAge())){
            productItem.setMinAge(productItemDTO.getMinAge());
        }
        if(!ObjectUtils.isEmpty(productItemDTO.getCost())){
            productItem.setCost(productItemDTO.getCost());
        }
        if(!ObjectUtils.isEmpty(productItemDTO.getDescription())){
            productItem.setDescription(productItemDTO.getDescription());
        }
        if(!ObjectUtils.isEmpty(productItemDTO.getStartAvailableTime())){
            productItem.setStartAvailableTime(productItemDTO.getStartAvailableTime());
        }
        if(!ObjectUtils.isEmpty(productItemDTO.getEndAvailableTime())){
            productItem.setEndAvailableTime(productItemDTO.getEndAvailableTime());
        }
        if(!ObjectUtils.isEmpty(productItemDTO.getCategoryDTOS())){
            productItem.setCategories(categoryMapper.toEntities(productItemDTO.getCategoryDTOS()));
        }
        if(!ObjectUtils.isEmpty(productItemDTO.getImageUrl())){
            productItem.setImageUrl(productItemDTO.getImageUrl());
        }
    }

    public ProductItemDTO getById(Long id){
        return mapper.toDto(repository.getById(id));
    }

    public ResponseEntity<?> deleteById(Long id){
        repository.deleteById(id);
        return ResponseEntity.ok(new AppMessage("Удаление товара с id " + " прошла успешно"));
    }
}
