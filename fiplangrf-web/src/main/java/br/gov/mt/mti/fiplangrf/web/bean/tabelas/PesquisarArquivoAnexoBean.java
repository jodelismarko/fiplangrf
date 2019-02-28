package br.gov.mt.mti.fiplangrf.web.bean.tabelas;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import br.gov.mt.cepromat.ceprofw.web.common.LazyObjectDataModel;
import br.gov.mt.mti.fiplangrf.criteria.tabelas.ArquivoAnexoCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.ArquivoAnexo;
import br.gov.mt.mti.fiplangrf.service.tabelas.ArquivoAnexoService;
import br.gov.mt.mti.fiplangrf.web.bean.base.AbstractPesquisaBean;

@Named("pesquisarArquivoAnexoBean")
@ViewScoped
@URLMapping(id = "pesquisarArquivoAnexo", pattern = "/arquivoanexo/pesquisar", viewId = "/pages/tabelas/arquivoAnexo/pesquisarArquivoAnexo.jsf")
public class PesquisarArquivoAnexoBean extends AbstractPesquisaBean<ArquivoAnexoCriteria> {

	private static final long serialVersionUID = -722032750408441667L;

	@Inject
	private Logger LOGGER;

	public static final String PESQUISAR_ARQUIVOANEXO_VIEW = "pretty:pesquisarArquivoAnexo";

	public static final String PERMISSAO_PESQUISAR_ARQUIVOANEXO= "pesquisar.arquivoAnexo";

	private LazyObjectDataModel<ArquivoAnexo> resultadoPesquisa;

	@Inject
	private ArquivoAnexoService arquivoAnexoService;

	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_PESQUISAR_ARQUIVOANEXO);
		limpar();
	}

	public void limpar() {
		setCriteria(new ArquivoAnexoCriteria());
		resultadoPesquisa = null;
	}

	public void pesquisar(ActionEvent event) {
		LOGGER.debug("Pesquisando registros de ArquivoAnexo: {}", getCriteria());
		resultadoPesquisa = new LazyObjectDataModel<ArquivoAnexo>(arquivoAnexoService, getCriteria());
	}

	public LazyObjectDataModel<ArquivoAnexo> getResultadoPesquisa() {
		return resultadoPesquisa;
	}

	public void setResultadoPesquisa(LazyObjectDataModel<ArquivoAnexo> resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

}
