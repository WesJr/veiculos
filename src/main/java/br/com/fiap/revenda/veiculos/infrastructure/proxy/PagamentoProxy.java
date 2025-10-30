package br.com.fiap.revenda.veiculos.infrastructure.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "pagamento", url = "http://localhost:8081")
public interface PagamentoProxy {


}
