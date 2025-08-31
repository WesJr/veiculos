package br.com.fiap.revenda.veiculos.presentation.assembler;

import br.com.fiap.revenda.veiculos.domain.model.Cliente;
import br.com.fiap.revenda.veiculos.domain.model.Contato;
import br.com.fiap.revenda.veiculos.domain.model.Endereco;
import br.com.fiap.revenda.veiculos.presentation.dto.ClienteDto;
import br.com.fiap.revenda.veiculos.presentation.dto.ContatoDto;
import br.com.fiap.revenda.veiculos.presentation.dto.EnderecoDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ClienteAssembler {

    public Cliente dtoParaModelo(ClienteDto clienteDto, EnderecoDto enderecoDto, ContatoDto contatoDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(clienteDto.nascimento(), formatter);
        Contato contato = new Contato();
        Endereco endereco = new Endereco();

        Cliente cliente = new Cliente();
        cliente.setId(clienteDto.id());
        cliente.setNome(clienteDto.nome());
        cliente.setDocumento(clienteDto.documento());
        cliente.setNascimento(data);
        cliente.setSexo(clienteDto.sexo());

        contato.setId(contatoDto.id());
        contato.setTelefone(contatoDto.telefone());
        contato.setCelular(contatoDto.celular());
        contato.setEmail(contatoDto.email());

        endereco.setId(enderecoDto.id());
        endereco.setLogradouro(enderecoDto.logradouro());
        endereco.setNumero(enderecoDto.numero());
        endereco.setCidade(enderecoDto.cidade());
        endereco.setUf(enderecoDto.uf());
        endereco.setCep(enderecoDto.cep());

        cliente.setContato(contato);
        cliente.setEndereco(endereco);

        return cliente;
    }
}
