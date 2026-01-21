package com.api.biblioteca_virtual.service;

import com.api.biblioteca_virtual.dto.LivroRequestDTO;
import com.api.biblioteca_virtual.dto.LivroResponseDTO;
import com.api.biblioteca_virtual.exception.LivroNotFoundException;
import com.api.biblioteca_virtual.mapper.LivroMapper;
import com.api.biblioteca_virtual.model.Livro;
import com.api.biblioteca_virtual.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;

    public List<LivroResponseDTO> buscarTodos() {
        return livroRepository.findAll().stream()
                .map(livroMapper::toDTO)
                .collect(Collectors.toList());
    }

    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException(id));
        return livroMapper.toDTO(livro);
    }

    public List<LivroResponseDTO> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo).stream()
                .map(livroMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<LivroResponseDTO> buscarPorAutor(String autor) {
        return livroRepository.findByAutorContainingIgnoreCase(autor).stream()
                .map(livroMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public LivroResponseDTO inserir(LivroRequestDTO livroDTO) {
        Livro livro = livroMapper.toEntity(livroDTO);
        Livro livroSalvo = livroRepository.save(livro);
        return livroMapper.toDTO(livroSalvo);
    }

    @Transactional
    public LivroResponseDTO atualizar(Long id, LivroRequestDTO livroDTO) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException(id));

        livroMapper.updateEntityFromDTO(livroDTO, livro);
        Livro livroAtualizado = livroRepository.save(livro);

        return livroMapper.toDTO(livroAtualizado);
    }

    @Transactional
    public void deletarPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException(id));
        livroRepository.delete(livro);
    }

    @Transactional
    public void deletarTodos() {
        livroRepository.deleteAll();
    }
}