package br.com.fiap.revenda.veiculos.infrastructure.proxy;

import br.com.fiap.revenda.veiculos.presentation.dto.PagamentoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "pagamento", url = "http://localhost:8081")
public interface PagamentoProxy {

    @PostMapping("/pagamento/realizar")
    void realizarPagamento(PagamentoDto pagamentoDto);
}
