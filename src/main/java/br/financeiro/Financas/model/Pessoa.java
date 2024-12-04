package br.financeiro.Financas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Nome não pode ser vazio")
    @Size(min = 3, message = "Nome deve ter mais de 3 letras")
    private String nome;

    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Email deve ser valido")
    private String email;

    @NotBlank(message = "Senha não pode ser vazia")
    @Size(min = 5, message = "A senha deve ter mais de 5 caracteres")
    private String senha;

    @NotBlank(message = "Confirmação de senha não pode ser vazia")
    @Transient
    private String confirmaSenha;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST, targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "id",nullable = false)
    private Role role;
}
