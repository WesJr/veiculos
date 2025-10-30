package br.com.fiap.revenda.veiculos.domain.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


import br.com.fiap.revenda.veiculos.domain.model.Cliente;
import br.com.fiap.revenda.veiculos.domain.model.Contato;
import br.com.fiap.revenda.veiculos.domain.model.Endereco;
import br.com.fiap.revenda.veiculos.infrastructure.repository.ClienteRepository;
import br.com.fiap.revenda.veiculos.presentation.Exception.ClienteException;
import br.com.fiap.revenda.veiculos.presentation.assembler.ClienteAssembler;
import br.com.fiap.revenda.veiculos.presentation.assembler.ContatoAssembler;
import br.com.fiap.revenda.veiculos.presentation.assembler.EnderecoAssembler;
import br.com.fiap.revenda.veiculos.presentation.dto.ClienteDto;
import br.com.fiap.revenda.veiculos.presentation.dto.ContatoDto;
import br.com.fiap.revenda.veiculos.presentation.dto.EnderecoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

class ClienteServiceTest {

    @Mock
    private ClienteAssembler clienteAssembler;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EnderecoService enderecoService;

    @Mock
    private EnderecoAssembler enderecoAssembler;

    @Mock
    private ContatoService contatoService;

    @Mock
    private ContatoAssembler contatoAssembler;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // 🔹 Teste do método inserirCliente()
    @Test
    void deveInserirClienteComSucesso() {
        // Arrange
        Endereco endereco = new Endereco();
        EnderecoDto enderecoDto = new EnderecoDto(10L, "Rua teste", "10", "São Paulo", "SP", "12345879");
        Contato contato = new Contato();
        ContatoDto contatoDto = new ContatoDto(1L, "1112548556", "11258745896", "teste@teste.com.br");
        ClienteDto clienteDto = new ClienteDto(1L, "João", "4578565845", "10/07/1950", "M", contatoDto, enderecoDto);

        when(enderecoService.insereEndereco(clienteDto.endereco())).thenReturn(endereco);
        when(enderecoAssembler.modeloParaDto(endereco)).thenReturn(enderecoDto);
        when(contatoService.inserirContato(clienteDto.contato())).thenReturn(contato);
        when(contatoAssembler.modeloParaDto(contato)).thenReturn(contatoDto);

        Cliente clienteModel = new Cliente();
        when(clienteAssembler.dtoParaModelo(clienteDto, enderecoDto, contatoDto)).thenReturn(clienteModel);

        // Act
        clienteService.inserirCliente(clienteDto);

        // Assert
        verify(enderecoService).insereEndereco(clienteDto.endereco());
        verify(contatoService).inserirContato(clienteDto.contato());
        verify(clienteRepository).save(clienteModel);
    }

    // 🔹 Teste do método listarTodosOsClientes()
    @Test
    void deveListarTodosOsClientes() {
        // Arrange
        Cliente cliente = new Cliente();
        ContatoDto contatoDto = new ContatoDto(1L, "1112548556", "11258745896", "teste@teste.com.br");
        EnderecoDto enderecoDto = new EnderecoDto(10L, "Rua teste", "10", "São Paulo", "SP", "12345879");
        ClienteDto clienteDto = new ClienteDto(1L, "João", "4578565845", "10/07/1950", "M", contatoDto, enderecoDto);

        when(clienteRepository.findAll()).thenReturn(List.of(cliente));
        when(clienteAssembler.modeloParaDto(cliente)).thenReturn(clienteDto);

        // Act
        List<ClienteDto> resultado = clienteService.listarTodosOsClientes();

        // Assert
        assertEquals(1, resultado.size());
        assertEquals("João", resultado.get(0).nome());
        verify(clienteRepository).findAll();
    }

    // 🔹 Teste do método consultarPorId() - sucesso
    @Test
    void deveConsultarClientePorId() throws ClienteException {
        // Arrange
        Long id = 1L;
        Cliente cliente = new Cliente();
        ContatoDto contatoDto = new ContatoDto(1L, "1112548556", "11258745896", "teste@teste.com.br");
        EnderecoDto enderecoDto = new EnderecoDto(10L, "Rua teste", "10", "São Paulo", "SP", "12345879");
        ClienteDto clienteDto = new ClienteDto(1L, "João", "4578565845", "10/07/1950", "M", contatoDto, enderecoDto);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        when(clienteAssembler.modeloParaDto(cliente)).thenReturn(clienteDto);

        // Act
        ClienteDto resultado = clienteService.consultarPorId(id);

        // Assert
        assertEquals("João", resultado.nome());
        verify(clienteRepository).findById(id);
    }

    // 🔹 Teste do método consultarPorId() - cliente não encontrado
    @Test
    void deveLancarExcecaoQuandoClienteNaoExistir() {
        // Arrange
        Long id = 99L;
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        // Act + Assert
        ClienteException ex = assertThrows(ClienteException.class, () -> clienteService.consultarPorId(id));
        assertEquals("Cliente não cadastrado", ex.getMessage());
        verify(clienteRepository).findById(id);
    }
}
