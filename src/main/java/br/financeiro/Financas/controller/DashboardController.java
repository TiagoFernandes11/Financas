package br.financeiro.Financas.controller;

import br.financeiro.Financas.model.Pessoa;
import br.financeiro.Financas.model.Transacao;
import br.financeiro.Financas.services.PessoaService;
import br.financeiro.Financas.services.TransacaoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private static final Logger log = LoggerFactory.getLogger(DashboardController.class);
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

    @GetMapping("/nova-transacao")
    public String displayNovaDespesas(Model model){
        model.addAttribute("transacao", new Transacao());
        return "nova-transacao";
    }

    @PostMapping("/nova-transacao")
    public String addNovaDespesa(@Valid Transacao transacao, Errors errors, Authentication authentication){

        Pessoa pessoa = pessoaService.getPessoaByEmail(authentication.getName());
        transacao.setPessoa(pessoa);
        transacaoService.salvarDespesa(transacao);
        return "redirect:../dashboard";
    }

    @GetMapping("/novo-recebimento")
    public String displayNovoRecebimento(Model model){
        model.addAttribute("transacao", new Transacao());
        return "novo-recebimento";
    }

    @PostMapping( "/novo-recebimento")
    public String addNovoRecebimento(Transacao transacao, Errors errors, Authentication authentication){
        if(errors.hasErrors()){
            log.error("Formulari de transação falhou devido: " + errors.toString());
            return "novo-recebimento";
        }
        Pessoa pessoa = pessoaService.getPessoaByEmail(authentication.getName());
        transacao.setPessoa(pessoa);
        transacaoService.salvarRecebimento(transacao);
        return "redirect:../dashboard";
    }
}
