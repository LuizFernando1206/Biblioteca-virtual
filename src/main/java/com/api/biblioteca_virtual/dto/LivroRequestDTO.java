package com.api.biblioteca_virtual.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroRequestDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String autor;

    @NotNull
    private Integer anoPublicacao;
}
