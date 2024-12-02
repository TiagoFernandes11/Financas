package br.financeiro.Financas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String displayLogin(@RequestParam(required = false) String error, Model model){
        if(null != error){
            model.addAttribute("error", "Credencias invalidas, tente novamente");
        }
        return "login";
    }
}
