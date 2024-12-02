package br.financeiro.Financas.controller;

import br.financeiro.Financas.model.Pessoa;
import br.financeiro.Financas.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    PessoaService pessoaService;

    @GetMapping(value = {"", "/", "/home"})
    public String displayHome(){
        return "home";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Pessoa pessoa){
        pessoaService.cadastrar(pessoa);
        return "home";
    }

}
