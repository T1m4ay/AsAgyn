package com.example.asadmin.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PhotoContent {

    String contentType;

    byte[] bytes;

    String originalFilename;
}
