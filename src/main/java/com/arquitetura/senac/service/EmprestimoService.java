package com.arquitetura.senac.service;

import com.arquitetura.senac.exception.LivrariaException;
import com.arquitetura.senac.dto.EmprestimoDto;
import com.arquitetura.senac.entity.Emprestimo;
import com.arquitetura.senac.entity.Livro;
import com.arquitetura.senac.enuns.Status;
import com.arquitetura.senac.mapper.UsuarioMapper;
import com.arquitetura.senac.repository.EmprestimoRepository;
import com.arquitetura.senac.repository.LivroRepository;
import com.arquitetura.senac.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import static com.arquitetura.senac.enuns.Status.INDISPONIVEL;

@Service
@RequiredArgsConstructor
public class EmprestimoService {

    private final EmprestimoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LivroService livroService;

    private final LivroRepository livroRepository;

    private final UsuarioMapper mapper;

    public Emprestimo emprestarLivro(EmprestimoDto dto) {
        Livro livro = livroService.findById(dto.idLivro());
        if(isLivroEmprestado(livro)) {
            throw new LivrariaException("O livro já está no registro de outra pessoa");
        }

        Emprestimo emprestimo = Emprestimo.builder()
                .dtEmprestimo(dto.dtEmprestimo())
                .dtEntregaLivro(dto.dtEntregaLivro())
                .usuario(usuarioRepository.findById(dto.idUsuario()).orElseThrow(() -> new EntityNotFoundException("Não encontrado")))
                .livro(livro)
                .valorEmprestimo(dto.valorEmprestimo())
                .taxaDeJuro(0.05)
                .build();

        return repository.save(emprestimo);
    }

    public Emprestimo findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Emprestimo não encontrado"));
    }

    public List<Emprestimo> findAll() {
        List<Emprestimo> emprestimos = repository.findAll();
        return emprestimos.stream()
                .map(this::calcularValorEmprestimo)
                .collect(Collectors.toList());
    }

    public Emprestimo calcularValorEmprestimo(Emprestimo emprestimo) {
        LocalDate dtAtual = LocalDate.now();
        LocalDate dtEntregaLivro = emprestimo.getDtEntregaLivro();
        long diasAtrasados = ChronoUnit.DAYS.between(dtEntregaLivro, dtAtual);

        if (diasAtrasados > 0) {
            double taxaJurosTotal = emprestimo.getTaxaDeJuro() + (diasAtrasados * 0.0025);
            double valorFinal = emprestimo.getValorEmprestimo() * (1 + taxaJurosTotal);
            emprestimo.setTaxaDeJuro(taxaJurosTotal);
            emprestimo.setValorEmprestimo(valorFinal);
        }
        return emprestimo;
    }

    public Emprestimo update(Long id, EmprestimoDto dto) {
        Emprestimo emprestimo = findById(id);

        emprestimo = Emprestimo.builder()
                .dtEmprestimo(dto.dtEmprestimo())
                .dtEmprestimo(dto.dtEmprestimo())
                .dtEntregaLivro(dto.dtEntregaLivro())
                .usuario(usuarioRepository.findById(dto.idUsuario()).orElseThrow(() -> new EntityNotFoundException("Não encontrado")))
                .livro(livroService.findById(dto.idLivro()))
                .valorEmprestimo(dto.valorEmprestimo())
                .build();

        return repository.save(emprestimo);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Emprestimo não encontrado");
        }
    }

    public boolean isLivroEmprestado(Livro livro) {
        return Status.valueOf(livro.getStatus().name()).equals(INDISPONIVEL);

    }
}