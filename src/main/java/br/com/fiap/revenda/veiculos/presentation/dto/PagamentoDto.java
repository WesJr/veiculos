package br.com.fiap.revenda.veiculos.presentation.dto;

import br.com.fiap.revenda.veiculos.presentation.enumerado.Status;

import java.math.BigDecimal;

public record PagamentoDto(
        Long id,
        String dataVenda,
        BigDecimal valorRemanescente,
        Status status,
        String codigoPagamento) {
}
