package br.com.fiap.revenda.veiculos.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_reserva")
    private BigDecimal valorReserva;

    @Column(name = "codigo_pagamento")
    private String codigoPagamento;

    @ManyToOne
    private Cliente cliente;

    @OneToOne
    private Veiculo veiculo;
}
