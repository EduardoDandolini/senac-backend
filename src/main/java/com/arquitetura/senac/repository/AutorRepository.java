package com.arquitetura.senac.repository;

import com.arquitetura.senac.entity.Autor;
import com.arquitetura.senac.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

}
