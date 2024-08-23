package com.arquitetura.senac.repository;

import com.arquitetura.senac.entity.Autor;
import com.arquitetura.senac.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
