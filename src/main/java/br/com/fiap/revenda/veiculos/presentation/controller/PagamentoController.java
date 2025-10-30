package br.com.fiap.revenda.veiculos.presentation.controller;

import br.com.fiap.revenda.veiculos.domain.service.PagamentoService;
import br.com.fiap.revenda.veiculos.presentation.dto.PagamentoDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    @PostMapping("/realizar")
    public void realizarPagamento(@RequestBody PagamentoDto pagamentoDto) {
        service.realizarPagamento(pagamentoDto);
    }
}
