package br.com.fiap.revenda.veiculos.presentation.dto;

import java.math.BigDecimal;

public record ReservaDto(
        Long id,
        BigDecimal valorReserva,
        String codigoPagamento,
        ClienteDto clienteDto,
        VeiculoDto veiculoDto) {
}
