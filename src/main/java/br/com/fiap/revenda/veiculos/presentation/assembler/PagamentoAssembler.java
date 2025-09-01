package br.com.fiap.revenda.veiculos.presentation.assembler;

import br.com.fiap.revenda.veiculos.domain.model.Pagamento;
import br.com.fiap.revenda.veiculos.presentation.dto.PagamentoDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PagamentoAssembler {

    public Pagamento dtoParaModelo(PagamentoDto pagamentoDto) {

        Pagamento pagamento = new Pagamento();

        pagamento.setId(pagamentoDto.id());
        pagamento.setDataVenda(LocalDateTime.now());
        pagamento.setValorRemanescente(pagamentoDto.valorRemanescente());
        pagamento.setCodigoPagamento(pagamentoDto.codigoPagamento());

        return pagamento;

    }
}
