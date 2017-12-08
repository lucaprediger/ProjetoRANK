package com.mycompany.projetorank.web.filter;

import com.mycompany.projetorank.modelo.dao.ConexaoHibernate;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = { "*.jsf", "/webservice/*" })
public class ConexaoHibernateFilter implements Filter {
	//private SessionFactory sf;
    private EntityManager manager;

    @Override
    public void init(FilterConfig config) throws ServletException {
            //this.sf = HibernateUtil.getSessionFactory();
            this.manager = ConexaoHibernate.getInstance();
            //this.manager.getTransaction().begin();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
            FilterChain chain) throws ServletException, IOException {

        //Session currentSession = this.sf.getCurrentSession();

        chain.doFilter(servletRequest, servletResponse);
        
        try {
           //transaction = currentSession.beginTransaction();
           //this.manager.getTransaction().begin();
           //chain.doFilter(servletRequest, servletResponse);
           //this.manager.flush();
           //this.manager.getTransaction().commit();
           
        } catch (Exception ex) {
            //chain.doFilter(servletRequest, servletResponse);
            
            //throw new ServletException(ex);
        }
    }

    @Override
    public void destroy() {
    }
}
