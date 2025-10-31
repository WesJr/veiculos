package br.com.fiap.revenda.veiculos.domain.service;

import br.com.fiap.revenda.veiculos.domain.model.Endereco;
import br.com.fiap.revenda.veiculos.infrastructure.repository.EnderecoRepository;
import br.com.fiap.revenda.veiculos.presentation.assembler.EnderecoAssembler;
import br.com.fiap.revenda.veiculos.presentation.dto.EnderecoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnderecoServiceTest {

    @Mock
    private EnderecoRepository repository;

    @Mock
    private EnderecoAssembler assembler;

    @InjectMocks
    private EnderecoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ Caso 1: Inserção bem-sucedida
    @Test
    void deveInserirEnderecoComSucesso() {
        // Arrange
        EnderecoDto enderecoDto = new EnderecoDto(1L,"Rua Teste", "123", "São Paulo", "SP", "01000-000");
        Endereco endereco = new Endereco();

        when(assembler.dtoParaModelo(enderecoDto)).thenReturn(endereco);
        when(repository.save(endereco)).thenReturn(endereco);

        // Act
        Endereco resultado = service.insereEndereco(enderecoDto);

        // Assert
        assertNotNull(resultado);
        verify(assembler).dtoParaModelo(enderecoDto);
        verify(repository).save(endereco);
    }

    @Test
    void devePropagarExcecaoQuandoRepositoryFalha() {
        // Arrange
        EnderecoDto enderecoDto = new EnderecoDto(1L,"Rua Teste", "123", "São Paulo", "SP", "01000-000");
        Endereco endereco = new Endereco();

        when(assembler.dtoParaModelo(enderecoDto)).thenReturn(endereco);
        when(repository.save(endereco)).thenThrow(new RuntimeException("Erro ao salvar endereço"));

        // Act + Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.insereEndereco(enderecoDto));
        assertEquals("Erro ao salvar endereço", ex.getMessage());

        verify(assembler).dtoParaModelo(enderecoDto);
        verify(repository).save(endereco);
    }
}
