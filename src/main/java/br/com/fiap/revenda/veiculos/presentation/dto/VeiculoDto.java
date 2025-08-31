package br.com.fiap.revenda.veiculos.presentation.dto;

import java.math.BigDecimal;

public record VeiculoDto(
        Long id,
        String marca,
        String modelo,
        Integer ano,
        String cor,
        BigDecimal preco,
        Boolean vendido,
        Boolean emEstoque) {
}
