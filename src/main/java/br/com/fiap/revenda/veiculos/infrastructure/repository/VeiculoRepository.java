package br.com.fiap.revenda.veiculos.infrastructure.repository;

import br.com.fiap.revenda.veiculos.domain.model.Veiculo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends CrudRepository<Veiculo, Long> {

}
