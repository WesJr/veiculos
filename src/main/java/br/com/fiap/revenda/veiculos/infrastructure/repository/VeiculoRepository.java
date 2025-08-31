package br.com.fiap.revenda.veiculos.infrastructure.repository;

import br.com.fiap.revenda.veiculos.domain.model.Veiculo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends CrudRepository<Veiculo, Long> {

    List<Veiculo> findByVendidoFalseOrderByPrecoAsc();
    List<Veiculo> findByVendidoTrueOrderByPrecoAsc();
}
