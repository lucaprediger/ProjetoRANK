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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author ALUNO
 */
@Entity
@Table(name= "TB_LOGIN")
public class Login implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "LOG_ID")
    private Integer ID;
    
    @Column(name = "EMAIL", length = 100, nullable = true)
    private String email;
    @Column(name = "SENHA", length = 100, nullable = true)
    private String senha;
    @Column(name = "HASH", nullable = true)
    private String hash;
    @Column(name = "VALIDAEMAIL", length = 100, nullable = true)
    private String validaEmail;    

    @OneToOne
    private Usuario usuario;
    
    
    
}
