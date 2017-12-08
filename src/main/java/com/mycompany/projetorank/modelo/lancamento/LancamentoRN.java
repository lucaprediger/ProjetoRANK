package br.edu.utfpr.giuvane.modelo.lancamento;
import br.edu.utfpr.giuvane.modelo.conta.Conta;
import br.edu.utfpr.giuvane.util.DAOFactory;
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
	public float saldo(Conta conta, Date data) { 
		float saldoInicial = conta.getSaldoInicial();
		float saldoNaData = this.lancamentoDAO.saldo(conta, data);
		return saldoInicial + saldoNaData;
	}
	public List<Lancamento> listar(Conta conta, Date dataInicio, Date dataFim) { 
		return this.lancamentoDAO.listar(conta, dataInicio, dataFim);
	}
}
