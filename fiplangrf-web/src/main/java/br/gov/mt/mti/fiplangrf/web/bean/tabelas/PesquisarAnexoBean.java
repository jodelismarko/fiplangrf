package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.AnexoCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.Anexo;
import br.gov.mt.mti.fiplangrf.service.tabelas.AnexoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarAnexoBean")
@ViewScoped
@URLMapping(id = "pesquisarAnexo", pattern = "/anexo/pesquisar", viewId = "/pages/tabelas/anexo/pesquisarAnexo.jsf")
public class PesquisarAnexoBean extends AbstractPesquisaBean<AnexoCriteria> {

	private static final long serialVersionUID = -724289104709095281L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_ANEXO_VIEW = "pretty:pesquisarAnexo";

	public static final String PERMISSAO_PESQUISAR_ANEXO= "pesquisar.anexo";

	private LazyObjectDataModel<Anexo> resultadoPesquisa;

	@Inject
	private AnexoService anexoService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_ANEXO);
		limpar();
	}

	public void limpar() {
		setCriteria(new AnexoCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de Anexo: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<Anexo>(anexoService, getCriteria());
	}

	public LazyObjectDataModel<Anexo> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<Anexo> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
