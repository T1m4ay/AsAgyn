package com.example.asadmin.service;

import com.example.asadmin.dto.PhotoContent;
import com.example.asadmin.dto.PhotoDTO;
import com.example.asadmin.dto.UploadPhotoDTO;
import com.example.asadmin.mapper.PhotoMapper;
import com.example.asadmin.model.Photo;
import com.example.asadmin.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private static final String DATADIR = "/home/data/photo";

    private final PhotoRepository repository;
    private final PhotoMapper mapper;

    public PhotoDTO uploadPhoto(UploadPhotoDTO uploadPhotoDTO){
        UUID filenameId = UUID.randomUUID();
        String inputDir = DATADIR + "/" + LocalDate.now().getYear() + "/" + LocalDate.now().getMonth().getValue();

        File in = new File(inputDir);
        if (!in.exists()) {
            in.mkdirs();
        }

        String filepath = inputDir + "/" + filenameId;
        File file = new File(filepath);
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)
        ) {
            bufferedOutputStream.write(uploadPhotoDTO.getFile().getBytes());
            bufferedOutputStream.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found error", e);
        } catch (IOException e) {
            throw new RuntimeException("IO error", e);
        }

        PhotoDTO photoDTO = new PhotoDTO();
        photoDTO.setOriginalFilename(uploadPhotoDTO.getFile().getOriginalFilename());
        photoDTO.setFilename(filenameId.toString());
        photoDTO.setInputDir(inputDir);
        photoDTO.setFilepath(filepath);
        photoDTO.setContentType(uploadPhotoDTO.getFile().getContentType());

        PhotoDTO output = save(photoDTO);
        output.setUrl("/demo/api/photo/get-photo-content/" + output.getId());
        partialUpdate(output);
        return output;
    }

    public PhotoContent getImageContent(Long id) throws IOException {
        PhotoDTO photoDTO = mapper.toDTO(repository.findById(id).orElse(null));
        if (photoDTO != null) {
            PhotoContent photoContent = new PhotoContent();
            byte[] bytes = FileUtils.readFileToByteArray(new File(photoDTO.getFilepath()));
            photoContent.setBytes(bytes);
            photoContent.setContentType(photoDTO.getContentType());
            return photoContent;
        }

        return null;
    }

    public PhotoDTO save(PhotoDTO photoDTO){
        Photo photo = mapper.toEntity(photoDTO);
        photo = repository.save(photo);
        return mapper.toDTO(photo);
    }

    public Optional<PhotoDTO> partialUpdate(PhotoDTO photoDTO){
        return repository.findById(photoDTO.getId()).map(repository::save).map(mapper::toDTO);
    }
}
