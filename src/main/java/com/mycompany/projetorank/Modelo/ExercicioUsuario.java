/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projetorank.Modelo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author ALUNO
 */
public class ExercicioUsuario {
    @Id
    @GeneratedValue
    @Column(name = "EXE_ID")
    private Integer ID;
    
    
    
    
}
