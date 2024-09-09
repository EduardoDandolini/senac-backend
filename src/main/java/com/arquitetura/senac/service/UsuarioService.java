package com.arquitetura.senac.service;

import com.arquitetura.senac.config.TokenService;
import com.arquitetura.senac.dto.LoginDTO;
import com.arquitetura.senac.dto.TokenDTO;
import com.arquitetura.senac.dto.UsuarioRequest;
import com.arquitetura.senac.dto.UsuarioResponse;
import com.arquitetura.senac.entity.Usuario;
import com.arquitetura.senac.exception.ObjectExistsException;
import com.arquitetura.senac.mapper.UsuarioMapper;
import com.arquitetura.senac.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public TokenDTO login(LoginDTO loginDTO) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.senha());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((UsuarioRequest) auth.getPrincipal());

        return new TokenDTO(token);
    }

    @Transactional(rollbackFor = Exception.class)
    public UsuarioResponse save(UsuarioRequest request) {

        if (repository.findByEmail(request.email()) != null) throw new ObjectExistsException("Usuario já cadastrado com esse email");

        Usuario usuario = mapper.toUsuario(request);
        usuario.setSenha(new BCryptPasswordEncoder().encode(request.senha()));
        repository.save(usuario);

        return mapper.toResponse(request);
    }

    @Transactional(readOnly = true)
    public UsuarioResponse  findById(Long id) {
        return mapper.toResponse(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não encontrado")));
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponse> findAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public UsuarioResponse update(Long id, UsuarioRequest request) {
     var usuario = repository.findById(id).orElseThrow(()
             -> new EntityNotFoundException("Não encontrado"));

     repository.save(mapper.toUsuario(request));

     return mapper.toResponse(request);
    }
}
