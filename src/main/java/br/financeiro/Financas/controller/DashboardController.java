package br.financeiro.Financas.controller;

import br.financeiro.Financas.model.Pessoa;
import br.financeiro.Financas.model.Transacao;
import br.financeiro.Financas.services.PessoaService;
import br.financeiro.Financas.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Objects;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    TransacaoService transacaoService;

    @Autowired
    PessoaService pessoaService;

    @GetMapping
    public String displayDashboard(Model model){
        model.addAttribute("transacoes", transacaoService.getTodasTransacoes());
        return "dashboard";
    }

    @RequestMapping(value = "/nova-transacao", method = {RequestMethod.POST, RequestMethod.GET})
    public String addNovaTransacao(Transacao transacao, Model model, Authentication authentication){
        if(Objects.isNull(transacao) || transacao.getValor() <= 0){
            model.addAttribute("transacao", new Transacao());
            model.addAttribute("pessoas", pessoaService.getTodasPessoas());
            return "nova-transacao";
        }
        Pessoa pessoa = pessoaService.getPessoaByEmail(authentication.getName());
        transacao.setPessoa(pessoa);
        transacaoService.save(transacao);
        return "nova-transacao";
    }
}
