package com.mycompany.projetorank.web;


import com.mycompany.projetorank.modelo.categoria.Categoria;
import com.mycompany.projetorank.modelo.conta.Conta;
import com.mycompany.projetorank.modelo.lancamento.Lancamento;
import com.mycompany.projetorank.modelo.lancamento.LancamentoRN;
import com.mycompany.projetorank.util.UtilException;
import com.mycompany.projetorank.web.util.RelatorioUtil;
import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "lancamentoBean")
@ViewScoped
public class LancamentoBean implements Serializable {
    private static final long serialVersionUID = -3050807461213326560L;
    private List<Lancamento> lista;
    private Conta conta;
    private List<Double> saldos;
    private float saldoGeral;
    private Lancamento editado = new Lancamento();
    private Integer	numeroCheque; 

    private java.util.Date		dataInicialRelatorio;
    private java.util.Date		dataFinalRelatorio;
    private StreamedContent	arquivoRetorno;

    @ManagedProperty(value = "#{contextoBean}")
    private ContextoBean contextoBean;

    public LancamentoBean() {
            this.novo();
    }

    public String novo() {
            this.editado = new Lancamento();
            this.editado.setData(new Date());
            this.numeroCheque = null; 
            return null;
    }

    

    public void salvar() {
            this.editado.setUsuario(this.contextoBean.getUsuarioLogado());
            this.editado.setConta(this.contextoBean.getContaAtiva());

            


            LancamentoRN lancamentoRN = new LancamentoRN();
            lancamentoRN.salvar(this.editado);

            this.novo();
            this.lista = null;
    }

    


    public void excluir() {
            LancamentoRN lancamentoRN = new LancamentoRN();
            lancamentoRN.excluir(this.editado);
            this.lista = null;
    }

    public StreamedContent getArquivoRetorno() {
            FacesContext context = FacesContext.getCurrentInstance();
            String usuario = contextoBean.getUsuarioLogado().getLogin();
            String nomeRelatorioJasper = "extrato";
            String nomeRelatorioSaida = usuario + "_extrato"; 
            LancamentoRN lancamentoRN = new LancamentoRN();
            GregorianCalendar calendario = new GregorianCalendar();
            calendario.setTime(this.getDataInicialRelatorio());
            calendario.add(Calendar.DAY_OF_MONTH, -1); 
            Date dataSaldo = new Date(calendario.getTimeInMillis());
            RelatorioUtil relatorioUtil = new RelatorioUtil();
            HashMap parametrosRelatorio = new HashMap(); 
            parametrosRelatorio.put("codigoUsuario", contextoBean.getUsuarioLogado().getCodigo());
            parametrosRelatorio.put("numeroConta", contextoBean.getContaAtiva().getConta());
            parametrosRelatorio.put("dataInicial", this.getDataInicialRelatorio());
            parametrosRelatorio.put("dataFinal", this.getDataFinalRelatorio());
            
            try {
                    this.arquivoRetorno = relatorioUtil.geraRelatorio(parametrosRelatorio, nomeRelatorioJasper,
                            nomeRelatorioSaida, RelatorioUtil.RELATORIO_PDF);
            } catch (UtilException e) {
                    context.addMessage(null, new FacesMessage(e.getMessage()));
                    return null;
            } 
            return this.arquivoRetorno;
    }

    public java.util.Date getDataInicialRelatorio() {
            return dataInicialRelatorio;
    }

    public void setDataInicialRelatorio(java.util.Date dataInicialRelatorio) {
            this.dataInicialRelatorio = dataInicialRelatorio;
    }

    public java.util.Date getDataFinalRelatorio() {
            return dataFinalRelatorio;
    }

    public void setDataFinalRelatorio(java.util.Date dataFinalRelatorio) {
            this.dataFinalRelatorio = dataFinalRelatorio;
    }

    public void setArquivoRetorno(StreamedContent arquivoRetorno) {
            this.arquivoRetorno = arquivoRetorno;
    }

    public List<Lancamento> getLista() {
            if (this.lista == null
                            || this.conta != this.contextoBean.getContaAtiva()) {
                    this.conta = this.contextoBean.getContaAtiva();

                    Calendar dataSaldo = new GregorianCalendar();
                    dataSaldo.add(Calendar.MONTH, -1);
                    dataSaldo.add(Calendar.DAY_OF_MONTH, -1);

                    Calendar inicio = new GregorianCalendar();
                    inicio.add(Calendar.MONTH, -1);

                    LancamentoRN lancamentoRN = new LancamentoRN();
                   
                    this.lista = lancamentoRN
                                    .listar(this.conta, inicio.getTime(), null);

                    Categoria categoria = null;
                    double saldo = this.saldoGeral;
                    this.saldos = new ArrayList<Double>();
                    for (Lancamento lancamento : this.lista) {
                            categoria = lancamento.getCategoria();
                            saldo = saldo
                                            + (lancamento.getValor().floatValue() * categoria
                                                            .getFator());
                            this.saldos.add(saldo);
                    }
            }
            return this.lista;
    }

    public Conta getConta() {
            return conta;
    }

    public void setConta(Conta conta) {
            this.conta = conta;
    }

    public List<Double> getSaldos() {
            return saldos;
    }

    public void setSaldos(List<Double> saldos) {
            this.saldos = saldos;
    }

    public float getSaldoGeral() {
            return saldoGeral;
    }

    public void setSaldoGeral(float saldoGeral) {
            this.saldoGeral = saldoGeral;
    }

    public Lancamento getEditado() {
            return editado;
    }

    public void setEditado(Lancamento editado) {
            this.editado = editado;
    }

    public ContextoBean getContextoBean() {
            return contextoBean;
    }

    public void setContextoBean(ContextoBean contextoBean) {
            this.contextoBean = contextoBean;
    }

    public void setLista(List<Lancamento> lista) {
            this.lista = lista;
    }

    public void setNumeroCheque(Integer numeroCheque) {
            this.numeroCheque = numeroCheque;
    }

    public Integer getNumeroCheque() {
            return numeroCheque;
    }
}
