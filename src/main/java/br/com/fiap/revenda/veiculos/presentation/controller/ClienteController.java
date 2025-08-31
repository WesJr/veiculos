package br.com.fiap.revenda.veiculos.presentation.controller;

import br.com.fiap.revenda.veiculos.domain.service.ClienteService;
import br.com.fiap.revenda.veiculos.presentation.dto.ClienteDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping("/inserir")
    public void inserirCliente(@RequestBody ClienteDto clienteDto) {
        service.inserirCliente(clienteDto);
    }
}
