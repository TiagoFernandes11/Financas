package br.financeiro.Financas.controller;

import br.financeiro.Financas.model.Pessoa;
import br.financeiro.Financas.services.PessoaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    PessoaService pessoaService;

    @GetMapping(value = {"", "/", "/home"})
    public String displayHome(Model model){
        model.addAttribute("pessoa", new Pessoa());
        return "home";
    }

}
