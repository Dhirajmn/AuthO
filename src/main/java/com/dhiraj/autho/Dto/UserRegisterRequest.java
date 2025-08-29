package com.dhiraj.autho.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterRequest {

    @Column(unique = true, nullable = false)
    @Size(min = 3, message = "username must be at least 3 characters")
    private String username;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "email is required")
    private String email;

    @Column(nullable = false)
    @Size(min = 6, message = "password must be at least 6 characters")
    private String password;

}
