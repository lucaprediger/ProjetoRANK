package br.edu.utfpr.giuvane.modelo.lancamento;

import br.edu.utfpr.giuvane.modelo.conta.Conta;
import java.util.*;

public interface LancamentoDAO {

	public void salvar(Lancamento lancamento);
	public void excluir(Lancamento lancamento);
	public Lancamento carregar(Integer lancamento);
	public List<Lancamento> listar(Conta conta, Date dataInicio, Date dataFim);
	public float saldo(Conta conta, Date data);
}
