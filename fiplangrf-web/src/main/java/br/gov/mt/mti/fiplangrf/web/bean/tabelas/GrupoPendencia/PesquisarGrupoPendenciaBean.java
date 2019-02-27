package br.gov.mt.mti.fiplangrf.web.bean.tabelas.GrupoPendencia;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.GrupoPendenciaCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.GrupoPendencia;
import br.gov.mt.mti.fiplangrf.service.tabelas.GrupoPendenciaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;
import lombok.Getter;
import lombok.Setter;

@Named("pesquisarGrupoPendenciaBean")
@ViewScoped
@URLMapping(id = "pesquisarGrupoPendencia", pattern = "/grupopendencia/pesquisar", viewId = "/pages/tabelas/grupoPendencia/pesquisarGrupoPendencia.jsf")
public class PesquisarGrupoPendenciaBean extends AbstractPesquisaBean<GrupoPendenciaCriteria> {

	private static final long serialVersionUID = -658941817844350387L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_GRUPOPENDENCIA_VIEW = "pretty:pesquisarGrupoPendencia";

	public static final String PERMISSAO_PESQUISAR_GRUPOPENDENCIA= "pesquisar.grupoPendencia";

	@Getter @Setter
	private LazyObjectDataModel<GrupoPendencia> resultadoPesquisa;

	@Inject
	private GrupoPendenciaService grupoPendenciaService;
	
	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_GRUPOPENDENCIA);
		limpar();
	}

	public void limpar() {
		setCriteria(new GrupoPendenciaCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de GrupoPendencia: {}", getCriteria());
		getCriteria().setSortOrder("desc");
		resultadoPesquisa = new LazyObjectDataModel<GrupoPendencia>(grupoPendenciaService, getCriteria());
	}
}
