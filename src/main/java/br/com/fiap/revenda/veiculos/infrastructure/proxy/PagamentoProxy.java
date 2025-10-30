package br.com.fiap.revenda.veiculos.infrastructure.proxy;

import br.com.fiap.revenda.veiculos.presentation.dto.PagamentoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pagamento", url = "http://localhost:8081")
public interface PagamentoProxy {

    @PostMapping("/pagamento/realizar")
    void realizarPagamento(@RequestBody PagamentoDto pagamentoDto);
}
