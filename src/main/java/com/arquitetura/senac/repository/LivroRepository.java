package com.arquitetura.senac.repository;

import com.arquitetura.senac.entity.Livro;
import com.arquitetura.senac.enuns.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
