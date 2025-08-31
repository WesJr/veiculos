package br.com.fiap.revenda.veiculos.presentation.dto;

public record ReservaDto(
        Long id,
        String codigoReserva,
        ClienteDto clienteDto,
        VeiculoDto veiculoDto) {
}
