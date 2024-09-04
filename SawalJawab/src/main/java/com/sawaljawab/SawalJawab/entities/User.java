package com.sawaljawab.SawalJawab.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;
    @NonNull
    @Indexed(unique = true)
    private String userName;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
