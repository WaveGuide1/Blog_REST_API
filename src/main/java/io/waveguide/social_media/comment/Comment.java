package io.waveguide.social_media.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Comment {

    @Id
    private String commentId;

    private String message;

    private String postId;

    private String userId;

    @JsonIgnore
    private boolean isDeleted = false;

    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
