package br.com.fiap.revenda.veiculos.infrastructure.repository;

import br.com.fiap.revenda.veiculos.domain.model.Endereco;
import org.springframework.data.repository.CrudRepository;

public interface EnderecoRepository extends CrudRepository<Endereco, Long>{
}
