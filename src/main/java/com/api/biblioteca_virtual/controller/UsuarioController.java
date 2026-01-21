package com.api.biblioteca_virtual.controller;

import com.api.biblioteca_virtual.dto.UsuarioRequestDTO;
import com.api.biblioteca_virtual.dto.UsuarioResponseDTO;
import com.api.biblioteca_virtual.mapper.UsuarioMapper;
import com.api.biblioteca_virtual.model.Usuario;
import com.api.biblioteca_virtual.service.UsuarioService;
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
@RequestMapping("/api/v1/usuarios")
@Tag(
        name = "Usuários",
        description = "Endpoints para gerenciamento de usuários do sistema"
)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna um usuário específico a partir do seu ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @Operation(
            summary = "Cadastrar usuário",
            description = "Cadastra um novo usuário no sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    })
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(
            @Valid @RequestBody UsuarioRequestDTO dto
    ) {
        UsuarioResponseDTO salvo = usuarioService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @Operation(
            summary = "Atualizar usuário",
            description = "Atualiza os dados de um usuário existente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDTO dto
    ) {
        return ResponseEntity.ok(usuarioService.atualizar(id, dto));
    }

    @Operation(
            summary = "Desativar usuário",
            description = "Desativa um usuário sem removê-lo do banco"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário desativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "400", description = "Usuário já está desativado")
    })
    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Void> desativar(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long id
    ) {
        usuarioService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Excluir usuário",
            description = "Remove definitivamente um usuário do sistema"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do usuário", example = "1")
            @PathVariable Long id
    ) {
        usuarioService.desativar(id);
        return ResponseEntity.noContent().build();
    }
}
