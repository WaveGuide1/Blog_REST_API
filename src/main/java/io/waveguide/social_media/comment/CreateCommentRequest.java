package io.waveguide.social_media.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

    @NotBlank(message = "Comment message is required")
    private String message;

    @NotBlank(message = "PostId is required")
    private ObjectId postId;

    @NotBlank(message = "UserId is required")
    private ObjectId userId;
}
