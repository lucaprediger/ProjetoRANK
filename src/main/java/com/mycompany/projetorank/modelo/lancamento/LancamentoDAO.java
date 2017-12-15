package com.mycompany.projetorank.modelo.lancamento;

import java.util.*;

public interface LancamentoDAO {

	public void salvar(Lancamento lancamento);
	public void excluir(Lancamento lancamento);
	public Lancamento carregar(Integer lancamento);
	
}
