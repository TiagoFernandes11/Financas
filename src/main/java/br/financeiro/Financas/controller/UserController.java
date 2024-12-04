package br.financeiro.Financas.controller;

import br.financeiro.Financas.model.Pessoa;
import br.financeiro.Financas.repository.PessoaRepository;
import br.financeiro.Financas.services.PessoaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    PessoaService pessoaService;

    @Autowired
    PessoaRepository pessoaRepository;

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String displayLogin(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Model model){
        if(null != error){
            model.addAttribute("error", "Credencias invalidas, tente novamente");
        }
        if(null != logout){
            model.addAttribute("error", "Você se deslogou com sucesso");
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/usuario/login?logout=true";
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

    @GetMapping("/update")
    public String displayEditarUsuario(Model model, Authentication authentication){
        Pessoa pessoa = pessoaService.getPessoaByEmail(authentication.getName());
        pessoa.setConfirmaSenha(pessoa.getSenha());
        model.addAttribute("pessoa", pessoa);
        return "editar-usuario";
    }

    @PostMapping("/update")
    public String editarUsuario(@Valid Pessoa pessoa, Errors errors){
        if(errors.hasErrors()){
            log.error(errors.toString());
        }
        if(pessoa.getSenha().equals(pessoa.getConfirmaSenha())){
            pessoaService.updateUsuario(pessoa);
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/delete")
    public String deletarUsuario(Pessoa pessoa, Errors errors, Authentication authentication){
        if(errors.hasErrors()){
            log.error(errors.toString());
        }
        pessoaService.deletarUsuario(pessoaService.getPessoaByEmail(authentication.getName()));
        return "redirect:/home";
    }
}
