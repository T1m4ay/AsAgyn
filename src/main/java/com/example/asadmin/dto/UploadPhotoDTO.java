package com.example.asadmin.dto;

import com.sun.istack.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class UploadPhotoDTO {

    @NotNull
    MultipartFile file;
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
