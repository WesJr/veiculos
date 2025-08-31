package br.com.fiap.revenda.veiculos.domain.service;

import br.com.fiap.revenda.veiculos.domain.model.Contato;
import br.com.fiap.revenda.veiculos.domain.model.Endereco;
import br.com.fiap.revenda.veiculos.infrastructure.repository.ClienteRepository;
import br.com.fiap.revenda.veiculos.presentation.assembler.ClienteAssembler;
import br.com.fiap.revenda.veiculos.presentation.assembler.ContatoAssembler;
import br.com.fiap.revenda.veiculos.presentation.assembler.EnderecoAssembler;
import br.com.fiap.revenda.veiculos.presentation.dto.ClienteDto;
import br.com.fiap.revenda.veiculos.presentation.dto.ContatoDto;
import br.com.fiap.revenda.veiculos.presentation.dto.EnderecoDto;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteAssembler assembler;
    private final ClienteRepository repository;
    private final EnderecoService enderecoService;
    private final EnderecoAssembler enderecoAssembler;
    private final ContatoService contatoService;
    private final ContatoAssembler contatoAssembler;

    public ClienteService(ClienteAssembler assembler, ClienteRepository repository, EnderecoService enderecoService, EnderecoAssembler enderecoAssembler, ContatoService contatoService, ContatoAssembler contatoAssembler) {
        this.assembler = assembler;
        this.repository = repository;
        this.enderecoService = enderecoService;
        this.enderecoAssembler = enderecoAssembler;
        this.contatoService = contatoService;
        this.contatoAssembler = contatoAssembler;
    }

    public void inserirCliente(ClienteDto clienteDto) {
        Endereco endereco = enderecoService.insereEndereco(clienteDto.endereco());
        EnderecoDto enderecoDto = enderecoAssembler.modeloParaDto(endereco);

        Contato contato = contatoService.inserirContato(clienteDto.contato());
        ContatoDto contatoDto = contatoAssembler.modeloParaDto(contato);

        repository.save(assembler.dtoParaModelo(clienteDto, enderecoDto, contatoDto));
    }
}
