package org.example.dtos.VideoManagementDTO;

import org.example.constants.VideoVisibility;

public record UploadResponseDTO(
        Long Id,
        String title,
        String video_url,
        String thumbnail_url,
        Long views_count,
        VideoVisibility visibility
) {

}
