package com.api.biblioteca_virtual.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDTO {
    private Long id;
    private String titulo;
    private String autor;
    private Integer anoPublicacao;
}
