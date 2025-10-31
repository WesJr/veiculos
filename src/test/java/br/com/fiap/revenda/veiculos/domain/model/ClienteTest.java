package br.com.fiap.revenda.veiculos.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteTest {

    @Test
    void deveCriarClienteComSucesso() {
        Contato contato = new Contato();
        contato.setEmail("teste@email.com");
        contato.setTelefone("11999999999");

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua de teste");
        endereco.setNumero("10");
        endereco.setCep("02115478");
        endereco.setCidade("São Paulo");

        Cliente cliente = new Cliente();

        cliente.setId(1L);
        cliente.setNome("João da Silva");
        cliente.setDocumento("12345678900");
        cliente.setNascimento(LocalDate.of(1990, 5, 15));
        cliente.setSexo("M");
        cliente.setContato(contato);
        cliente.setEndereco(endereco);

        // Assert
        assertEquals(1L, cliente.getId());
        assertEquals("João da Silva", cliente.getNome());
        assertEquals("12345678900", cliente.getDocumento());
        assertEquals(LocalDate.of(1990, 5, 15), cliente.getNascimento());
        assertEquals("M", cliente.getSexo());
        assertEquals(contato, cliente.getContato());
        assertEquals(endereco, cliente.getEndereco());
    }
}
