package br.com.fiap.revenda.veiculos.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnderecoTest {

    @Test
    void deveCriarEnderecoComSucesso() {
        Endereco endereco = new Endereco();

        endereco.setId(1L);
        endereco.setLogradouro("Rua das Flores");
        endereco.setNumero("123");
        endereco.setCidade("São Paulo");
        endereco.setUf("SP");
        endereco.setCep("01001-000");

        assertEquals(1L, endereco.getId());
        assertEquals("Rua das Flores", endereco.getLogradouro());
        assertEquals("123", endereco.getNumero());
        assertEquals("São Paulo", endereco.getCidade());
        assertEquals("SP", endereco.getUf());
        assertEquals("01001-000", endereco.getCep());
    }
}
