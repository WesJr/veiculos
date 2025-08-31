package br.com.fiap.revenda.veiculos.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String documento;
    private LocalDate nascimento;
    private String sexo;

    @OneToOne
    private Contato contato;

    @OneToOne
    private Endereco endereco;
}
