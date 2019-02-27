package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.common.util.ButtonScript;
import br.gov.mt.mti.fiplangrf.common.util.Env;
import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.model.tabelas.Revisao;
import br.gov.mt.mti.fiplangrf.service.security.user.UsuarioService;
import br.gov.mt.mti.fiplangrf.service.tabelas.RevisaoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractManterBean;
import lombok.Getter;
import lombok.Setter;

@Named("manterRevisaoBean")
@ViewScoped
public class ManterRevisaoBean extends AbstractManterBean {

	private static final long serialVersionUID = -248400022019676629L;

	@Inject
	private Logger LOGGER;

	@Getter @Setter
	private Revisao revisao;

	@Inject
	private RevisaoService revisaoService;
	
	@Inject
	private UsuarioService usuarioService;

	public void gerarRevisao() throws BusinessException {
		setRevisao(new Revisao());
		Usuario usuario = usuarioService.findById(Env.getUsuarioLogado().getCodigo());
		
		getRevisao().setUsuario(usuario);
		getRevisao().setEnderecoIP(Env.getUsuarioLogado().getIp());;
		
		revisaoService.merge(getRevisao());
		
		showMainMsgDialog("Revisão cadastrada com sucesso.", ButtonScript.CLOSE_MAIN_MSG_DLG);
		LOGGER.debug("Persistindo Revisao: {}");
	}

	@Override
	public Serializable getId() {
		return null;
	}

}
