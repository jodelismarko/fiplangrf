package br.gov.mt.mti.fiplangrf.web.bean.tabelas.UnidadeAdministrativa;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.UnidadeAdministrativaCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.UnidadeAdministrativa;
import br.gov.mt.mti.fiplangrf.service.tabelas.UnidadeAdministrativaService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarUnidadeAdministrativaBean")
@ViewScoped
@URLMapping(id = "pesquisarUnidadeAdministrativa", pattern = "/unidadeadministrativa/pesquisar", viewId = "/pages/tabelas/unidadeAdministrativa/pesquisarUnidadeAdministrativa.jsf")
public class PesquisarUnidadeAdministrativaBean extends AbstractPesquisaBean<UnidadeAdministrativaCriteria> {

	private static final long serialVersionUID = -657425828001205940L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_UNIDADEADMINISTRATIVA_VIEW = "pretty:pesquisarUnidadeAdministrativa";

	public static final String PERMISSAO_PESQUISAR_UNIDADEADMINISTRATIVA= "pesquisar.unidadeAdministrativa";

	private LazyObjectDataModel<UnidadeAdministrativa> resultadoPesquisa;

	@Inject
	private UnidadeAdministrativaService unidadeAdministrativaService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_UNIDADEADMINISTRATIVA);
		limpar();
	}

	public void limpar() {
		setCriteria(new UnidadeAdministrativaCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de UnidadeAdministrativa: {}", getCriteria());
		
		/*FilterField field = new FilterField();
		field.setField("credorPrincipal");
		field.setExpression(Expression.EQUAL);		
		field.setValue(DominioSimNao.SIM);
		field.setType(FieldType.ENUM);
		criteria.addParam(field);*/
		
		resultadoPesquisa = new LazyObjectDataModel<UnidadeAdministrativa>(unidadeAdministrativaService, getCriteria());
	}

	public LazyObjectDataModel<UnidadeAdministrativa> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<UnidadeAdministrativa> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
