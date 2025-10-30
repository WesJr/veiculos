package br.com.fiap.revenda.veiculos.domain.service;

import br.com.fiap.revenda.veiculos.infrastructure.proxy.PagamentoProxy;
import br.com.fiap.revenda.veiculos.presentation.dto.PagamentoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    private final PagamentoProxy proxy;

    public PagamentoService(PagamentoProxy proxy) {
        this.proxy = proxy;
    }


    public void realizarPagamento(PagamentoDto pagamentoDto) {
        proxy.realizarPagamento(pagamentoDto);
    }
}
