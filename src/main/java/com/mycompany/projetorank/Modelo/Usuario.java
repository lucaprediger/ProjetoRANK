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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author ALUNO
 */
@Entity
@Table(name = "TB_USUARIO")
public class Usuario extends Pessoa implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "USU_ID")
    private Integer ID;

    @Column(name = "USU_NICK", length = 50, nullable = true)
    private String nomeUsuario;

    @OneToMany(mappedBy = "Usuario")
    @OnDelete(action = OnDeleteAction.CASCADE) //relacionamento tb_exercio_usuario
    private List<ExercicioUsuario> exercicioUsuario;

    @OneToMany(mappedBy = "Usuario")
    @OnDelete(action = OnDeleteAction.CASCADE) //relacionamento tb_exercio_usuario
    private List<UsuarioPermissao> usuarioPermissao;
    
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE) //relacionamento tb_pessoa
    private Pessoa pessoa;

    @OneToOne(mappedBy = "Usuario")
    @OnDelete(action = OnDeleteAction.CASCADE) //relacionamento tb_pessoa
    private Login login;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public List<ExercicioUsuario> getExercicioUsuario() {
        return exercicioUsuario;
    }

    public void setExercicioUsuario(List<ExercicioUsuario> exercicioUsuario) {
        this.exercicioUsuario = exercicioUsuario;
    }

    public List<UsuarioPermissao> getUsuarioPermissao() {
        return usuarioPermissao;
    }

    public void setUsuarioPermissao(List<UsuarioPermissao> usuarioPermissao) {
        this.usuarioPermissao = usuarioPermissao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    
    
    
}
