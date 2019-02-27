package br.gov.mt.mti.fiplangrf.web.bean.relatorios;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.common.util.Constantes;
import br.gov.mt.mti.fiplangrf.criteria.user.UsuarioCriteria;

@Named("relatorioExemploBean")
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "relatorioExemploBean", pattern = "/relatorioExemploBean/relatorio", viewId = "/pages/relatorios/relatorioExemploBean/relatorioExemploBean.jsf"),
		@URLMapping(id = "exibirRelatorioExemploBean", pattern = "/relatorioExemploBean/" + Constantes.RELATORIO_EXIBIR_URL , viewId = "/pages/exibirRelatorio.jsf") })
public class RelatorioExemploBean extends AbstractRelatorioBean<UsuarioCriteria> {

	private static final long serialVersionUID = 5627574910684059462L;

	public static final String RELATORIO_CERTCANCELADOSPORPERIODO_VIEW = "pretty:relatorioExemploBean";

	public static final String PERMISSAO_RELATORIO_CERTCANCELADOSPORPERIODO = "relatorio.relatorioExemploBean";

	private final String RELATORIO_CERTCANCELADOSPORPERIODO_CRITERIA_KEY = "RelatorioExemploBean";

	public RelatorioExemploBean() {
		setCriteria(new UsuarioCriteria());
	}
	
	@PostConstruct
	public void initialize() {
		setAction(PERMISSAO_RELATORIO_CERTCANCELADOSPORPERIODO);
		limpar();
	}

	
	@URLAction(mappingId = "exibirRelatorioExemploBean", onPostback = false)
	public void carregarRelatorio() throws BusinessException {
		super.carregarRelatorio();
	}

	public void limpar() {
		resetarParametros();
	}

	@Override
	public String getKey() {
		return RELATORIO_CERTCANCELADOSPORPERIODO_CRITERIA_KEY;
	}

	@Override
	public String getReportPath() {
		return "/relatorios/relatorioExemploBean/relatorioExemploBean.jasper";
	}

	@Override
	public String getPopUpPath() {
		return "/relatorioExemploBean/relatorioexibir";
	}

}
