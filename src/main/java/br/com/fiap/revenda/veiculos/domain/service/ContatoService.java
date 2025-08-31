package br.com.fiap.revenda.veiculos.domain.service;

import br.com.fiap.revenda.veiculos.domain.model.Contato;
import br.com.fiap.revenda.veiculos.infrastructure.repository.ContatoRepository;
import br.com.fiap.revenda.veiculos.presentation.assembler.ContatoAssembler;
import br.com.fiap.revenda.veiculos.presentation.dto.ContatoDto;
import org.springframework.stereotype.Service;

@Service
public class ContatoService {

    private final ContatoRepository repository;
    private final ContatoAssembler assembler;

    public ContatoService(ContatoRepository repository, ContatoAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public Contato inserirContato(ContatoDto contatoDto) {

        return repository.save(assembler.dtoParaModelo(contatoDto));
    }
}
