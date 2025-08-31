package br.com.fiap.revenda.veiculos.domain.service;

import br.com.fiap.revenda.veiculos.domain.model.Reserva;
import br.com.fiap.revenda.veiculos.infrastructure.repository.ReservaRepository;
import br.com.fiap.revenda.veiculos.presentation.Exception.ReservaException;
import br.com.fiap.revenda.veiculos.presentation.assembler.ReservaAssembler;
import br.com.fiap.revenda.veiculos.presentation.dto.ReservaDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository repository;
    private final ReservaAssembler assembler;

    public ReservaService(ReservaRepository repository, ReservaAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public String reservar(ReservaDto reservaDto) {

        Reserva reserva = assembler.dtoParaModelo(reservaDto);
        List<Reserva> listaReservas = repository.findByVeiculo(reserva.getVeiculo());

        if(!listaReservas.isEmpty()) {
            throw new ReservaException("Veiculo já foi reservado");
        } else {
            Reserva reserva1 = repository.save(reserva);
            return reserva1.getCodigoPagamento();
        }
    }

    public ReservaDto buscarReservaPorId(Long id) {
        return repository.findById(id)
                .map(assembler :: modeloParaDto)
                .orElseThrow(() -> new ReservaException("Não foi possível encontrar a reserva"));
    }

    public ReservaDto buscarReservaPorCodigo(String codigo) {
        return repository.findByCodigoPagamento(codigo)
                .map(assembler :: modeloParaDto)
                .orElseThrow(() -> new ReservaException("Não foi possível encontrar a reserva"));
    }
}
