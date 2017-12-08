package br.edu.utfpr.giuvane.modelo.cheque;

import br.edu.utfpr.giuvane.modelo.categoria.Categoria;
import br.edu.utfpr.giuvane.modelo.conta.Conta;
import br.edu.utfpr.giuvane.modelo.dao.ConexaoHibernate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

public class ChequeDAOHibernate implements ChequeDAO {

    //private Session	session;
    private EntityManager manager;
    
    public ChequeDAOHibernate() {
        this.manager = ConexaoHibernate.getInstance();
    }
    
    @Override
    public void salvar(Cheque cheque) {
        //this.session.saveOrUpdate(cheque);
        //this.manager.getTransaction().begin();
        this.manager.persist(cheque);
        //this.manager.flush();
        //this.manager.getTransaction().commit();
    }
	
	@Override
    public void atualizar(Cheque cheque) {
        this.manager.merge(cheque);
	}
	
    @Override
    public void excluir(Cheque cheque) {
        //this.session.delete(cheque);
        this.manager.getTransaction().begin();
        this.manager.remove(cheque);
        this.manager.flush();
        this.manager.getTransaction().commit();
    }
    @Override
    public Cheque carregar(ChequeId chequeId) { 
        //return (Cheque) this.session.get(Cheque.class, chequeId);
        return (Cheque) this.manager.find(Cheque.class, chequeId);
    }
    @Override
    public List<Cheque> listar(Conta conta) {
        
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Cheque> c = cb.createQuery(Cheque.class);
        Root<Cheque> a = c.from(Cheque.class);
        c.select(a);   
        
        Predicate predicate = cb.equal(a.get("conta"), conta);
        c.where(predicate);
        
        TypedQuery<Cheque> query = this.manager.createQuery(c);
        List<Cheque> lista = query.getResultList();
        return lista;
        
    }
} 
