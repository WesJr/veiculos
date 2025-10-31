package br.com.fiap.revenda.veiculos.domain.service;

import br.com.fiap.revenda.veiculos.domain.model.Reserva;
import br.com.fiap.revenda.veiculos.domain.model.Veiculo;
import br.com.fiap.revenda.veiculos.infrastructure.repository.ReservaRepository;
import br.com.fiap.revenda.veiculos.presentation.Exception.ReservaException;
import br.com.fiap.revenda.veiculos.presentation.assembler.ReservaAssembler;
import br.com.fiap.revenda.veiculos.presentation.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservaServiceTest {

    @Mock
    private ReservaRepository repository;

    @Mock
    private ReservaAssembler assembler;

    @InjectMocks
    private ReservaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveReservarComSucesso() {


        EnderecoDto enderecoDto = new EnderecoDto(10L, "Rua teste", "10", "São Paulo", "SP", "12345879");
        ContatoDto contatoDto = new ContatoDto(1L, "1112548556", "11258745896", "teste@teste.com.br");
        ClienteDto clienteDto = new ClienteDto(1L, "João", "4578565845", "10/07/1950", "M", contatoDto, enderecoDto);
        Veiculo veiculo = new Veiculo();
        veiculo.setId(1L);
        VeiculoDto veiculoDto = new VeiculoDto(1L, "Chevrolet", "Tracker", 2025, "preta", new BigDecimal(15000), false, true);

        ReservaDto reservaDto = new ReservaDto(1L, new BigDecimal(5000),"55ae8b0e-6200-409d-95e4-e2cc0fe9e8b5",  clienteDto, veiculoDto);
        Reserva reserva = new Reserva();
        reserva.setVeiculo(veiculo);

        Reserva reservaSalva = new Reserva();
        reservaSalva.setCodigoPagamento("codigo123");

        when(assembler.dtoParaModelo(reservaDto)).thenReturn(reserva);
        when(repository.findByVeiculo(veiculo)).thenReturn(List.of());
        when(repository.save(reserva)).thenReturn(reservaSalva);

        // Act
        String codigoPagamento = service.reservar(reservaDto);

        // Assert
        assertEquals("codigo123", codigoPagamento);
        verify(repository).findByVeiculo(veiculo);
        verify(repository).save(reserva);
    }

    // ⚠️ Caso 2: Tentativa de reservar veículo já reservado
    @Test
    void deveLancarExcecaoQuandoVeiculoJaReservado() {
        EnderecoDto enderecoDto = new EnderecoDto(10L, "Rua teste", "10", "São Paulo", "SP", "12345879");
        ContatoDto contatoDto = new ContatoDto(1L, "1112548556", "11258745896", "teste@teste.com.br");
        ClienteDto clienteDto = new ClienteDto(1L, "João", "4578565845", "10/07/1950", "M", contatoDto, enderecoDto);
        VeiculoDto veiculoDto = new VeiculoDto(1L, "Chevrolet", "Tracker", 2025, "preta", new BigDecimal(15000), false, true);
        Veiculo veiculo = new Veiculo();
        veiculo.setId(1L);


        ReservaDto reservaDto = new ReservaDto(1L, new BigDecimal(5000),"55ae8b0e-6200-409d-95e4-e2cc0fe9e8b5",  clienteDto, veiculoDto);
        Reserva reservaExistente = new Reserva();
        reservaExistente.setVeiculo(veiculo);

        when(assembler.dtoParaModelo(reservaDto)).thenReturn(reservaExistente);
        when(repository.findByVeiculo(veiculo)).thenReturn(List.of(reservaExistente));

        // Act + Assert
        ReservaException ex = assertThrows(ReservaException.class, () -> service.reservar(reservaDto));
        assertEquals("Veiculo já foi reservado", ex.getMessage());

        verify(repository).findByVeiculo(veiculo);
        verify(repository, never()).save(any());
    }

    // ✅ Caso 3: Buscar reserva por ID com sucesso
    @Test
    void deveBuscarReservaPorIdComSucesso() {
        // Arrange
        Long id = 1L;

        EnderecoDto enderecoDto = new EnderecoDto(10L, "Rua teste", "10", "São Paulo", "SP", "12345879");
        ContatoDto contatoDto = new ContatoDto(1L, "1112548556", "11258745896", "teste@teste.com.br");
        ClienteDto clienteDto = new ClienteDto(1L, "João", "4578565845", "10/07/1950", "M", contatoDto, enderecoDto);
        VeiculoDto veiculoDto = new VeiculoDto(1L, "Chevrolet", "Tracker", 2025, "preta", new BigDecimal(15000), false, true);
        Reserva reserva = new Reserva();
        ReservaDto reservaDto = new ReservaDto(1L, new BigDecimal(5000),"55ae8b0e-6200-409d-95e4-e2cc0fe9e8b5",  clienteDto, veiculoDto);

        when(repository.findById(id)).thenReturn(Optional.of(reserva));
        when(assembler.modeloParaDto(reserva)).thenReturn(reservaDto);

        // Act
        ReservaDto resultado = service.buscarReservaPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals("55ae8b0e-6200-409d-95e4-e2cc0fe9e8b5", resultado.codigoPagamento());
        verify(repository).findById(id);
        verify(assembler).modeloParaDto(reserva);
    }

    // ⚠️ Caso 4: Buscar reserva por ID inexistente
    @Test
    void deveLancarExcecaoAoBuscarReservaInexistente() {
        // Arrange
        Long id = 99L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act + Assert
        ReservaException ex = assertThrows(ReservaException.class, () -> service.buscarReservaPorId(id));
        assertEquals("Não foi possível encontrar a reserva", ex.getMessage());
        verify(repository).findById(id);
    }

    // ✅ Caso 5: Buscar reserva por código existente
    @Test
    void deveBuscarReservaPorCodigoComSucesso() {
        // Arrange
        String codigo = "ABC123";
        Reserva reserva = new Reserva();
        EnderecoDto enderecoDto = new EnderecoDto(10L, "Rua teste", "10", "São Paulo", "SP", "12345879");
        ContatoDto contatoDto = new ContatoDto(1L, "1112548556", "11258745896", "teste@teste.com.br");
        ClienteDto clienteDto = new ClienteDto(1L, "João", "4578565845", "10/07/1950", "M", contatoDto, enderecoDto);
        VeiculoDto veiculoDto = new VeiculoDto(1L, "Chevrolet", "Tracker", 2025, "preta", new BigDecimal(15000), false, true);
        ReservaDto reservaDto = new ReservaDto(1L, new BigDecimal(5000),"55ae8b0e-6200-409d-95e4-e2cc0fe9e8b5",  clienteDto, veiculoDto);

        when(repository.findByCodigoPagamento(codigo)).thenReturn(Optional.of(reserva));
        when(assembler.modeloParaDto(reserva)).thenReturn(reservaDto);

        // Act
        ReservaDto resultado = service.buscarReservaPorCodigo(codigo);

        // Assert
        assertNotNull(resultado);
        assertEquals("55ae8b0e-6200-409d-95e4-e2cc0fe9e8b5", resultado.codigoPagamento());
        verify(repository, times(2)).findByCodigoPagamento(codigo); // é chamado duas vezes no método original
        verify(assembler).modeloParaDto(reserva);
    }

    // ⚠️ Caso 6: Buscar reserva por código inexistente
    @Test
    void deveRetornarNullQuandoReservaNaoEncontradaPorCodigo() {
        // Arrange
        String codigo = "SEM_RESERVA";
        when(repository.findByCodigoPagamento(codigo)).thenReturn(Optional.empty());

        // Act
        ReservaDto resultado = service.buscarReservaPorCodigo(codigo);

        // Assert
        assertNull(resultado);
        verify(repository).findByCodigoPagamento(codigo);
        verify(assembler, never()).modeloParaDto(any());
    }
}
