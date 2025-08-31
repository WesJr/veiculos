package br.com.fiap.revenda.veiculos.presentation.controller;

import br.com.fiap.revenda.veiculos.domain.service.ReservaService;
import br.com.fiap.revenda.veiculos.presentation.dto.ReservaDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @PostMapping("/reservar")
    public String reservar(@RequestBody ReservaDto reservaDto) {
        return service.reservar(reservaDto);
    }
}
