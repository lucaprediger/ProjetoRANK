package com.mycompany.projetorank.modelo.lancamento;

import com.mycompany.projetorank.modelo.conta.Conta;
import java.util.*;

public class LancamentoRN {
	private LancamentoDAO	lancamentoDAO;
        
        public LancamentoRN () {
            lancamentoDAO = new LancamentoDAOHibernate();
        }
	
	public void salvar(Lancamento lancamento) {
		this.lancamentoDAO.salvar(lancamento);
	}
	public void excluir(Lancamento lancamento) {
		this.lancamentoDAO.excluir(lancamento);
	}
	public Lancamento carregar(Integer lancamento) {
		return this.lancamentoDAO.carregar(lancamento);
	}

    public List<Lancamento> listar(Conta conta, Object object, Date time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}
