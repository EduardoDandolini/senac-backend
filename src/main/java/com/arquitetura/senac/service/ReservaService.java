package com.arquitetura.senac.service;

import com.arquitetura.senac.dto.ReservaDto;
import com.arquitetura.senac.entity.Emprestimo;
import com.arquitetura.senac.entity.Livro;
import com.arquitetura.senac.entity.Reserva;
import com.arquitetura.senac.enuns.Status;
import com.arquitetura.senac.repository.EmprestimoRepository;
import com.arquitetura.senac.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final LivroRepository livroRepository;
    private final EmprestimoRepository emprestimoRepository;

    public Reserva reservarLivro(ReservaDto dto) {
        Livro livro = livroRepository.findById(dto.livroId())
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));

        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(dto.livroId());
        if (livro.getStatus() == Status.INDISPONIVEL) {
            emprestimo.ifPresent(e -> {
                throw new IllegalStateException("Livro indisponível. Será devolvido dia: " + e.getDtEntregaLivro());
            });
        }
        return null;
    }
}
