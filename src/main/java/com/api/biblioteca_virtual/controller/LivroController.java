package com.api.biblioteca_virtual.controller;

import com.api.biblioteca_virtual.dto.LivroRequestDTO;
import com.api.biblioteca_virtual.dto.LivroResponseDTO;
import com.api.biblioteca_virtual.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/livros")
@Tag(
        name = "Livros",
        description = "Endpoints para gerenciamento de livros da biblioteca"
)
public class LivroController {

    private final LivroService livroService;

    @Operation(
            summary = "Listar livros",
            description = "Retorna todos os livros cadastrados. Pode filtrar por título ou autor."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de livros retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> buscarTodos(
            @Parameter(description = "Filtrar livros pelo título")
            @RequestParam(required = false) String titulo,

            @Parameter(description = "Filtrar livros pelo autor")
            @RequestParam(required = false) String autor
    ) {
        if (titulo != null) {
            return ResponseEntity.ok(livroService.buscarPorTitulo(titulo));
        }
        if (autor != null) {
            return ResponseEntity.ok(livroService.buscarPorAutor(autor));
        }
        return ResponseEntity.ok(livroService.buscarTodos());
    }

    @Operation(
            summary = "Buscar livro por ID",
            description = "Retorna um livro específico a partir do seu ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Livro encontrado"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> buscarPorId(
            @Parameter(description = "ID do livro", example = "1")
            @PathVariable Long id
    ) {
        LivroResponseDTO livro = livroService.buscarPorId(id);
        return ResponseEntity.ok(livro);
    }

    // POST
    @Operation(
            summary = "Cadastrar novo livro",
            description = "Cadastra um novo livro na biblioteca"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Livro cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<LivroResponseDTO> inserir(
            @Valid @RequestBody LivroRequestDTO dto
    ) {
        LivroResponseDTO salvo = livroService.inserir(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // PUT
    @Operation(
            summary = "Atualizar livro",
            description = "Atualiza os dados de um livro existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizar(
            @Parameter(description = "ID do livro", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody LivroRequestDTO dto
    ) {
        LivroResponseDTO atualizado = livroService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    // DELETE por ID
    @Operation(
            summary = "Deletar livro por ID",
            description = "Remove um livro da biblioteca pelo ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Livro removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(
            @Parameter(description = "ID do livro", example = "1")
            @PathVariable Long id
    ) {
        livroService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    // DELETE todos
    @Operation(
            summary = "Deletar todos os livros",
            description = "Remove todos os livros cadastrados na biblioteca"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Todos os livros removidos com sucesso")
    })
    @DeleteMapping
    public ResponseEntity<Void> deletarTodos() {
        livroService.deletarTodos();
        return ResponseEntity.noContent().build();
    }
}
