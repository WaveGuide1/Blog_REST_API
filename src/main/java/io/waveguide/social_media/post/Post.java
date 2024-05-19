package io.waveguide.social_media.post;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Document(collection = "posts")
public class Post {

    @Id
    private String postId;

    private String title;
    private String body;

    @JsonProperty
    private boolean isDeleted = false;

    private String userId;

    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
