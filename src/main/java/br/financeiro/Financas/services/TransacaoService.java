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
        transacaoRepository.save(transacao);
    }

    public List<Transacao> getTodasTransacoes(){
        return transacaoRepository.findAll();
    }
}
