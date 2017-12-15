package com.mycompany.projetorank.modelo.bolsa.acao;
import com.mycompany.projetorank.modelo.usuario.Usuario;
import java.util.List;

public interface AcaoDAO {
	public void salvar(Acao acao);
	public void excluir(Acao acao);
	public List<Acao> listar(Usuario usuario);
}
