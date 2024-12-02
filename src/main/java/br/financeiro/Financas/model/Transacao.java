package br.financeiro.Financas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String categoria;
    private String descricao;
    private LocalDateTime data;
    private double valor;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL, targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false)
    private Pessoa pessoa;
}
