package com.example.asadmin.mapper;

import com.example.asadmin.dto.PhotoDTO;
import com.example.asadmin.model.Photo;
import org.springframework.stereotype.Component;

@Component
public class PhotoMapper {

    public PhotoDTO toDTO(Photo photo){
        if(photo == null){
            return null;
        }
        PhotoDTO dto = new PhotoDTO();
        dto.setId(photo.getId());
        dto.setFilename(photo.getFilename());
        dto.setFilepath(photo.getFilepath());
        dto.setUrl(photo.getUrl());
        dto.setContentType(photo.getContentType());
        dto.setInputDir(photo.getInputDir());
        dto.setOriginalFilename(photo.getOriginalFilename());

        return dto;
    }

    public Photo toEntity(PhotoDTO photoDTO){
        if(photoDTO == null){
            return null;
        }
        Photo entity = new Photo();
        entity.setId(photoDTO.getId());
        entity.setFilename(photoDTO.getFilename());
        entity.setFilepath(photoDTO.getFilepath());
        entity.setUrl(photoDTO.getUrl());
        entity.setContentType(photoDTO.getContentType());
        entity.setInputDir(photoDTO.getInputDir());
        entity.setOriginalFilename(photoDTO.getOriginalFilename());

        return entity;
    }
}
