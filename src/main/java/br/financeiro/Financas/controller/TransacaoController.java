package br.financeiro.Financas.controller;

import br.financeiro.Financas.model.Pessoa;
import br.financeiro.Financas.model.Transacao;
import br.financeiro.Financas.services.PessoaService;
import br.financeiro.Financas.services.TransacaoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    PessoaService pessoaService;

    @Autowired
    TransacaoService transacaoService;

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
    public String addNovoRecebimento(@Valid Transacao transacao, Errors errors, Authentication authentication){
        Pessoa pessoa = pessoaService.getPessoaByEmail(authentication.getName());
        transacao.setPessoa(pessoa);
        transacaoService.salvarRecebimento(transacao);
        return "redirect:../dashboard";
    }

    @GetMapping("/excluir/{id}")
    public String removerTransacao(@PathVariable int id){
        transacaoService.removerTransacao(id);
        return "redirect:../../dashboard";
    }
}
