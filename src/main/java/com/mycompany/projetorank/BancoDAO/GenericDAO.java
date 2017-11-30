/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projetorank.BancoDAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Giuvane
 */
public class GenericDAO<T> implements iGenericDAO<T> {

    EntityManager manager = Conexao.getInstance();
    
    public void commit()
    {
        manager.getTransaction().commit(); // faz o commit no banco de dados
    }
    
    @Override
    public void save(T object) {  //salva o objeto
        manager.getTransaction().begin();
        manager.persist(object);
        manager.getTransaction().commit();
        System.out.println(object.getClass().getSimpleName() + " salvo som sucesso!");
    }

    @Override
    public T listOne(String pkName, int pkValue, Class clazz) { // faz a pesquisa com inteiro
        String jpql = " SELECT t FROM " + clazz.getTypeName() + " t where t." + pkName + " = " + pkValue;
        Query query = manager.createQuery (jpql);
        Object obj = query.getSingleResult();
        return (T) obj;
    }
    
    
    public T listOne(String pkName, String pkValue, Class clazz) {
        String jpql = " SELECT t FROM " + clazz.getTypeName() + " t where t." + pkName + " = " + pkValue;
        Query query = manager.createQuery (jpql);
        Object obj = query.getSingleResult();
        return (T) obj;
    }

    @Override
    public List listAll(int first, int max) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(T object) {
        manager.getTransaction().begin();
        manager.persist(object);
        manager.getTransaction().commit();
        System.out.println(object.getClass().getSimpleName() + " atualizado som sucesso!");
        
    }

    @Override
    public void delete(T object) {
        manager.getTransaction().begin();
        manager.remove(object);
        manager.getTransaction().commit();
        System.out.println(object.getClass().getSimpleName() + " excluído som sucesso!");
    }

    @Override
    public List listAll(Class clazz) {
        String jpql = " SELECT t FROM " + clazz.getTypeName() + " t";
        Query query = manager.createQuery(jpql);
        List<T> objects = query.getResultList();
        return objects;
    }
    
}