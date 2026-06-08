package org.example.dtos.VideoManagementDTO;

import jakarta.validation.constraints.*;
import org.example.constants.VideoVisibility;

public record UploadRequestDTO(
        @NotBlank
        String title,

        String description,

        @NotNull
        VideoVisibility visibility

) {
}
