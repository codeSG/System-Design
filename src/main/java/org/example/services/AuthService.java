package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.Exception.AuthException;
import org.example.dtos.AuthenticationDTO.RefreshTokenPayloadDTO;
import org.example.dtos.AuthenticationDTO.SignupRequestDTO;
import org.example.dtos.AuthenticationDTO.SignupResponseDTO;
import org.example.dtos.AuthenticationDTO.AccessTokenPayloadDTO;
import org.example.entities.RefreshToken;
import org.example.entities.User;
import org.example.repositories.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;


    private final RefreshTokenService refreshTokenService;
    private final JWTTokenService jwtTokenService;

    private final PasswordEncoder passwordEncoder;


    public SignupResponseDTO signup(SignupRequestDTO request) {
        String name = request.name();
        String email = request.email();
        String password = request.password();

        // user validation
        if(userRepository.existsByEmail(email)){
            System.out.println("User with email " + email + " already exists");
            throw new AuthException(HttpStatus.CONFLICT , "User with email " + email + " already exists");
        }

        String passwordHash = passwordEncoder.encode(password);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordHash);

        User savedUser = userRepository.save(user);

        //generate access token and refresh token
        String accessToken = jwtTokenService.generateAccessToken(new AccessTokenPayloadDTO(savedUser.getId(), savedUser.getEmail()));
        String refreshToken = refreshTokenService.generateRefreshToken();
        RefreshToken savedRefreshToken = refreshTokenService.saveRefreshTokenToDB(new RefreshTokenPayloadDTO(user , refreshToken));
        System.out.println("savedRefreshToken " + savedRefreshToken);

        return new SignupResponseDTO(savedUser.getId(), accessToken , refreshToken , "User created Successfully");

    }
}
