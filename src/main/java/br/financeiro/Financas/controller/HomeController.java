package br.financeiro.Financas.controller;

import br.financeiro.Financas.model.Pessoa;
import br.financeiro.Financas.services.PessoaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(@Valid Pessoa pessoa, Errors errors){
        ModelAndView modelAndView = new ModelAndView("home");
        if(errors.hasErrors()){
            log.error("Contact form validation failed due to : " + errors.toString());
            return modelAndView;
        }
        if(pessoaService.cadastrar(pessoa)){
            modelAndView.addObject("error", "Cadastro efetuado com sucesso");
            return modelAndView;
        }
        else {
            return modelAndView;
        }
    }

}
