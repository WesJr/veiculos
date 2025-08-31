package br.com.fiap.revenda.veiculos.presentation.controller;

import br.com.fiap.revenda.veiculos.domain.service.VeiculoService;
import br.com.fiap.revenda.veiculos.presentation.dto.VeiculoDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/listar/nao-vendidos")
    public List<VeiculoDto> getListaVeiculosAVendaPorPrecoCrescente(){
        return service.getListaVeiculosAVendaPorPreco();
    }
}
