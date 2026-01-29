    package com.api.biblioteca_virtual.service;

    import com.api.biblioteca_virtual.dto.LoginRequestDTO;
    import com.api.biblioteca_virtual.dto.LoginResponseDTO;
    import com.api.biblioteca_virtual.dto.UsuarioRequestDTO;
    import com.api.biblioteca_virtual.dto.UsuarioResponseDTO;
    import com.api.biblioteca_virtual.exception.BusinessException;
    import com.api.biblioteca_virtual.exception.UsuarioNotFoundException;
    import com.api.biblioteca_virtual.mapper.UsuarioMapper;
    import com.api.biblioteca_virtual.model.Usuario;
    import com.api.biblioteca_virtual.repository.UsuarioRepository;
    import com.api.biblioteca_virtual.security.JwtService;
    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    @RequiredArgsConstructor
    @Service
    public class UsuarioService {

        private final UsuarioRepository usuarioRepository;
        private final  UsuarioMapper usuarioMapper;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;

        public UsuarioResponseDTO buscarPorId(Long id) {
            return usuarioRepository.findById(id)
                    .map(usuarioMapper::toDTO)
                    .orElseThrow(() -> new UsuarioNotFoundException(id));
        }

        @Transactional
        public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {

            if (usuarioRepository.existsByEmail(dto.getEmail())) {
                throw new BusinessException("Usuário já cadastrado com esse email");
            }

            Usuario usuario = usuarioMapper.toEntity(dto);

            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            Usuario usuarioSalvo = usuarioRepository.save(usuario);
            return usuarioMapper.toDTO(usuarioSalvo);
        }

        @Transactional
        public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new UsuarioNotFoundException(id));

            usuarioMapper.atualizarEntity(dto, usuario);

            Usuario usuarioAtualizado = usuarioRepository.save(usuario);
            return usuarioMapper.toDTO(usuarioAtualizado);
        }

        @Transactional
        public void desativar(Long id) {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new UsuarioNotFoundException(id));

            if(!usuario.isAtivo()) {
                throw new BusinessException("Usuário já está desativado");
            }

            usuario.setAtivo(false);
            usuarioRepository.save(usuario);
        }


        public LoginResponseDTO login(LoginRequestDTO dto) {

           Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                   .orElseThrow(() -> new BusinessException("Email ou senha inválidos"));

            if(!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
                throw new BusinessException("Email ou senha inválidos");
            }
            String token = jwtService.generateToken(usuario.getEmail());

            return new LoginResponseDTO(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    usuario.getRole().name(),
                    token
            );
        }

    }
