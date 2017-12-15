package com.mycompany.projetorank.modelo.bolsa.acao;


import com.mycompany.projetorank.modelo.usuario.Usuario;
import com.mycompany.projetorank.util.UtilException;
import java.util.*;

public class AcaoRN {
    private AcaoDAO	acaoDAO;
    
    public AcaoRN() {
        this.acaoDAO = new AcaoDAOHibernate();
    }
    public void salvar(Acao acao) {
            this.acaoDAO.salvar(acao);
    }
    public void excluir(Acao acao) {
            this.acaoDAO.excluir(acao);
    }
    public List<Acao> listar(Usuario usuario) {
            return this.acaoDAO.listar(usuario);
    }
    public List<AcaoVirtual> listarAcaoVirtual(Usuario usuario) throws UtilException { 
            List<AcaoVirtual> listaAcaoVirtual = new ArrayList<AcaoVirtual>();
            AcaoVirtual acaoVirtual = null;
            String cotacao = null;
            float ultimoPreco = 0.0f;
            for (Acao acao : this.listar(usuario)) {
                    acaoVirtual = new AcaoVirtual();
                    acaoVirtual.setAcao(acao);
                    
            }
            return listaAcaoVirtual;
    }
}
