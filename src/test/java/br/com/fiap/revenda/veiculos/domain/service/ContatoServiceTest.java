package br.com.fiap.revenda.veiculos.domain.service;

import br.com.fiap.revenda.veiculos.domain.model.Contato;
import br.com.fiap.revenda.veiculos.infrastructure.repository.ContatoRepository;
import br.com.fiap.revenda.veiculos.presentation.assembler.ContatoAssembler;
import br.com.fiap.revenda.veiculos.presentation.dto.ContatoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContatoServiceTest {

    @Mock
    private ContatoRepository repository;

    @Mock
    private ContatoAssembler assembler;

    @InjectMocks
    private ContatoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveInserirContatoComSucesso() {
        // Arrange (cenário)
        ContatoDto contatoDto = new ContatoDto(1L,"11365878954","email@teste.com", "11999999999");
        Contato contato = new Contato();

        when(assembler.dtoParaModelo(contatoDto)).thenReturn(contato);
        when(repository.save(contato)).thenReturn(contato);

        // Act (execução)
        Contato resultado = service.inserirContato(contatoDto);

        // Assert (validação)
        assertNotNull(resultado);
        verify(assembler).dtoParaModelo(contatoDto);
        verify(repository).save(contato);
    }

    @Test
    void devePropagarExcecaoQuandoRepositoryFalha() {
        // Arrange
        ContatoDto contatoDto = new ContatoDto(1L,"11365878954","email@teste.com", "11999999999");
        Contato contato = new Contato();

        when(assembler.dtoParaModelo(contatoDto)).thenReturn(contato);
        when(repository.save(contato)).thenThrow(new RuntimeException("Erro ao salvar no banco"));

        // Act & Assert
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> service.inserirContato(contatoDto)
        );

        assertEquals("Erro ao salvar no banco", ex.getMessage());
        verify(assembler).dtoParaModelo(contatoDto);
        verify(repository).save(contato);
    }
}
