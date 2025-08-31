package br.com.fiap.revenda.veiculos.presentation.assembler;

import br.com.fiap.revenda.veiculos.domain.model.Cliente;
import br.com.fiap.revenda.veiculos.domain.model.Reserva;
import br.com.fiap.revenda.veiculos.domain.model.Veiculo;
import br.com.fiap.revenda.veiculos.presentation.dto.ReservaDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReservaAssembler {

    private final ClienteAssembler clienteAssembler;
    private final VeiculoAssembler veiculoAssembler;

    public ReservaAssembler(ClienteAssembler clienteAssembler, VeiculoAssembler veiculoAssembler) {
        this.clienteAssembler = clienteAssembler;
        this.veiculoAssembler = veiculoAssembler;
    }

    public Reserva dtoParaModelo(ReservaDto reservaDto) {
        Reserva reserva = new Reserva();
        Cliente cliente = clienteAssembler.dtoParaModelo(reservaDto.clienteDto(), reservaDto.clienteDto().endereco(), reservaDto.clienteDto().contato());
        Veiculo veiculo = veiculoAssembler.dtoParaModelo(reservaDto.veiculoDto());

        reserva.setId(reservaDto.id());
        reserva.setValorReserva(reservaDto.valorReserva());
        reserva.setCodigoPagamento(UUID.randomUUID().toString());
        reserva.setCliente(cliente);
        reserva.setVeiculo(veiculo);

        return reserva;
    }

    public ReservaDto modeloParaDto(Reserva reserva) {
        return new ReservaDto(
                reserva.getId(),
                reserva.getValorReserva(),
                reserva.getCodigoPagamento(),
                clienteAssembler.modeloParaDto(reserva.getCliente()),
                veiculoAssembler.modeloParaDto(reserva.getVeiculo()));
    }
}
