package br.com.fiap.revenda.veiculos.domain.service;

import br.com.fiap.revenda.veiculos.infrastructure.repository.VeiculoRepository;
import br.com.fiap.revenda.veiculos.presentation.assembler.VeiculoAssembler;
import br.com.fiap.revenda.veiculos.presentation.dto.VeiculoDto;
import org.springframework.stereotype.Service;

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
}
