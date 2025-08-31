package br.com.fiap.revenda.veiculos.infrastructure.repository;

import br.com.fiap.revenda.veiculos.domain.model.Reserva;
import org.springframework.data.repository.CrudRepository;

public interface ReservaRepository extends CrudRepository<Reserva, Long> {
}
