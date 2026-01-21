package com.api.biblioteca_virtual.mapper;

import com.api.biblioteca_virtual.dto.LivroRequestDTO;
import com.api.biblioteca_virtual.dto.LivroResponseDTO;
import com.api.biblioteca_virtual.model.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public Livro toEntity(LivroRequestDTO dto) {
        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        return livro;
    }

    public LivroResponseDTO toDTO(Livro livro) {
        LivroResponseDTO dto = new LivroResponseDTO();
        dto.setId(livro.getId());
        dto.setTitulo(livro.getTitulo());
        dto.setAutor(livro.getAutor());
        dto.setAnoPublicacao(livro.getAnoPublicacao());
        return dto;
    }

    public void updateEntityFromDTO(LivroRequestDTO dto, Livro livro) {
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
    }
}