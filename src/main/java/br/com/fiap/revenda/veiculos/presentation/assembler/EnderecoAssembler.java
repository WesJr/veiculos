package br.com.fiap.revenda.veiculos.presentation.assembler;

import br.com.fiap.revenda.veiculos.domain.model.Endereco;
import br.com.fiap.revenda.veiculos.presentation.dto.EnderecoDto;
import org.springframework.stereotype.Component;

@Component
public class EnderecoAssembler {

    public Endereco dtoParaModelo(EnderecoDto enderecoDto){
        Endereco endereco = new Endereco();
        endereco.setId(enderecoDto.id());
        endereco.setLogradouro(enderecoDto.logradouro());
        endereco.setNumero(enderecoDto.numero());
        endereco.setCidade(enderecoDto.cidade());
        endereco.setUf(enderecoDto.uf());
        endereco.setCep(enderecoDto.cep());
        return endereco;
    }

    public EnderecoDto modeloParaDto(Endereco endereco){
       return new EnderecoDto(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getUf(),
                endereco.getCep());
    }

}
