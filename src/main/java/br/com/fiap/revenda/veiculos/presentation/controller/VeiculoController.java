package br.com.fiap.revenda.veiculos.presentation.controller;

import br.com.fiap.revenda.veiculos.domain.service.VeiculoService;
import br.com.fiap.revenda.veiculos.presentation.dto.VeiculoDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @PostMapping("/inserir")
    public void inserirVeiculo(@RequestBody VeiculoDto veiculoDto){
        service.inserirVeiculo(veiculoDto);
    }
}
