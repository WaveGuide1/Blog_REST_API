package io.waveguide.social_media.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    @Id
    private ObjectId postId;

    private String title;
    private String body;
    private Boolean published;
    private ObjectId userId;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
