package br.financeiro.Financas.repository;

import br.financeiro.Financas.model.Pessoa;
import br.financeiro.Financas.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
    List<Transacao> findByPessoa(Pessoa pessoa);
}
