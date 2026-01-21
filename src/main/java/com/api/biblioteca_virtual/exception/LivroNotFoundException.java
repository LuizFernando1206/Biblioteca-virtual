package com.api.biblioteca_virtual.exception;

public class LivroNotFoundException extends RuntimeException {

    public LivroNotFoundException(String mensagem) {
        super(mensagem);
    }

    public LivroNotFoundException(Long id) {
        super("Livro com ID: " + id + " não encontrado");
    }
}
