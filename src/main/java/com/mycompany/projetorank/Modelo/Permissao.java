/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projetorank.Modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ALUNO
 */
@Entity
@Table(name = "TB_PERMISSAO")
public class Permissao implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "PER_ID")
    private Integer ID;

    @Column(name = "PER_TIPOS", nullable = true)
    private Integer tipo;
    
    
    
}
