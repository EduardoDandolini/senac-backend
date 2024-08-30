package com.arquitetura.senac.service;

import com.arquitetura.senac.dto.LivroDto;
import com.arquitetura.senac.entity.Livro;
import com.arquitetura.senac.repository.LivroRepository;
import com.arquitetura.senac.enuns.Status;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository repository;

    public Livro save(LivroDto dto) {
        Livro livro = Livro.builder()
                .nome(dto.nome())
               // .autor(Autor.builder().id(dto.autorId()).build())
                .editora(dto.editora())
                .genero(dto.genero())
                .status(Status.DISPONIVEL)
                .build();
        return repository.save(livro);
    }

    public Livro findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
    }

    public List<Livro> findAll() {
        return repository.findAll();
    }

    public Livro update(Long id, LivroDto dto) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));

        livro.setNome(dto.nome());
      //  livro.setAutor(Autor.builder().id(dto.autorId()).build());
        livro.setEditora(dto.editora());
        livro.setGenero(dto.genero());
        livro.setStatus(Status.valueOf(dto.status().name()));

        return repository.save(livro);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Livro não encontrado");
        }
    }

        public void gerarPlanilha(HttpServletResponse response) throws IOException {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=relatorio.xlsx");

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Relatório estoque de livros");

            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("Nome");
            row.createCell(1).setCellValue("Editora");
            row.createCell(2).setCellValue("Genero");
            row.createCell(3).setCellValue("Status");

            List<Livro> livros = repository.findAll();

            for (int i = 0; i < livros.size(); i++) {
                row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(livros.get(i).getNome());
                row.createCell(1).setCellValue(livros.get(i).getEditora());
                row.createCell(2).setCellValue(livros.get(i).getGenero());
                row.createCell(3).setCellValue(livros.get(i).getStatus().name());
            }

            ServletOutputStream ops = response.getOutputStream();
            workbook.write(ops);
            workbook.close();
            ops.close();

        }
}
