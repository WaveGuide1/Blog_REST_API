package io.waveguide.social_media.comment;

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
public class Comment {

    @Id
    private ObjectId commentId;

    private String message;
    private ObjectId postId;
    private ObjectId userId;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
