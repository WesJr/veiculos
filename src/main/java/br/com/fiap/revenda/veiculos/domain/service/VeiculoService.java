package br.com.fiap.revenda.veiculos.domain.service;

import br.com.fiap.revenda.veiculos.infrastructure.repository.VeiculoRepository;
import br.com.fiap.revenda.veiculos.presentation.Exception.VeiculoException;
import br.com.fiap.revenda.veiculos.presentation.assembler.VeiculoAssembler;
import br.com.fiap.revenda.veiculos.presentation.dto.VeiculoDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;
    private final VeiculoAssembler assembler;

    public VeiculoService(VeiculoRepository repository, VeiculoAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public void inserirVeiculo(VeiculoDto veiculoDto) {
        repository.save(assembler.dtoParaModelo(veiculoDto));
    }

    public void atualizarVeiculo(VeiculoDto veiculoDto) {
        repository.save(assembler.dtoParaModelo(veiculoDto));
    }

    public List<VeiculoDto> getListaVeiculosAVendaPorPrecoCresceste() {

        return repository.findByVendidoFalseOrderByPrecoAsc()
                .stream()
                .map(assembler::modeloParaDto)
                .toList();
    }

    public List<VeiculoDto> getListaVeiculosAVendidosPorPrecoCrescente() {
        return repository.findByVendidoTrueOrderByPrecoAsc()
                .stream()
                .map(assembler::modeloParaDto)
                .toList();
    }

    public VeiculoDto consultarPorId(Long id) throws VeiculoException {
        return repository.findById(id)
                .map(assembler::modeloParaDto)
                .orElseThrow(() -> new VeiculoException("Veículo não encontrado"));
    }
}
