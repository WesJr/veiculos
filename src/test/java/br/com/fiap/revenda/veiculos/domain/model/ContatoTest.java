package br.com.fiap.revenda.veiculos.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContatoTest {

    @Test
    void deveCriarContatoComSucesso() {
        Contato contato = new Contato();

        contato.setId(1L);
        contato.setTelefone("1133334444");
        contato.setCelular("11999999999");
        contato.setEmail("usuario@teste.com");

        assertEquals(1L, contato.getId());
        assertEquals("1133334444", contato.getTelefone());
        assertEquals("11999999999", contato.getCelular());
        assertEquals("usuario@teste.com", contato.getEmail());
    }
}
