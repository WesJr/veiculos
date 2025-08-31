package br.com.fiap.revenda.veiculos.presentation.dto;

public record ClienteDto(
        Long id,
        String nome,
        String documento,
        String nascimento,
        String sexo,
        ContatoDto contato,
        EnderecoDto endereco
        ){

}