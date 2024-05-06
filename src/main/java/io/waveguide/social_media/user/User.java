package io.waveguide.social_media.user;

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
public class User {

    @Id
    private ObjectId userId;

    private String fullName;
    private String username;
    private String password;
    private Byte role;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}