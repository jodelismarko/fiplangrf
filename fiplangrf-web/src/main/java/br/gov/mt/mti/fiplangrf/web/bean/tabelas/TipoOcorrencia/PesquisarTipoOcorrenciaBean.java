package br.gov.mt.mti.fiplangrf.web.bean.tabelas.TipoOcorrencia;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.TipoOcorrenciaCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoOcorrencia;
import br.gov.mt.mti.fiplangrf.service.tabelas.TipoOcorrenciaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;
import lombok.Getter;
import lombok.Setter;

@Named("pesquisarTipoOcorrenciaBean")
@ViewScoped
@URLMapping(id = "pesquisarTipoOcorrencia", pattern = "/tipoocorrencia/pesquisar", viewId = "/pages/tabelas/tipoOcorrencia/pesquisarTipoOcorrencia.jsf")
public class PesquisarTipoOcorrenciaBean extends AbstractPesquisaBean<TipoOcorrenciaCriteria> {

	private static final long serialVersionUID = -82654871551977867L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_TIPOOCORRENCIA_VIEW = "pretty:pesquisarTipoOcorrencia";

	public static final String PERMISSAO_PESQUISAR_TIPOOCORRENCIA= "pesquisar.tipoOcorrencia";

	@Getter @Setter
	private LazyObjectDataModel<TipoOcorrencia> resultadoPesquisa;

	@Inject
	private TipoOcorrenciaService tipoOcorrenciaService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_TIPOOCORRENCIA);
		limpar();
	}

	public void limpar() {
		setCriteria(new TipoOcorrenciaCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de TipoOcorrencia: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<TipoOcorrencia>(tipoOcorrenciaService, getCriteria());
	}
	
	

}
