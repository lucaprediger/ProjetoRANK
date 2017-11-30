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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author ALUNO
 */
@Entity
@Table(name = "TB_EXERCICIOS")
public class Exercicios implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "EXE_ID")
    private Integer ID;

    @Column(name = "EXE_NUMERO", nullable = true)
    private String numeroURI;

    @Column(name = "EXE_TITULO", length = 250, nullable = true)
    private String tituloExercicio;

    @Column(name = "EXE_VALORESTRELA", nullable = true)
    private Integer valorEstrela;

    @OneToMany(mappedBy = "Exercicio")
    @OnDelete(action = OnDeleteAction.CASCADE) //relacionamento tb_exercio_usuario
    private List<ExercicioUsuario> exercicioUsuario;
}
