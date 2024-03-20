package com.integracao.sistemas.api.trabalho01api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
//@Table(name = "tb_produto")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Produto {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String empresa;
    private String descricao;

    @Column(name = "quantidade")
    private int qtd;
    private String marca;
    private double valor;

}
