package com.casava.library.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "user")
public class UserEntity {
    @Id
    private String id;
    private String fullName;
    private String email;
    private LocalDateTime membershipDate;
}
