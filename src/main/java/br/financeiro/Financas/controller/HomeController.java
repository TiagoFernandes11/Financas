package br.financeiro.Financas.controller;

import br.financeiro.Financas.model.Pessoa;
import br.financeiro.Financas.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    PessoaService pessoaService;

    @GetMapping(value = {"", "/", "/home"})
    public String displayHome(){
        return "home";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Pessoa pessoa, Model model){
        if(pessoaService.cadastrar(pessoa)){
            model.addAttribute("error", "Cadastro efetuado com sucesso");
            return "home";
        }
        model.addAttribute("error", "As senhas não são iguais ou o e-mail ja esta cadastrado");
        return "home";
    }

}
