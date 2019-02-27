package br.gov.mt.mti.fiplangrf.web.bean.user.Usuario;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.criterion.Order;
import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterField;
import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.cepromat.ceprofw.web.support.SelectItemUtil;
import br.gov.mt.mti.fiplangrf.common.util.Sanityzer;
import br.gov.mt.mti.fiplangrf.criteria.user.UsuarioCriteria;
import br.gov.mt.mti.fiplangrf.model.security.user.Perfil;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.service.security.user.PerfilService;
import br.gov.mt.mti.fiplangrf.service.security.user.UsuarioService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named
@ViewScoped
@URLMapping(id = "pesquisarUsuario", pattern = "/usuario/pesquisar", viewId = "/pages/user/usuario/pesquisarUsuario.jsf")
public class PesquisarUsuarioBean extends AbstractPesquisaBean<UsuarioCriteria> {

    private static final long serialVersionUID = -375449078685569365L;

    @Inject
    private Logger LOGGER;

	public static final String PESQUISAR_USUARIO_VIEW = "pretty:pesquisarUsuario";
	
	public static final String PERMISSAO_PESQUISAR_USUARIO = "pesquisar.usuario";

	private LazyObjectDataModel<Usuario> resultadoPesquisa;

	@Inject
	private UsuarioService usuarioService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_USUARIO);
		limpar();
	}

	public void pesquisar(ActionEvent event) {

		LOGGER.debug("Pesquisando registros de Usuario: {}", getCriteria());
		
		FilterField cpfField = getCriteria().getModel().getFieldByName("login");
        if(getCriteria().getModel().getSelectedFields().contains(cpfField)) {
            int index = getCriteria().getModel().getSelectedFields().indexOf(cpfField);
            String value = (String) getCriteria().getModel().getSelectedFields().get(index).getValue();
            getCriteria().getModel().getSelectedFields().get(index).setValue(Sanityzer.sanityzeNumericString(value));
        }
		
		resultadoPesquisa = new LazyObjectDataModel<Usuario>(usuarioService, getCriteria());
	}

	public void limpar() {

		setCriteria(new UsuarioCriteria());
		resultadoPesquisa = null;

	}

	public LazyObjectDataModel<Usuario> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<Usuario> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

	@Inject
	PerfilService perfilService;
	
	public List<SelectItem> listarPerfils(){
		List<Perfil> listaPerfils = perfilService.findAll(Order.asc("descricao"));
		return SelectItemUtil.toSelectItems(listaPerfils, "id", "descricao");
	}
}
