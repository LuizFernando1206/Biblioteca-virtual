package com.api.biblioteca_virtual.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Schema(description = "Entidade que representa um livro da biblioteca")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Identificador único do livro",
            example = "1",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Column(nullable = false, length = 255)
    @Schema(
            description = "Título do livro",
            example = "Dom Casmurro",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String titulo;

    @Column(nullable = false, length = 255)
    @Schema(
            description = "Autor do livro",
            example = "Machado de Assis",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String autor;

    @Column(nullable = false)
    @Schema(
            description = "Ano de publicação do livro",
            example = "1899",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Integer anoPublicacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @Schema(
            description = "Usuário proprietário do livro",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Usuario usuario;

}