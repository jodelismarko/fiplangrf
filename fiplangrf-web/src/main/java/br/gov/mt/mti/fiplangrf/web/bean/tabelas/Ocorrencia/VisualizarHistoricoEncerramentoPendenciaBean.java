package br.gov.mt.mti.fiplangrf.web.bean.tabelas.Ocorrencia;

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
import br.gov.mt.mti.fiplangrf.model.tabelas.EncerramentoPendencia;
import br.gov.mt.mti.fiplangrf.web.bean.auditoria.HistoricoEnversBean;
import lombok.Getter;
import lombok.Setter;

@Named("visualizarHistoricoEncerramentoPendenciaBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "visualizarHistoricoEncerramentoPendencia", pattern = "/encerramentoPendencia/visualizarHistorico/#{id: visualizarHistoricoEncerramentoPendenciaBean.id}", viewId = "/pages/tabelas/ocorrencia/visualizarHistoricoEncerramentoPendencia.jsf") })
public class VisualizarHistoricoEncerramentoPendenciaBean extends HistoricoEnversBean<EncerramentoPendencia,Long> {

    private static final long serialVersionUID = 997893526221509035L;

    private static final Logger LOGGER = LoggerFactory.getLogger(VisualizarHistoricoEncerramentoPendenciaBean.class);

	public static final String HISTORICO_VIEW = "pretty:visualizarHistoricoEncerramentoPendencia";
	 
	private CustomRevisionEntityCriteria criteria;	

	@Setter
	private LazyObjectDataModel<CustomRevisionEntity> resultadoPesquisa;

	@Getter @Setter
    private Long id;

	public String visualizar() {
		return null;
	}
	
	@Override
	protected Class<EncerramentoPendencia> getType() {
		return EncerramentoPendencia.class;
	}
	
	@PostConstruct
	public void initialize() {
		limpar();
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de visualizarHistoricoEncerramento Pendencia: {}", criteria);
		super.pesquisar(event);
	}

	public void limpar() {
		criteria = new CustomRevisionEntityCriteria();
		resultadoPesquisa = null;
	}
	
}
