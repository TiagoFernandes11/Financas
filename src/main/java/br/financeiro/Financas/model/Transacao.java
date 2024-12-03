package br.financeiro.Financas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Categoria não pode ser vazio")
    @Size(min = 3, message = "Categoria deve ter mais de 3 letras")
    private String categoria;

    @NotBlank(message = "Descricao não pode ser vazio")
    @Size(min = 5, message = "Descricao deve ter mais de 5 letras")
    private String descricao;

    @Past
    private LocalDateTime data;

    private double valor;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false)
    private Pessoa pessoa;
}
