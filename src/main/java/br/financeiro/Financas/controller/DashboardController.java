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


}
