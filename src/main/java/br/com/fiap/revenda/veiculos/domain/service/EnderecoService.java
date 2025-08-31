package br.com.fiap.revenda.veiculos.domain.service;

import br.com.fiap.revenda.veiculos.domain.model.Endereco;
import br.com.fiap.revenda.veiculos.infrastructure.repository.EnderecoRepository;
import br.com.fiap.revenda.veiculos.presentation.assembler.EnderecoAssembler;
import br.com.fiap.revenda.veiculos.presentation.dto.EnderecoDto;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    private final EnderecoRepository repository;
    private final EnderecoAssembler assembler;


    public EnderecoService(EnderecoRepository repository, EnderecoAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public Endereco insereEndereco(EnderecoDto enderecoDto) {
        return repository.save(assembler.dtoParaModelo(enderecoDto));

    }
}
