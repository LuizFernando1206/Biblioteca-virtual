package com.api.biblioteca_virtual.dto;



import com.api.biblioteca_virtual.model.Role;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private Role role;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private Integer totalLivros;
}