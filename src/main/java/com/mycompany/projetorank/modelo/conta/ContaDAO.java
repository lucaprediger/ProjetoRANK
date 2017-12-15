package com.mycompany.projetorank.modelo.conta;





import com.mycompany.projetorank.modelo.usuario.Usuario;
import java.util.List;
public interface ContaDAO {
	public void salvar(Conta conta);
	public void excluir(Conta conta);
	public Conta carregar(Integer conta);
	public List<Conta> listar(Usuario usuario); 
	public Conta buscarFavorita(Usuario usuario); 
} 
