package com.example.asadmin.api;

import com.example.asadmin.dto.PhotoContent;
import com.example.asadmin.dto.PhotoDTO;
import com.example.asadmin.dto.UploadPhotoDTO;
import com.example.asadmin.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/photo")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST', 'ROLE_CUSTOMER')")
    @PostMapping(value = "/upload-photo", consumes = "multipart/form-data")
    public PhotoDTO uploadImage(@ModelAttribute UploadPhotoDTO uploadPhotoDTO) {
        return photoService.uploadPhoto(uploadPhotoDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST', 'ROLE_CUSTOMER')")
    @GetMapping("/get-photo-content/{id}")
    public ResponseEntity getImage(@PathVariable Long id) throws IOException{
        PhotoContent photoContent = photoService.getImageContent(id);

        if (photoContent == null) {
            throw new FileNotFoundException();
        }

        String extension = "";
        if (photoContent.getContentType().contains("png")) {
            extension += ".png";
        } else if (photoContent.getContentType().contains("jpeg")) {
            extension += ".jpg";
        }

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=certificate" + extension)
                .contentLength(photoContent.getBytes().length)
                .contentType(MediaType.valueOf(photoContent.getContentType()))
                .body(photoContent.getBytes());

    }
}
