package br.gov.mt.mti.fiplangrf.web.bean.log;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.hibernate.envers.RevisionType;
import org.ocpsoft.shade.org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.gov.mt.mti.fiplangrf.criteria.user.UsuarioCriteria;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.service.log.LogService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.CriteriaLogApp;

@SuppressWarnings("rawtypes")
@Named("pesquisarLogBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "pesquisarLog", pattern = "/audit/log", viewId = "/pages/audit/log.jsf") 
		})
public class PesquisarLogBean extends AbstractManterBean implements Serializable {

    private static final long serialVersionUID = 768780291957001170L;

    private static final Logger LOGGER = LoggerFactory.getLogger(PesquisarLogBean.class);    
    
	private List<CriteriaLogApp> listaClasses = new ArrayList<CriteriaLogApp>();
	
	private static final String PERMISSAO_ACTION = "audit.log";
	
	@Inject
    private LogService logService;
	
//	@Inject
//	private UsuarioService usuarioService;
	
	private LazyAuditDataModel listaLog;
	
	private String className;
	
	private Long revisao;
	
	private List<Integer> listaRevisoes;	
	
	private Integer revisaoInicial;
	
	private Integer revisaoFinal;	
	
	private CriteriaLogApp criteriaLogApp;
	
	private Usuario usuarioSelecionado;
	
	private List<String> listaTabelasPorRev;
	
	private String nomeUsuario;
	
	private String cpf;
	
	private Date dataInicial;
	
	private Date dataFinal;
	
	private List<RevisionType> operacoesSelecionadas;
	
	private RevisionType[] listaOperacoes = RevisionType.values();
	
	private Long id;
	
	private Map<String, CriteriaLogApp> mapCriteriaLogApp = new HashMap<String, CriteriaLogApp>();

	@SuppressWarnings("unchecked")
	public PesquisarLogBean() {
		
		this.listaClasses.add(CriteriaLogApp.getInstance(mapCriteriaLogApp, new UsuarioCriteria()));
		
		// Adcionar as classes de log aqui
		
		Collections.sort(this.listaClasses);
	}
	
	@PostConstruct
	public void init() {
		
	}
	
	@URLAction(mappingId="pesquisarLog", onPostback=false)
	public void configurarPesquisaLog() {
		LOGGER.debug("Inicializando PesquisarLogBean");
		setActionUpdate(PERMISSAO_ACTION);
	}
	
	public void limpar(ActionEvent event) {
		setRevisao(null);
		setRevisaoInicial(null);
		setRevisaoFinal(null);
		setClassName(null);
		setCriteriaLogApp(null);
		setListaLog(null);
		setDataInicial(null);
		setDataFinal(null);
		setOperacoesSelecionadas(null);
		setId(null);
	}
    
    public List<Integer> getListaRevisoes(){
    	if(this.listaRevisoes == null) {
    		this.listaRevisoes = logService.listarRevisoes();
    	}
    	return this.listaRevisoes;
    }
    
    public LazyAuditDataModel getListaLog(){
    	return this.listaLog;
    }
    
    public void setListaLog(LazyAuditDataModel listaLog) {
    	this.listaLog = listaLog;
    }

	public List<CriteriaLogApp> getListaClasses() {
		return listaClasses;
	}
	
	private void validar() throws Exception {
		if(revisaoInicial != null && revisaoFinal != null) {
			if(revisaoInicial.compareTo(revisaoFinal) > 0) {
				throw new Exception("O valor da Revisão Inicial não poderá ser maior que o valor da Revisão Final.");
			}
		}
		
		if(dataInicial != null && dataFinal != null) {
			if(dataInicial.compareTo(dataFinal) > 0) {
				throw new Exception("A Data Inicial não poderá ser maior que a Data Final.");
			}
		}
	}
	
	public void pesquisarById(Map<String, String> rec) {		
		setId(Long.valueOf(rec.get("id")));
		doSearch();
	}
	
	public void pesquisarByRevisao(Object rec) throws NumberFormatException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		setRevisaoInicial(Integer.valueOf(BeanUtils.getProperty(rec, "id")));
		doSearch();
	}
	
	public void pesquisar(ActionEvent event) {	
		doSearch(); 
	}

	public boolean isRevisaoSelecionada() {
		return getRevisaoInicial() != null && getRevisaoFinal() == null;
	}
	
	private void doSearch() {
		if(StringUtils.isNotBlank(getClassName())) {
			Class clazz;
			try {
				clazz = Class.forName(className);
				
				validar();
				
				LazyAuditDataModel listaLog = new LazyAuditDataModel(
													this.logService,
													clazz, criteriaLogApp.getCriteria(),
													getRevisaoInicial(), getRevisaoFinal(),
													getDataInicial(), getDataFinal(),
													getOperacoesSelecionadas(),
													getId(),
													getNomeUsuario(),
													getCpf());
				if(getRevisaoInicial() != null) {
					
					setListaTabelasPorRev(this.logService.listarTabelasPorRev(Long.valueOf(getRevisaoInicial())));
				}
				setListaLog(listaLog);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {				
				String textMessage = e.getMessage();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, textMessage, textMessage));
			}
		}
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		setCriteriaLogApp(mapCriteriaLogApp.get(className));
		this.className = className;
	}

	public Long getRevisao() {
		return revisao;
	}

	public void setRevisao(Long revisao) {
		this.revisao = revisao;
	}

	public CriteriaLogApp getCriteriaLogApp() {
		return criteriaLogApp;
	}

	public void setCriteriaLogApp(CriteriaLogApp criteriaLogApp) {
		this.criteriaLogApp = criteriaLogApp;
	}

	public Integer getRevisaoInicial() {
		return revisaoInicial;
	}

	public void setRevisaoInicial(Integer revisaoInicial) {
		this.revisaoInicial = revisaoInicial;
	}

	public Integer getRevisaoFinal() {
		return revisaoFinal;
	}

	public void setRevisaoFinal(Integer revisaoFinal) {
		this.revisaoFinal = revisaoFinal;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public List<String> getListaTabelasPorRev() {
		return listaTabelasPorRev;
	}

	public void setListaTabelasPorRev(List<String> listaTabelasPorRev) {
		this.listaTabelasPorRev = listaTabelasPorRev;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public RevisionType[] getListaOperacoes(){
		
		return this.listaOperacoes;
	}

	public List<RevisionType> getOperacoesSelecionadas() {
		return operacoesSelecionadas;
	}	
	
	public void setOperacoesSelecionadas(List<RevisionType> operacoesSelecionadas) {
		this.operacoesSelecionadas = operacoesSelecionadas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue(Map<String, String> entity, String campo) {
		String value = null;
		value = entity.get(campo);
		return value;
	}
	
	public String getEntityName() {
		if(StringUtils.isNotBlank(getClassName())) {
			int i = getClassName().lastIndexOf(".");
			return " - " +  getClassName().substring(i+1, getClassName().length());
		}
		return "";
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}
