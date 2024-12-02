package br.financeiro.Financas.repository;

import br.financeiro.Financas.model.Pessoa;
import br.financeiro.Financas.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
    Transacao findByPessoa(Pessoa pessoa);
}
