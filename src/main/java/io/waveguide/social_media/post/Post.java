package io.waveguide.social_media.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Post {

    @Id
    private ObjectId postId;

    private String title;
    private String body;
    private Boolean published;
    private ObjectId userId;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
