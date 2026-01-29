    package com.api.biblioteca_virtual.controller;

    import com.api.biblioteca_virtual.dto.LoginRequestDTO;
    import com.api.biblioteca_virtual.dto.LoginResponseDTO;
    import com.api.biblioteca_virtual.service.UsuarioService;
    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api/v1/auth")
    @RequiredArgsConstructor
    public class AuthController {

        private final UsuarioService usuarioService;

        @PostMapping("/login")
        public ResponseEntity<LoginResponseDTO> login(
                @Valid @RequestBody LoginRequestDTO dto
                ) {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.login(dto));
        }
    }
