package br.com.fiap.revenda.veiculos.domain.service;

import br.com.fiap.revenda.veiculos.infrastructure.proxy.PagamentoProxy;
import br.com.fiap.revenda.veiculos.presentation.dto.PagamentoDto;
import br.com.fiap.revenda.veiculos.presentation.enumerado.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PagamentoServiceTest {

    @Mock
    private PagamentoProxy proxy;

    @InjectMocks
    private PagamentoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRealizarPagamentoComSucesso() {
        PagamentoDto pagamentoDto = new PagamentoDto(1L,"30/10/2025", new BigDecimal(2000), Status.APROVADO, "55ae8b0e-6200-409d-95e4-e2cc0fe9e8b5");

        service.realizarPagamento(pagamentoDto);

        verify(proxy).realizarPagamento(pagamentoDto);
        verifyNoMoreInteractions(proxy);
    }

    @Test
    void devePropagarExcecaoQuandoProxyFalha() {
        // Arrange
        PagamentoDto pagamentoDto = new PagamentoDto(1L,"30/10/2025", new BigDecimal(2000), Status.APROVADO, "55ae8b0e-6200-409d-95e4-e2cc0fe9e8b5");
        doThrow(new RuntimeException("Erro ao processar pagamento"))
                .when(proxy)
                .realizarPagamento(pagamentoDto);

        // Act + Assert
        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.realizarPagamento(pagamentoDto));
        assertEquals("Erro ao processar pagamento", ex.getMessage());

        verify(proxy).realizarPagamento(pagamentoDto);
    }
}
