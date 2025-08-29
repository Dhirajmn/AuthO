package com.dhiraj.autho.Controller;

import com.dhiraj.autho.Dto.ApiResponse;
import com.dhiraj.autho.Dto.UserLoginRequest;
import com.dhiraj.autho.Dto.UserRegisterRequest;
import com.dhiraj.autho.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@RequestBody UserRegisterRequest request) {
        return userService.saveUser(request);
    }

    @PostMapping("/login/email")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody UserLoginRequest request) {
        userService.loginWithEmail(request);
    }
}
