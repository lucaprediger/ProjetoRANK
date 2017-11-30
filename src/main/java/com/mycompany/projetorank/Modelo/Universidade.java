/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projetorank.Modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ALUNO
 */
@Entity
@Table(name = "TB_UNIVERSIDADE")
public class Universidade implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "NOME", length = 150, nullable = true)
    private String nome;
    @Column(name = "SIGLA", length = 20, nullable = true)
    private String sigla;
    @Column(name = "CAMPUS", length = 100, nullable = true)
    private String campus;

    @OneToMany(mappedBy = "universidade") // usa pra fazer as ralações
    private List<Pessoa> pessoa; //uma lista de pessoa para cada
                                    //univerdidade
}
