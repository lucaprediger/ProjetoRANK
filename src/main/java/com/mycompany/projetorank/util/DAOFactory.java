package com.mycompany.projetorank.util;


import com.mycompany.projetorank.modelo.usuario.UsuarioDAO;
import com.mycompany.projetorank.modelo.usuario.UsuarioDAOHibernate;

public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO; 
	}
}
