package br.com.fiap.revenda.veiculos.presentation.dto;

public record EnderecoDto(
        Long id,
        String logradouro,
        String numero,
        String cidade,
        String uf,
        String cep) {
}
