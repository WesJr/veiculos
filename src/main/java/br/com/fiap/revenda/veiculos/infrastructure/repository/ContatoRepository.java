package br.com.fiap.revenda.veiculos.infrastructure.repository;

import br.com.fiap.revenda.veiculos.domain.model.Contato;
import org.springframework.data.repository.CrudRepository;

public interface ContatoRepository extends CrudRepository<Contato, Long> {
}
