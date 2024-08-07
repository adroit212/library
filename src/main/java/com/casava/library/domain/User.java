package com.casava.library.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private String id;
    private String fullName;
    private String email;
    private LocalDateTime membershipDate;
}
