package com.arquitetura.senac.service;

import com.arquitetura.senac.dto.UsuarioRequest;
import com.arquitetura.senac.dto.UsuarioResponse;
import com.arquitetura.senac.entity.Usuario;
import com.arquitetura.senac.mapper.UsuarioMapper;
import com.arquitetura.senac.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    @Transactional(rollbackFor = Exception.class)
    public UsuarioResponse save(UsuarioRequest request) {

        Usuario usuario = mapper.toUsuario(request);
        repository.save(usuario);

        return mapper.toResponse(request);
    }

    @Transactional(readOnly = true)
    public UsuarioResponse findById(Long id) {
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
