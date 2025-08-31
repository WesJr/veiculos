package br.com.fiap.revenda.veiculos.infrastructure.repository;

import br.com.fiap.revenda.veiculos.domain.model.Reserva;
import br.com.fiap.revenda.veiculos.domain.model.Veiculo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long> {

    List<Reserva> findByVeiculo(Veiculo veiculo);
}
