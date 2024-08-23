package com.arquitetura.senac.repository;

import com.arquitetura.senac.entity.Autor;
import com.arquitetura.senac.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

}
