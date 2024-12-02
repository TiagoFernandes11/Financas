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
    public String displayDashboard(Model model, Authentication authentication){
        Pessoa pessoa = pessoaService.getPessoaByEmail(authentication.getName());
        model.addAttribute("despesasTotais", transacaoService.getDespesaTotal(pessoa));
        model.addAttribute("transacoes", transacaoService.getTodasTransacoes(pessoa));
        return "dashboard";
    }

    @RequestMapping(value = "/nova-transacao", method = {RequestMethod.POST, RequestMethod.GET})
    public String addNovaDespesa(Transacao transacao, Model model, Authentication authentication){
        if(Objects.isNull(transacao) || transacao.getValor() <= 0){
            model.addAttribute("transacao", new Transacao());
            return "nova-transacao";
        }
        Pessoa pessoa = pessoaService.getPessoaByEmail(authentication.getName());
        transacao.setPessoa(pessoa);
        transacaoService.salvarDespesa(transacao);
        return "redirect:../dashboard";
    }

    @RequestMapping(value = "/novo-recebimento", method = {RequestMethod.POST, RequestMethod.GET})
    public String addNovoRecebimento(Transacao transacao, Model model, Authentication authentication){
        if(Objects.isNull(transacao) || transacao.getValor() <= 0){
            model.addAttribute("transacao", new Transacao());
            return "novo-recebimento";
        }
        Pessoa pessoa = pessoaService.getPessoaByEmail(authentication.getName());
        transacao.setPessoa(pessoa);
        transacaoService.salvarRecebimento(transacao);
        return "redirect:../dashboard";
    }
}
