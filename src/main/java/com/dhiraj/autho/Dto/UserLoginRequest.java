package com.dhiraj.autho.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class UserLoginRequest {

    @Column(nullable = false)
    @NotBlank(message = "username is required")
    private String username;

    @Column(nullable = false)
    @Size(min = 6, message = "password must be at least 6 characters")
    private String password;
}
