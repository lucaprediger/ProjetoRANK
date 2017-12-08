package br.edu.utfpr.giuvane.modelo.cheque;

import br.edu.utfpr.giuvane.modelo.conta.Conta;
import br.edu.utfpr.giuvane.modelo.lancamento.Lancamento;
import br.edu.utfpr.giuvane.util.RNException;
import java.util.*;

public class ChequeRN {

	private ChequeDAO chequeDAO;

	public ChequeRN() {
            //this.chequeDAO = DAOFactory.criarChequeDAO();
            this.chequeDAO = new ChequeDAOHibernate();
	}

	public void salvar(Cheque cheque) {
		this.chequeDAO.salvar(cheque);
	}

	public int salvarSequencia(Conta conta, Integer chequeInicial, Integer chequeFinal) { 
		Cheque cheque = null;
		ChequeId chequeId = null;
		int contaTotal = 0;
		for (int i = chequeInicial; i <= chequeFinal; i++) {
			chequeId = new ChequeId(conta.getConta(), i);
			cheque = this.carregar(chequeId);
			if (cheque == null) {
				cheque = new Cheque();
				cheque.setChequeId(chequeId);
				cheque.setSituacao(Cheque.SITUACAO_CHEQUE_NAO_EMITIDO);
				cheque.setDataCadastro(new Date());
				this.salvar(cheque);
				contaTotal++;
			}
		}
		return contaTotal;
	}

	public void excluir(Cheque cheque) throws RNException { 
		if (cheque.getSituacao() == Cheque.SITUACAO_CHEQUE_NAO_EMITIDO) {
			this.chequeDAO.excluir(cheque);
		} else {
			throw new RNException("Não é poss�vel excluir cheque, status não permitido para operação.");
		}
	}

	public Cheque carregar(ChequeId chequeId) { 
		return this.chequeDAO.carregar(chequeId);
	}

	public List<Cheque> listar(Conta conta) {
		return this.chequeDAO.listar(conta);
	}

	public void cancelarCheque(Cheque cheque) throws RNException { 
		if (cheque.getSituacao() == Cheque.SITUACAO_CHEQUE_NAO_EMITIDO 
			|| cheque.getSituacao() == Cheque.SITUACAO_CHEQUE_CANCELADO) {
			cheque.setSituacao(Cheque.SITUACAO_CHEQUE_CANCELADO);
			this.chequeDAO.salvar(cheque);
		} else {
			throw new RNException("Não é possível cancelar cheque, status não permitido para operação.");
		}
	}

	public void baixarCheque(ChequeId chequeId, Lancamento lancamento) {
		Cheque cheque = this.carregar(chequeId);
		if (cheque != null) {
			cheque.setSituacao(Cheque.SITUACAO_CHEQUE_BAIXADO);
			cheque.setLancamento(lancamento);
			//this.chequeDAO.salvar(cheque);
			this.chequeDAO.atualizar(cheque);
		}
	}

	public void desvinculaLancamento(Conta conta, Integer numeroCheque) throws RNException {  
		ChequeId chequeId = new ChequeId(conta.getConta(), numeroCheque);
		Cheque cheque = this.carregar(chequeId);
		if (cheque == null) {
			return;
		}
		if (cheque.getSituacao() == Cheque.SITUACAO_CHEQUE_CANCELADO) {
			throw new RNException("N�o � poss�vel usar cheque cancelado.");	
		} else {
			cheque.setSituacao(Cheque.SITUACAO_CHEQUE_NAO_EMITIDO);
			cheque.setLancamento(null);
			this.salvar(cheque);
		}
	}	
}
