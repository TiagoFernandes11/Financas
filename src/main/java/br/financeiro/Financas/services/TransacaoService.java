package br.financeiro.Financas.services;

import br.financeiro.Financas.model.Transacao;
import br.financeiro.Financas.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    public void save(Transacao transacao){
        transacao.setValor(0.0 - transacao.getValor());
        transacaoRepository.save(transacao);
    }

    public List<Transacao> getTodasTransacoes(){
        return transacaoRepository.findAll();
    }

    public double getDespesaTotal(){
        List<Transacao> transacaos = getTodasTransacoes();
        double total = 0.0;
        for(Transacao transacao: transacaos){
            total += transacao.getValor();
        }
        return total;
    }
}
