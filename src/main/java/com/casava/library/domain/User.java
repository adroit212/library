package com.casava.library.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private String id;
    @NotBlank(message = "Full name is required")
    private String fullName;
    @NotBlank(message = "Email is required")
    private String email;
    @NotNull(message = "Membership Date is required")
    private LocalDateTime membershipDate;
}