package com.api.biblioteca_virtual.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String role;
    private String token;
}

