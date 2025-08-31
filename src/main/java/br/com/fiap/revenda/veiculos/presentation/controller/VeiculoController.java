package br.com.fiap.revenda.veiculos.presentation.controller;

import br.com.fiap.revenda.veiculos.domain.service.VeiculoService;
import br.com.fiap.revenda.veiculos.presentation.Exception.VeiculoException;
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

    @GetMapping("/consultar/{id}")
    public VeiculoDto consultarPorId(@PathVariable("id")Long id) throws VeiculoException {
        return service.consultarPorId(id);
    }

    @PutMapping("/alterar")
    public void atualizarVeiculo(@RequestBody VeiculoDto veiculoDto){
        service.atualizarVeiculo(veiculoDto);
    }

    @GetMapping("/listar/nao-vendidos")
    public List<VeiculoDto> getListaVeiculosAVendaPorPrecoCrescente(){
        return service.getListaVeiculosAVendaPorPrecoCresceste();
    }

    @GetMapping("/listar/vendidos")
    public List<VeiculoDto> getListaVeiculosVendidosPorPrecoCrescente(){
        return service.getListaVeiculosAVendidosPorPrecoCrescente();
    }
}
