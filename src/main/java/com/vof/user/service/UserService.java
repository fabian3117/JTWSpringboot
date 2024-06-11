package com.vof.user.service;

import com.vof.user.controller.dto.UserRegisterRequest;
import com.vof.user.model.UserEntity;
import com.vof.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public String registerUser(UserRegisterRequest userDTO) throws Exception {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new Exception("User exists");

        }
        return userRepository.save(
                UserEntity.builder()
                        .id(UUID.randomUUID().toString())
                        .username(userDTO.getUsername())
                        .password(passwordEncoder.encode(userDTO.getPassword()))
                        .role(userDTO.getRole())
                        .build()
        ).getId();
    }
}
