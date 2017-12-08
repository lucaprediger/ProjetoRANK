package br.edu.utfpr.giuvane.modelo.lancamento;

import br.edu.utfpr.giuvane.modelo.categoria.Categoria;
import br.edu.utfpr.giuvane.modelo.conta.Conta;
import br.edu.utfpr.giuvane.modelo.dao.ConexaoHibernate;
import java.util.*;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

public class LancamentoDAOHibernate implements LancamentoDAO {
    
    //private Session	session;
    private EntityManager manager;
    
    public LancamentoDAOHibernate() {
        this.manager = ConexaoHibernate.getInstance();
    }
    
    public void salvar(Lancamento lancamento) {
        //this.session.saveOrUpdate(lancamento);
        this.manager.getTransaction().begin();
        this.manager.persist(lancamento);
        this.manager.flush();
        this.manager.getTransaction().commit();
    }
    public void excluir(Lancamento lancamento) {
        //this.session.delete(lancamento);
        this.manager.getTransaction().begin();
        this.manager.remove(lancamento);
        this.manager.flush();
        this.manager.getTransaction().commit();
    }
    public Lancamento carregar(Integer lancamento) {
        //return (Lancamento) this.session.get(Lancamento.class, lancamento);
        return (Lancamento) this.manager.find(Lancamento.class, lancamento);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Lancamento> listar(Conta conta, Date dataInicio, Date dataFim) { 
            
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> c = cb.createQuery(Lancamento.class);
        Root<Lancamento> a = c.from(Lancamento.class);
        c.select(a);   
        
        if (dataInicio != null && dataFim != null) {
            Predicate predicateBetween = cb.between(a.<Date>get("data"), dataInicio, dataFim);
            c.where(predicateBetween);
            //criteria.add(Restrictions.between("data", dataInicio, dataFim));
        } else if (dataInicio != null) {
            Predicate predicateGreaterThan = cb.greaterThanOrEqualTo(a.<Date>get("data"), dataInicio);
            c.where(predicateGreaterThan);
            //criteria.add(Restrictions.ge("data", dataInicio));
        } else if (dataFim != null) {
            Predicate predicateLessThan = cb.lessThanOrEqualTo(a.<Date>get("data"), dataFim);
            c.where(predicateLessThan);
            //criteria.add(Restrictions.le("data", dataFim));
        }
        
        Predicate predicateConta = cb.equal(a.get("conta"), conta);
        c.where(predicateConta);
        //criteria.add(Restrictions.eq("conta", conta));
        
        Order orderConta = cb.asc(a.get("data"));
        c.orderBy(orderConta);
        
        TypedQuery<Lancamento> query = this.manager.createQuery(c);
        List<Lancamento> lista = query.getResultList();
        return lista;

    }
    public float saldo(Conta conta, Date data) {
        
        
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<BigDecimal> c = cb.createQuery(BigDecimal.class);
        Root<Lancamento> a = c.from(Lancamento.class);
        
        
        //a.join("conta", JoinType.INNER);
        //a.join("categoria", JoinType.INNER);
        
        Predicate predicateConta = cb.equal(a.<Conta>get("conta"), conta);
        c.where(predicateConta);
        
        Predicate predicateData = cb.equal(a.<Date>get("data"), data);    
        c.where(predicateData);
        
        //sql.append("select sum(l.valor * c.fator)");
        c.select(cb.sum(cb.prod(a.<BigDecimal>get("valor"), a.join("categoria").<BigDecimal>get("fator"))));
        
        TypedQuery<BigDecimal> query = this.manager.createQuery(c);

        BigDecimal saldo = (BigDecimal) query.getSingleResult();
        if (saldo != null) {
                return saldo.floatValue();
        }
        return 0f;
    }
}
