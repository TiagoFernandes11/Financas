package br.financeiro.Financas.services;

import br.financeiro.Financas.model.Pessoa;
import br.financeiro.Financas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void cadastrar(Pessoa pessoa){
        if(pessoa.getSenha().equals(pessoa.getConfirmaSenha())){
            pessoa.setSenha(passwordEncoder.encode(pessoa.getSenha()));
            pessoaRepository.save(pessoa);
        }
        else {
            throw new BadCredentialsException("Valores invalidos");
        }
    }

    public Pessoa getPessoaByEmail(String email){
        Pessoa pessoa = pessoaRepository.findByEmail(email);
        if(null != pessoa){
            return pessoa;
        }
        return null;
    }

    public List<Pessoa> getTodasPessoas(){
        return pessoaRepository.findAll();
    }
}
