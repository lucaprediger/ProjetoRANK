/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projetorank.Bean;

import com.mycompany.projetorank.Modelo.Pessoa;
import com.mycompany.projetorank.Modelo.Usuario;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ALUNO
 */
@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean {

        private Usuario u;
        
	private List<Usuario> lista;
	private String destinoSalvar;
	

    public String novo() {
        this.destinoSalvar = "usuariosucesso";
        this.u = new Usuario();
        return "/ArquivosWeb/UsuarioWeb";
    }
    
    public String salvar(){
        //fazer com o hibernate
        
        
        return this.destinoSalvar;
    }

    
    
}
