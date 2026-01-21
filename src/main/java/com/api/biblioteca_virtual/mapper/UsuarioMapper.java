package com.api.biblioteca_virtual.mapper;

import com.api.biblioteca_virtual.dto.UsuarioRequestDTO;
import com.api.biblioteca_virtual.dto.UsuarioResponseDTO;
import com.api.biblioteca_virtual.model.Role;
import com.api.biblioteca_virtual.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setRole(Role.USER);

        return usuario;
    }

    public UsuarioResponseDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setRole(usuario.getRole());
        dto.setDataCriacao(usuario.getDataCriacao());
        dto.setDataAtualizacao(usuario.getDataAtualizacao());

        return dto;
    }

    public void atualizarEntity(UsuarioRequestDTO dto, Usuario usuario) {
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
    }

}