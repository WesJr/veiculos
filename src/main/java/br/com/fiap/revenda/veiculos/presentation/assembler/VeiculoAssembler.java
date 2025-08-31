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
        veiculo.setEmEstoque(veiculoDto.emEstoque());
        return veiculo;
    }

    public VeiculoDto modeloParaDto(Veiculo veiculo) {
        return new VeiculoDto(
                veiculo.getId(),
                veiculo.getMarca(),
                veiculo.getModelo(),
                veiculo.getAno(),
                veiculo.getCor(),
                veiculo.getPreco(),
                veiculo.getVendido(),
                veiculo.getEmEstoque());
    }

}
