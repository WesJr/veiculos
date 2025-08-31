package br.com.fiap.revenda.veiculos.infrastructure.repository;

import br.com.fiap.revenda.veiculos.domain.model.Venda;
import org.springframework.data.repository.CrudRepository;

public interface VendaRepository extends CrudRepository<Venda, Long> {
}
