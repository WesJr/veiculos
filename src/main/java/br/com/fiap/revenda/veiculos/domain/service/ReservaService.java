package br.com.fiap.revenda.veiculos.domain.service;

import br.com.fiap.revenda.veiculos.domain.model.Reserva;
import br.com.fiap.revenda.veiculos.infrastructure.repository.ReservaRepository;
import br.com.fiap.revenda.veiculos.presentation.assembler.ReservaAssembler;
import br.com.fiap.revenda.veiculos.presentation.dto.ReservaDto;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    private final ReservaRepository repository;
    private final ReservaAssembler assembler;

    public ReservaService(ReservaRepository repository, ReservaAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    public String reservar(ReservaDto reservaDto) {

        Reserva reserva = repository.save(assembler.dtoParaModelo(reservaDto));
        return reserva.getCodigoReserva();
    }
}
