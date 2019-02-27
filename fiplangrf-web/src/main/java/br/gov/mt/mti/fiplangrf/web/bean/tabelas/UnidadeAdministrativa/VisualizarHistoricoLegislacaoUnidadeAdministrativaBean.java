package br.gov.mt.mti.fiplangrf.web.bean.tabelas.UnidadeAdministrativa;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.auditoria.CustomRevisionEntityCriteria;
import br.gov.mt.mti.fiplangrf.model.auditoria.CustomRevisionEntity;
import br.gov.mt.mti.fiplangrf.model.tabelas.LegislacaoUnidadeAdministrativa;
import br.gov.mt.mti.fiplangrf.web.bean.auditoria.HistoricoEnversBean;
import lombok.Getter;
import lombok.Setter;

@Named("visualizarHistoricoLegislacaoUnidadeAdministrativaBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "visualizarHistoricoLegislacaoUnidadeAdministrativa", pattern = "/legislacaounidadeadministrativa/visualizarHistorico/#{id: visualizarHistoricoLegislacaoUnidadeAdministrativaBean.id}", viewId = "/pages/tabelas/unidadeAdministrativa/visualizarHistoricoLegislacaoUnidadeAdministrativa.jsf") })
public class VisualizarHistoricoLegislacaoUnidadeAdministrativaBean extends HistoricoEnversBean<LegislacaoUnidadeAdministrativa,Long> {

    private static final long serialVersionUID = 997893526221509035L;

    private static final Logger LOGGER = LoggerFactory.getLogger(VisualizarHistoricoLegislacaoUnidadeAdministrativaBean.class);

	public static final String HISTORICO_VIEW = "pretty:visualizarHistoricoLegislacaoUnidadeAdministrativa";
	 
	private CustomRevisionEntityCriteria criteria;	

	@Setter
	private LazyObjectDataModel<CustomRevisionEntity> resultadoPesquisa;

	@Getter @Setter
    private Long id;

	public String visualizar() {
		return null;
	}
	
	@Override
	protected Class<LegislacaoUnidadeAdministrativa> getType() {
		return LegislacaoUnidadeAdministrativa.class;
	}
	
	@PostConstruct
	public void initialize() {
		limpar();
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de Legislação de Unidade Administrativa: {}", criteria);
		super.pesquisar(event);
	}

	public void limpar() {
		criteria = new CustomRevisionEntityCriteria();
		resultadoPesquisa = null;
	}
	
}
