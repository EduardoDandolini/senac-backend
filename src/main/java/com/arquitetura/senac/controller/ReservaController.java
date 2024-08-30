package com.arquitetura.senac.controller;

import com.arquitetura.senac.dto.ReservaDto;
import com.arquitetura.senac.entity.Reserva;
import com.arquitetura.senac.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserva")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping("/reservar")
    public ResponseEntity<String> reservarLivro(@RequestBody ReservaDto dto) {
        try {
            Reserva reserva = reservaService.reservarLivro(dto);
            if (reserva == null) {
                return ResponseEntity.badRequest().body("Livro indisponível.");
            }
            return ResponseEntity.ok("Consulte a data de devolução.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
