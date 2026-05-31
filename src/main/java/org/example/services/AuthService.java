package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.Exception.AuthException;
import org.example.dtos.SignupRequestDTO;
import org.example.dtos.SignupResponseDTO;
import org.example.entities.User;
//import org.example.repositories.RefreshTokenRepository;
import org.example.repositories.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
//    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;

    public SignupResponseDTO signup(SignupRequestDTO request) {
        String name = request.name();
        String email = request.email();
        String password = request.password();

        // user validation
        if(userRepository.existsByEmail(email)){
            throw new AuthException(HttpStatus.CONFLICT , "User with email " + email + " already exists");
        }

        String passwordHash = passwordEncoder.encode(password);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordHash);

        User savedUser = userRepository.save(user);

        return new SignupResponseDTO(savedUser.getId(), "sample token" , "sample refresh token" , "User created Successfully");


    }
}
