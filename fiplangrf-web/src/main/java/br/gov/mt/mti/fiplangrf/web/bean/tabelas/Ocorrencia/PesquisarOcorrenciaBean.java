package br.gov.mt.mti.fiplangrf.web.bean.tabelas.Ocorrencia;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.OcorrenciaCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.Ocorrencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.Revisao;
import br.gov.mt.mti.fiplangrf.service.tabelas.OcorrenciaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.RevisaoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;
import lombok.Getter;
import lombok.Setter;

@Named("pesquisarOcorrenciaBean")
@ViewScoped
@URLMapping(id = "pesquisarOcorrencia", pattern = "/ocorrencia/pesquisar", viewId = "/pages/tabelas/ocorrencia/pesquisarOcorrencia.jsf")
public class PesquisarOcorrenciaBean extends AbstractPesquisaBean<OcorrenciaCriteria> {

	private static final long serialVersionUID = -371854781026970351L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_OCORRENCIA_VIEW = "pretty:pesquisarOcorrencia";

	public static final String PERMISSAO_PESQUISAR_OCORRENCIA= "pesquisar.ocorrencia";

	@Getter @Setter
	Revisao revisao;
	
	private LazyObjectDataModel<Ocorrencia> resultadoPesquisa;

	@Inject
	private OcorrenciaService ocorrenciaService;
	
	@Inject RevisaoService revisaoService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_OCORRENCIA);
		limpar();
	}

	public void limpar() {
		setCriteria(new OcorrenciaCriteria());
		setRevisao(new Revisao());
		revisao = revisaoService.findUltimaRevisao();
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de Ocorrencia: {}", getCriteria());
		getCriteria().setSortField("id" );
		getCriteria().setSortOrder("ASC");
		resultadoPesquisa = new LazyObjectDataModel<Ocorrencia>(ocorrenciaService, getCriteria());
	}

	public LazyObjectDataModel<Ocorrencia> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<Ocorrencia> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
