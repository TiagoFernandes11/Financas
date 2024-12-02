package br.financeiro.Financas.services;

import br.financeiro.Financas.model.Pessoa;
import br.financeiro.Financas.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public boolean cadastrar(Pessoa pessoa){
        Pessoa temp = pessoaRepository.findByEmail(pessoa.getEmail());
        if(pessoa.getSenha().equals(pessoa.getConfirmaSenha()) && temp == null){
            pessoa.setSenha(passwordEncoder.encode(pessoa.getSenha()));
            pessoaRepository.save(pessoa);
            return true;
        }
        else {
            return false;
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
