package io.waveguide.social_media.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    @Id
    private String commentId;

    private String message;
    private String postId;
    private String userId;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
