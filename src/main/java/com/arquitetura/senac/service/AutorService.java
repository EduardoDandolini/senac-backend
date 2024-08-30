package com.arquitetura.senac.service;

import com.arquitetura.senac.dto.AutorDto;
import com.arquitetura.senac.entity.Autor;
import com.arquitetura.senac.repository.AutorRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository repository;

    public Autor save(AutorDto dto){
        Autor autor = Autor.builder()
                .nome(dto.nome())
                .dataNascimento(dto.dataNascimento())
                .email(dto.email())
                .build();
        return repository.save(autor);
    }

    public Autor findById(long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado"));
    }

    public List<Autor> findAll(){
        return repository.findAll();
    }

    public Autor Update(Long id, AutorDto dto){
        Autor autor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado"));

        autor.setNome(dto.nome());
        autor.setDataNascimento(dto.dataNascimento());
        autor.setEmail(dto.email());

        return repository.save(autor);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Autor não encontrado");
        }
    }
}
