package com.example.asadmin.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PhotoDTO {

    private Long id;

    private String inputDir;

    private String filename;

    private String originalFilename;

    private String url;

    private String contentType;

    private String filepath;
}
