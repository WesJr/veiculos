package br.com.fiap.revenda.veiculos.presentation.controller;

import br.com.fiap.revenda.veiculos.domain.service.ClienteService;
import br.com.fiap.revenda.veiculos.presentation.Exception.ClienteException;
import br.com.fiap.revenda.veiculos.presentation.dto.ClienteDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/listar")
    public List<ClienteDto> listarTodosOsClientes(){
        return service.listarTodosOsClientes();
    }

    @GetMapping("/consultar/{id}")
    public ClienteDto consultarCliente(@PathVariable(name = "id") Long id) throws ClienteException {
        return service.consultarPorId(id);
    }
}
