package com.mycompany.projetorank.modelo.bolsa.acao;


import com.mycompany.projetorank.modelo.dao.ConexaoHibernate;
import com.mycompany.projetorank.modelo.usuario.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class AcaoDAOHibernate implements AcaoDAO {
    //private Session	session;
    private EntityManager manager;
    
    public AcaoDAOHibernate() {
        this.manager = ConexaoHibernate.getInstance();
    }

    public void salvar(Acao acao) {
            //this.session.saveOrUpdate(acao);
            this.manager.getTransaction().begin();
            this.manager.persist(acao);
            this.manager.flush();
            this.manager.getTransaction().commit();
    }
    public void excluir(Acao acao) {
            //this.session.delete(acao);
            this.manager.getTransaction().begin();
            this.manager.remove(acao);
            this.manager.flush();
            this.manager.getTransaction().commit();
    }
    public List<Acao> listar(Usuario usuario) {
        
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Acao> c = cb.createQuery(Acao.class);
        Root<Acao> a = c.from(Acao.class);
        c.select(a);   
        
        Predicate predicate = cb.equal(a.get("usuario"), usuario);
        c.where(predicate);
        
        TypedQuery<Acao> query = this.manager.createQuery(c);
        List<Acao> lista = query.getResultList();
        return lista;
        
    }
}
