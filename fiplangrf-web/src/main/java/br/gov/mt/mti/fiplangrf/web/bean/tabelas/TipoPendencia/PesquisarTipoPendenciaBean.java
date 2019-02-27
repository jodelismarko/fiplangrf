package br.gov.mt.mti.fiplangrf.web.bean.tabelas.TipoPendencia;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.TipoPendenciaCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoPendencia;
import br.gov.mt.mti.fiplangrf.service.tabelas.TipoPendenciaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarTipoPendenciaBean")
@ViewScoped
@URLMapping(id = "pesquisarTipoPendencia", pattern = "/tipopendencia/pesquisar", viewId = "/pages/tabelas/tipoPendencia/pesquisarTipoPendencia.jsf")
public class PesquisarTipoPendenciaBean extends AbstractPesquisaBean<TipoPendenciaCriteria> {

	private static final long serialVersionUID = -68951753063687629L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_TIPOPENDENCIA_VIEW = "pretty:pesquisarTipoPendencia";

	public static final String PERMISSAO_PESQUISAR_TIPOPENDENCIA= "pesquisar.tipoPendencia";

	private LazyObjectDataModel<TipoPendencia> resultadoPesquisa;

	@Inject
	private TipoPendenciaService tipoPendenciaService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_TIPOPENDENCIA);
		limpar();
	}

	public void limpar() {
		setCriteria(new TipoPendenciaCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de TipoPendencia: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<TipoPendencia>(tipoPendenciaService, getCriteria());
	}

	public LazyObjectDataModel<TipoPendencia> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<TipoPendencia> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}
}
