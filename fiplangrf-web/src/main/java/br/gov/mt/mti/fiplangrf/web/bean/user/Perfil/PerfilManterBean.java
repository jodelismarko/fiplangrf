package br.gov.mt.mti.fiplangrf.web.bean.user.Perfil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.common.util.ButtonScript;
import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;
import br.gov.mt.mti.fiplangrf.model.security.user.Funcionalidade;
import br.gov.mt.mti.fiplangrf.model.security.user.Perfil;
import br.gov.mt.mti.fiplangrf.service.security.user.FuncionalidadeService;
import br.gov.mt.mti.fiplangrf.service.security.user.PerfilService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.common.BeanMessageConstants;
import lombok.Getter;
import lombok.Setter;

@Named("perfilManterBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirPerfil", pattern = "/perfil/incluir", viewId = "/pages/user/perfil/perfilManter.jsf"),
		@URLMapping(id = "alterarPerfil", pattern = "/perfil/alterar/#{ id : perfilManterBean.id}", viewId = "/pages/user/perfil/perfilManter.jsf"),
		@URLMapping(id = "visualizarPerfil", pattern = "/perfil/visualizar/#{ id : perfilManterBean.id}", viewId = "/pages/user/perfil/perfilManter.jsf"),
		@URLMapping(id = "excluirPerfil", pattern = "/perfil/excluir/#{ id : perfilManterBean.id}", viewId = "/pages/user/perfil/perfilManter.jsf") })
public class PerfilManterBean extends AbstractManterBean {

	private static final long serialVersionUID = -184723293394267565L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_PERFIL_VIEW = "pretty:alterarPerfil";

	public static final String VISUALIZAR_PERFIL_VIEW = "pretty:visualizarPerfil";

	public static final String EXCLUIR_PERFIL_VIEW = "pretty:excluirPerfil";

	public static final String PERMISSAO_INCLUIR_PERFIL = "incluir.perfil";

	public static final String PERMISSAO_ALTERAR_PERFIL = "alterar.perfil";

	public static final String PERMISSAO_EXCLUIR_PERFIL = "excluir.perfil";

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private Perfil perfil;

	@Inject
	private PerfilService perfilService;

	@Inject
	private FuncionalidadeService funcionalidadeService;
	
	@Getter @Setter
	private DualListModel<Funcionalidade> funcionalidadesPickList = new DualListModel<Funcionalidade>();
	
	@Getter @Setter
	private List<Funcionalidade> funcionalidadeNaoAssociadoList = new ArrayList<Funcionalidade>();
	
	@Getter @Setter
	private List<Funcionalidade> funcionalidadeAssociadoList = new ArrayList<Funcionalidade>();

	@PostConstruct
	public void inicializar() {
		limpar();
	}

	public void limpar() {
		perfil = new Perfil();
		carregarFuncionalidades();
	}

	public void carregar() {
		LOGGER.debug("Carregando Perfil: {}", getId());
		perfil = perfilService.loadById(getId());
		carregarFuncionalidades();
	}

	@URLAction(mappingId = "alterarPerfil", onPostback = false)
	public void configurarModoAlteracao() {
		setAction(PERMISSAO_ALTERAR_PERFIL);
		super.setModoAlteracao();
		carregar();
	}

	@URLAction(mappingId = "incluirPerfil", onPostback = false)
	public void configurarModoInclusao() {
		setAction(PERMISSAO_INCLUIR_PERFIL);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "visualizarPerfil", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirPerfil", onPostback = false)
	public void configurarModoExclusao() {
		setAction(PERMISSAO_EXCLUIR_PERFIL);
		super.setModoExclusao();
		carregar();

	}

	public void salvar() throws BusinessException {

		boolean inclusao = perfil.getId() == null;

		LOGGER.debug("Persistindo Perfil: {}", perfil);
		
		if(funcionalidadeAssociadoList.isEmpty()){
			showMainErrorMsgDialog("É obrigatório informar pelo menos uma Permissão.", ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}
		
		try {
			
			perfil.setFuncionalidades(new HashSet<Funcionalidade>(funcionalidadeAssociadoList));
			perfil = perfilService.checkAndSave(perfil);
			if (inclusao) {
				showMainMsgDialog(DominioMensagem.MSG_INCLUIR_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			} else {
				showMainMsgDialog(DominioMensagem.MSG_ALTERADA_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			}
		} catch (BusinessException e) {
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}

		setId(perfil.getId());
		setModoVisualizacao();
	}

	public void excluir() {
		LOGGER.debug("Excluindo Perfil: {}", getId());
		perfilService.delete(getId());
		showMainMsgDialog(BeanMessageConstants.MSG_EXCLUIR_SUCESSO, ButtonScript.ATUALIZAR_PESQUISA);
	}

	public void carregarFuncionalidades() {
		funcionalidadeNaoAssociadoList = funcionalidadeService.findAllNotInList(new ArrayList<Funcionalidade>(perfil.getFuncionalidades()));
		funcionalidadeAssociadoList = new ArrayList<Funcionalidade>(perfil.getFuncionalidades());
		Collections.sort(funcionalidadeNaoAssociadoList); 
		Collections.sort(funcionalidadeAssociadoList); 
		funcionalidadesPickList = new DualListModel<Funcionalidade>(funcionalidadeNaoAssociadoList, funcionalidadeAssociadoList);
	}

	@SuppressWarnings("unchecked")
	public void onTransferFuncionalidade(TransferEvent event) {
		if (event.isAdd()) {
			funcionalidadeAssociadoList.addAll((List<Funcionalidade>) event.getItems());
		} else {
			funcionalidadeAssociadoList.removeAll((List<Funcionalidade>) event.getItems());
		}
	}
}
