package com.vof.user.controller.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String username;
    private String role;
    private String email;
    private String name;
    private String lastname;
}
