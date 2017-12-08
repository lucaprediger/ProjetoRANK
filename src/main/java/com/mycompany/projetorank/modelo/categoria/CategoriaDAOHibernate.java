package br.edu.utfpr.giuvane.modelo.categoria;

import br.edu.utfpr.giuvane.modelo.conta.Conta;
import br.edu.utfpr.giuvane.modelo.dao.ConexaoHibernate;
import br.edu.utfpr.giuvane.modelo.usuario.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

public class CategoriaDAOHibernate implements CategoriaDAO {

    //private Session session;
    private EntityManager manager;
    
    public CategoriaDAOHibernate() {
        this.manager = ConexaoHibernate.getInstance();
    }

    public void setSession(Session session) {
            //this.session = session;
    }

    @Override
    public Categoria salvar(Categoria categoria) {
        this.manager.getTransaction().begin();
        this.manager.persist(categoria);
        this.manager.flush();
        this.manager.getTransaction().commit();
        
        //this.manager.flush();
        //this.manager.getTransaction().commit();
        //this.session.clear();
        //return merged;
        return categoria;
    }
    
    @Override
    public Categoria editar(Categoria categoria) {
        this.manager.getTransaction().begin();
        //Categoria merged = (Categoria) this.manager.merge(categoria);
        this.manager.merge(categoria);
        this.manager.flush();
        this.manager.getTransaction().commit();

        return categoria;
    }

    @Override
    public void excluir(Categoria categoria) {
        this.manager.getTransaction().begin();
        this.manager.remove(categoria);
        this.manager.flush();
        this.manager.getTransaction().commit();
        
        //this.manager.flush();
        //this.manager.clear();
        /*
            categoria = (Categoria) this.carregar(categoria.getCodigo());
            this.session.delete(categoria);
            this.session.flush();
            this.session.clear();
            */
    }

    @Override
    public Categoria carregar(Integer categoria) {
        return (Categoria) this.manager.find(Categoria.class, categoria);
            //return (Categoria) this.session.get(Categoria.class, categoria);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Categoria> listar(Usuario usuario) {
        
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Categoria> c = cb.createQuery(Categoria.class);
        Root<Categoria> a = c.from(Categoria.class);
        c.select(a);   
        
        Predicate predicate = cb.equal(a.get("usuario"), usuario);
        c.where(predicate);
        
        TypedQuery<Categoria> query = this.manager.createQuery(c);
        List<Categoria> lista = query.getResultList();
        return lista;
        
        /*
            String hql = "select c from Categoria c where c.pai is null and c.usuario_codigo = :codigo ";
            //Query query = this.session.createQuery(hql);
            Query query = this.manager.createQuery(hql);
            query.setParameter("codigo", usuario.getCodigo());
            //query.setInteger("usuario", usuario.getCodigo());

            List<Categoria> lista = query.getResultList();

            return lista;
        */
    }
}
