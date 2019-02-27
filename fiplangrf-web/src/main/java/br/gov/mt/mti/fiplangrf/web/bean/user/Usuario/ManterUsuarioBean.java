package br.gov.mt.mti.fiplangrf.web.bean.user.Usuario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.gov.mt.cepromat.ceprofw.common.util.CriptografiaUtil;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.common.util.ButtonScript;
import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;
import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUsuario;
import br.gov.mt.mti.fiplangrf.model.security.user.Perfil;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.service.security.user.PerfilService;
import br.gov.mt.mti.fiplangrf.service.security.user.UsuarioService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import br.gov.mt.mti.fiplangrf.web.bean.util.ImageUploadBean;
import lombok.Getter;
import lombok.Setter;

@Named("manterUsuarioBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "incluirUsuario", pattern = "/usuario/incluir", viewId = "/pages/user/usuario/manterUsuario.jsf"),
		@URLMapping(id = "alterarUsuario", pattern = "/usuario/alterar/#{ id : manterUsuarioBean.idCriptogradado}", viewId = "/pages/user/usuario/manterUsuario.jsf"),
		@URLMapping(id = "visualizarUsuarioAlterado", pattern = "/usuario/alterar/visualizar/#{ id : manterUsuarioBean.idCriptogradado}", viewId = "/pages/user/usuario/manterUsuario.jsf"),
		@URLMapping(id = "visualizarUsuario", pattern = "/usuario/visualizar/#{ id : manterUsuarioBean.idCriptogradado}", viewId = "/pages/user/usuario/manterUsuario.jsf"),
		@URLMapping(id = "excluirUsuario", pattern = "/usuario/excluir/#{ id : manterUsuarioBean.idCriptogradado}", viewId = "/pages/user/usuario/manterUsuario.jsf") })
public class ManterUsuarioBean extends AbstractManterBean {

	private static final long serialVersionUID = -17032142010817024L;

	@Inject
	private Logger LOGGER;

	public static final String ALTERAR_USUARIO_VIEW = "pretty:alterarUsuario";

	public static final String VISUALIZAR_USUARIO_VIEW = "pretty:visualizarUsuario";

	public static final String VISUALIZAR_USUARIO_ALTERADO_VIEW = "pretty:visualizarUsuarioAlterado";

	public static final String EXCLUIR_USUARIO_VIEW = "excluir.usuario";

	public static final String ALTERAR_USUARIO = "alterar.usuario";

	public static final String INCLUIR_USUARIO = "incluir.usuario";

	public static final String PESQUISAR_USUARIO = "pesquisar.usuario";

	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private Usuario usuario;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	@Getter
	@Setter
	private ImageUploadBean imageUploadBean;
	
	//Perfil
    @Inject
    private PerfilService perfilService;
	
    @Getter @Setter
    private DualListModel<Perfil> perfilPickList = new DualListModel<Perfil>();    
    
    @Getter @Setter
    private List<Perfil> perfilNaoAssociadoList = new ArrayList<Perfil>();
    
    @Getter @Setter
    private List<Perfil> perfilAssociadoList = new ArrayList<Perfil>(); 

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void inicializar() {
		if (getSessionBuffer().get(getBufferKey()) == null) {
			getSessionBuffer().put(getBufferKey(), new HashSet<Perfil>());
		}
		limpar();
	}

	public void limpar() {
		usuario = new Usuario();
		getImageUploadBean().clear();
		carregarListaPerfil();
	}

	public void carregarListaPerfil(){
		perfilNaoAssociadoList = perfilService.findAllNotInList(new ArrayList<Perfil>(usuario.getPerfis()));
		perfilAssociadoList = new ArrayList<>(usuario.getPerfis());
		perfilPickList = new DualListModel<>(perfilNaoAssociadoList, perfilAssociadoList);
	}
	
	public void carregar() {

		LOGGER.debug("Carregando Usuario: {}", getId());
		usuario = usuarioService.findByIdFetchAll(getId());
		
		if (usuario.getFoto() != null) {
			getImageUploadBean().setBytes(usuario.getFoto());
		}
		
		carregarListaPerfil();
	}

	@URLAction(mappingId = "alterarUsuario", onPostback = false)
	public void configurarModoAlteracao() {
		super.setModoAlteracao();
		setAction(ALTERAR_USUARIO);
		carregar();
	}

	@URLAction(mappingId = "incluirUsuario", onPostback = false)
	public void configurarModoInclusao() {
		setAction(INCLUIR_USUARIO);
		super.setModoInclusao();
	}

	@URLAction(mappingId = "visualizarUsuarioAlterado", onPostback = false)
	public void configurarModoVisualizacaoAlterar() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "visualizarUsuario", onPostback = false)
	public void configurarModoVisualizacao() {
		super.setModoVisualizacao();
		carregar();
	}

	@URLAction(mappingId = "excluirUsuario", onPostback = false)
	public void configurarModoExclusao() {
		setAction(EXCLUIR_USUARIO_VIEW);
		super.setModoExclusao();
		carregar();
	}

	public void salvar() throws BusinessException {

		boolean inclusao = usuario.getId() == null;

		if (getImageUploadBean().getBytes() != null) {
			usuario.setFoto(getImageUploadBean().getBytes());
		}

		LOGGER.debug("Persistindo Usuario: {}", usuario);
		
		if(perfilAssociadoList.isEmpty()){
			showMainErrorMsgDialog("É obrigatório informar pelo menos um Perfil de Acesso.", ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}
		
		try {
			usuario.setPerfis(new HashSet<Perfil>(perfilAssociadoList) );

			if (inclusao) {
				usuario = usuarioService.incluir(usuario);
				showMainMsgDialog(DominioMensagem.MSG_INCLUIR_SUCESSO.getDesc(), ButtonScript.CLOSE_MAIN_MSG_DLG);
			} else {
				usuario = usuarioService.updateUsuario(usuario);
				showMainMsgDialog(DominioMensagem.MSG_ALTERADA_SUCESSO.getDesc(),
						ButtonScript.SALVAR_ATUALIZAR_PESQUISA);
			}
		} catch (Exception e) {
			showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_ERROR, ButtonScript.CLOSE_MAIN_MSG_DLG);
			return;
		}

		setId(usuario.getId());
		setModoVisualizacao();
	}

	public void carregarUsuarioFIPLANByCPF() throws BusinessException {

		if (usuario.getCpf() != null) {
			usuario.setCpf(usuario.getCpf().replaceAll("\\D", ""));
			usuario.setLogin(usuario.getCpf());
		}

		if (usuario.getLogin() != null) {
			FIPLANUsuario usu = new FIPLANUsuario();

			try {
				usu = usuarioService.carregarUsuarioFIPLANByCPF(usuario.getLogin());
			} catch (BusinessException e) {
				showMainMsgDialog(e.getMessage(), FacesMessage.SEVERITY_WARN, ButtonScript.CLOSE_MAIN_MSG_DLG);
				setUsuario(new Usuario());
				return;
			}

			if (usuarioService.isObjectCadastradoPorAtributo(usuario, "login")) {
				showMainMsgDialog(DominioMensagem.MSG_CPF_JA_CADASTRADO.getDesc(), FacesMessage.SEVERITY_WARN,
						ButtonScript.CLOSE_MAIN_MSG_DLG);
				setUsuario(new Usuario());
				return;
			}

			usuario.setFiplanUsuario(usu);
		}
	}

	public void updatePerfis() {
		@SuppressWarnings("unchecked")
		Set<Perfil> perfisSelecionados = (Set<Perfil>) getSessionBuffer().remove(getBufferKey());
		if (perfisSelecionados != null) {

			if (getUsuario() == null) {
				setUsuario(new Usuario());
			}
			if (getUsuario().getPerfis() == null) {
				getUsuario().setPerfis((new HashSet<Perfil>()));
			}

			getUsuario().getPerfis().addAll(perfisSelecionados);
		}

	}

	public void excluirPerfil(Perfil perfil) {
		getUsuario().getPerfis().remove(perfil);
	}
	
	@SuppressWarnings("unchecked")
	public void onTransferPerfil(TransferEvent event){	    
		if(event.isAdd()){
			perfilAssociadoList.addAll((List<Perfil>)event.getItems());
		}else{
			perfilAssociadoList.removeAll((List<Perfil>)event.getItems());
		}
	}

	public void excluir() {
		LOGGER.debug("Excluindo Usuario: {}", id);
		usuarioService.delete(id);
		showMainMsgDialog(DominioMensagem.MSG_EXCLUIR_SUCESSO.getDesc(), ButtonScript.ATUALIZAR_PESQUISA);
	}

	public void removerFoto(ActionEvent event) {
		this.getImageUploadBean().clear();
		usuario.setFoto(null);
	}

	public String getIdCriptogradado() {
		return CriptografiaUtil.encripta(id.toString());
	}

	public void setIdCriptogradado(String id) {
		this.id = CriptografiaUtil.decripta(id);
	}
}
