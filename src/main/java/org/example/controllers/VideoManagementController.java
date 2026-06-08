package org.example.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dtos.VideoManagementDTO.UploadRequestDTO;
import org.example.dtos.VideoManagementDTO.UploadResponseDTO;
import org.example.services.VideoManagementService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileManagement")
@RequiredArgsConstructor
public class VideoManagementController {
    private final VideoManagementService videoService;
    // file upload
    @PostMapping(
            value = "/video",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE  // tells spring to accept only formdata/multipart as content type
    )
    ResponseEntity<UploadResponseDTO> handleUpload(
            @Valid @RequestPart("metadata") UploadRequestDTO metadata,
            @RequestPart("file")MultipartFile file
    ){
        UploadResponseDTO res = videoService.upload(file,metadata);
        return ResponseEntity.ok(res);
    }
}
