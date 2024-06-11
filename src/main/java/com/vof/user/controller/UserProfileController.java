package com.vof.user.controller;

import com.vof.user.controller.dto.UserDTO;
import com.vof.user.exceptions.UserException;
import com.vof.user.model.UserEntity;
import com.vof.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserRepository userRepository;

    @GetMapping
    public UserDTO find(@RequestAttribute("username") String username) throws UserException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(()->new UserException("No existe"));
        UserDTO userResult = UserDTO.builder()
                .email(userEntity.getEmail())
                .build();
        return userResult;
    }

    @PutMapping
    public void update(@RequestAttribute("username") String username,@RequestBody UserDTO userDTO ) throws UserException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(()->new UserException("No existe"));
        userEntity.setEmail(userDTO.getEmail());
        userRepository.save(userEntity);
    }
}
