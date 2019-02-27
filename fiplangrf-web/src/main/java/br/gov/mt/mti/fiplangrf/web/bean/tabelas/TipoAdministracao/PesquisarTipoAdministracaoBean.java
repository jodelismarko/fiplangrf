package br.gov.mt.mti.fiplangrf.web.bean.tabelas.TipoAdministracao;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.TipoAdministracaoCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoAdministracao;
import br.gov.mt.mti.fiplangrf.service.tabelas.TipoAdministracaoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarTipoAdministracaoBean")
@ViewScoped
@URLMapping(id = "pesquisarTipoAdministracao", pattern = "/tipoadministracao/pesquisar", viewId = "/pages/tabelas/tipoAdministracao/pesquisarTipoAdministracao.jsf")
public class PesquisarTipoAdministracaoBean extends AbstractPesquisaBean<TipoAdministracaoCriteria> {

	private static final long serialVersionUID = 597066009647328141L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_TIPOADMINISTRACAO_VIEW = "pretty:pesquisarTipoAdministracao";

	public static final String PERMISSAO_PESQUISAR_TIPOADMINISTRACAO= "pesquisar.tipoAdministracao";

	private LazyObjectDataModel<TipoAdministracao> resultadoPesquisa;

	@Inject
	private TipoAdministracaoService tipoAdministracaoService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_TIPOADMINISTRACAO);
		limpar();
	}

	public void limpar() {
		setCriteria(new TipoAdministracaoCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de TipoAdministracao: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<TipoAdministracao>(tipoAdministracaoService, getCriteria());
	}

	public LazyObjectDataModel<TipoAdministracao> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<TipoAdministracao> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
