/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projetorank.BancoDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.HibernateException;


/**
 *
 * @author ALUNO
 */
public class Conexao {
    
	private static EntityManagerFactory factory;
    private volatile static EntityManager  manager;
    
    //public static Connection connection = null; // manages connection
 
    private Conexao() { }

    public static EntityManager getInstance() {
        if (manager == null) {
            synchronized (Conexao.class) {
                if (manager == null) {

                    try {
                        factory = Persistence.createEntityManagerFactory("ProjetoRank");
                        manager = factory.createEntityManager ();
                    } catch(HibernateException he)
                    {
                        System.err.println(he.getMessage() + " Erro ao criar unidade de persistencia");
                    }

                }
            }
        }
        return manager;
    }  
}
