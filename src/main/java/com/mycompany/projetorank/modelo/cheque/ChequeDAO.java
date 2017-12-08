package br.edu.utfpr.giuvane.modelo.cheque;

import br.edu.utfpr.giuvane.modelo.conta.Conta;
import java.util.List;

public interface ChequeDAO {

	public void salvar(Cheque cheque);
	public void atualizar(Cheque cheque);
	public void excluir(Cheque cheque);
	public Cheque carregar(ChequeId chequeId);
	public List<Cheque> listar(Conta conta);
} 
