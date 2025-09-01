package br.com.fiap.revenda.veiculos.domain.service;

import br.com.fiap.revenda.veiculos.domain.model.Pagamento;
import br.com.fiap.revenda.veiculos.domain.model.Veiculo;
import br.com.fiap.revenda.veiculos.infrastructure.repository.PagamentoRepository;
import br.com.fiap.revenda.veiculos.presentation.Exception.PagamentoException;
import br.com.fiap.revenda.veiculos.presentation.assembler.PagamentoAssembler;
import br.com.fiap.revenda.veiculos.presentation.assembler.VeiculoAssembler;
import br.com.fiap.revenda.veiculos.presentation.dto.PagamentoDto;
import br.com.fiap.revenda.veiculos.presentation.dto.ReservaDto;
import br.com.fiap.revenda.veiculos.presentation.enumerado.Status;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;
    private final PagamentoAssembler assembler;
    private final VeiculoAssembler veiculoAssembler;
    private final VeiculoService veiculoService;
    private final ReservaService reservaService;

    public PagamentoService(PagamentoRepository repository, PagamentoAssembler assembler, VeiculoAssembler veiculoAssembler, VeiculoService veiculoService, ReservaService reservaService) {

        this.repository = repository;
        this.assembler = assembler;
        this.veiculoAssembler = veiculoAssembler;
        this.veiculoService = veiculoService;
        this.reservaService = reservaService;
    }

    public void incluirPagamento(PagamentoDto pagamentoDto) {

        Pagamento pagamento = assembler.dtoParaModelo(pagamentoDto);
        ReservaDto reserva = reservaService.buscarReservaPorCodigo(pagamentoDto.codigoPagamento());

        try {
            if(Objects.nonNull(reserva)) {
                pagamento.setStatus(Status.APROVADO);
                repository.save(pagamento);

                Veiculo veiculo = veiculoAssembler.dtoParaModelo(veiculoService.consultarPorId(reserva.veiculoDto().id()));
                veiculo.setVendido(true);

                veiculoService.inserirVeiculo(veiculoAssembler.modeloParaDto(veiculo));

            } else {
                pagamento.setStatus(Status.RECUSADO);
                repository.save(pagamento);
                throw new PagamentoException("O pagamento não pode ser realizado sem reserva posterior");
            }
        } catch (Exception e) {
            throw new PagamentoException("O pagamento não foi realizado", e);
        }


    }
}
