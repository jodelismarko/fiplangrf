package br.gov.mt.mti.fiplangrf.web.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.criterion.Order;

import br.gov.mt.cepromat.ceprofw.web.support.SelectItemUtil;
import br.gov.mt.mti.fiplangrf.model.tabelas.GrupoPendencia;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoOcorrencia;
import br.gov.mt.mti.fiplangrf.service.tabelas.GrupoPendenciaService;
import br.gov.mt.mti.fiplangrf.service.tabelas.TipoOcorrenciaService;

@Named("combosListaHelperBean")
@ApplicationScoped
public class CombosListaHelperBean implements Serializable {

	private static final long serialVersionUID = -8878077686899405160L;

	@Inject private TipoOcorrenciaService tipoOcorrenciaService;
	
	@Inject private GrupoPendenciaService grupoPendenciaService;

	
	/**
	 * Exemplo - Método que retorna número de 1 a 10.
	 * @return
	 */
	public List<Integer> listarNumerosUmADez() {
		List<Integer> lista = new ArrayList<Integer>();
		
		for(int i = 1; i<=10; i++) {
			lista.add(i);
		}
		
		return lista;
	}
	
	public List<SelectItem> listarTipoOcorrencia(){
		List<TipoOcorrencia> tipoOcorrenciaList = tipoOcorrenciaService.findAllCombo(Order.asc("descricao"));
		return SelectItemUtil.toSelectItems(tipoOcorrenciaList, "id", "descricao");
	}
	
	public List<SelectItem> listarGrupoPendencia(){
		List<GrupoPendencia> tipoGrupoPendenciaList = grupoPendenciaService.findAllCombo(Order.asc("descricao"));
		return SelectItemUtil.toSelectItems(tipoGrupoPendenciaList, "id", "descricao");
	}

}
