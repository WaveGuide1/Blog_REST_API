package io.waveguide.social_media.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePostRequest {

    @NotBlank(message = "PostId is required")
    private String postId;

    @NotBlank(message = "Post title is required")
    private String title;

    @NotBlank(message = "Post Body is required")
    private String body;

    @NotNull(message = "Published is required")
    private Boolean published;

    @NotBlank(message = "UserId is required")
    private String userId;
}
