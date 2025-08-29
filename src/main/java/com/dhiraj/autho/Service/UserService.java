package com.dhiraj.autho.Service;

import com.dhiraj.autho.Dto.ApiResponse;
import com.dhiraj.autho.Dto.UserLoginRequest;
import com.dhiraj.autho.Dto.UserRegisterRequest;
import com.dhiraj.autho.Entity.User;
import com.dhiraj.autho.Repository.UserRepository;
import com.dhiraj.autho.Config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public ResponseEntity<ApiResponse<String>> saveUser(UserRegisterRequest request) {
        if (userRepo.existsByUsername(request.getUsername())) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Username already exists"), HttpStatus.CONFLICT);
        }
        if (userRepo.existsByEmail(request.getEmail())) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Email already exists"), HttpStatus.CONFLICT);
        }
        User user = User
                .builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepo.save(user);
        return new ResponseEntity<>(new ApiResponse<>(true, "User created"), HttpStatus.CREATED);
    }

    public ResponseEntity<ApiResponse<String>> authenticate(UserLoginRequest request) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            String jwt = jwtService.generateToken(request.getUsername());

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                    .body(new ApiResponse<>(true, "login successful", null));

        } catch (AuthenticationException e) {
            return new ResponseEntity<>(new ApiResponse<>(false, "Invalid username or password"), HttpStatus.UNAUTHORIZED);
        }
    }
}
