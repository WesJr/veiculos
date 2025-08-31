package br.com.fiap.revenda.veiculos.presentation.assembler;

import br.com.fiap.revenda.veiculos.domain.model.Veiculo;
import br.com.fiap.revenda.veiculos.presentation.dto.VeiculoDto;
import org.springframework.stereotype.Component;

@Component
public class VeiculoAssembler {

    public Veiculo dtoParaModelo(VeiculoDto veiculoDto) {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(veiculoDto.id());
        veiculo.setMarca(veiculoDto.marca());
        veiculo.setModelo(veiculoDto.modelo());
        veiculo.setAno(veiculoDto.ano());
        veiculo.setCor(veiculoDto.cor());
        veiculo.setPreco(veiculoDto.preco());
        veiculo.setVendido(veiculoDto.vendido());
        return veiculo;
    }

}
