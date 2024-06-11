package com.vof.user.controller;

import com.vof.user.controller.dto.LoginRequest;
import com.vof.user.controller.dto.LoginResponse;
import com.vof.user.controller.dto.UserRegisterRequest;
import com.vof.user.security.jwt.JwtService;
import com.vof.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthenticationManager authenticationManager;


    private final JwtService jwtTokenProvider;


    private final UserService userService;



    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String,Object> registerUser(@RequestBody UserRegisterRequest userDTO)  {
      try {
          return Map.of("id", userService.registerUser(userDTO));
      }
      catch (Exception e){
          return Map.of("error",e.getMessage());
      }
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Obtener el usuario autenticado
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String jwt = jwtTokenProvider.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(jwt));
    }
}
