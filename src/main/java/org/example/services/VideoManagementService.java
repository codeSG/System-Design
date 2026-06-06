package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dtos.VideoManagementDTO.UploadRequestDTO;
import org.example.dtos.VideoManagementDTO.UploadResponseDTO;
import org.example.entities.Video;
import org.example.repositories.VideoRepository;
import org.example.services.validation.FileValidation;
import org.example.strategy.StorageStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoManagementService {
    private final FileValidation fileValidation;
    private final StorageStrategy storage;

    private final VideoRepository videoRepository;

    public UploadResponseDTO upload(MultipartFile file , UploadRequestDTO metadata){
        // Validate File
        fileValidation.validate(file);

        /* Store File */
        // create storage key
        String storageKey = "videos/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        storage.store(file,storageKey);

        //save metdata in DB
        Video video = new Video(metadata.title() , storageKey , metadata.visibility());
        Video savedVideo = videoRepository.save(video);
        return new UploadResponseDTO(
                savedVideo.getId(),
                savedVideo.getTitle(),
                storageKey,
                null, // thumbnail url can be generated later
                0L, // initial views count
                savedVideo.getVisibility()
        );
    }
}
