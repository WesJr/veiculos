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

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/incluir")
    public void incluirPagamento(@RequestBody PagamentoDto pagamentoDto) {

        pagamentoService.incluirPagamento(pagamentoDto);
    }

}
