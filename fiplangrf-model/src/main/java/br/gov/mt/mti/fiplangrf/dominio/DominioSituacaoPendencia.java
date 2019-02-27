package br.gov.mt.mti.fiplangrf.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import lombok.Getter;
import lombok.ToString;

@ToString(callSuper = false, of = { "desc" })
public enum DominioSituacaoPendencia {

	// @formatter:off
	PENDENTE("PENDENTE", "Pendente"),
	EM_REGULARIZACAO("EM_REGULARIZACAO", "Em Regularização"),
	EM_ANALISE("EM_ANALISE", "Em Análise"),
	SUSPENSA("SUSPENSA", "Suspensa"),
	REGULARIZADA("REGULARIZADA", "Regularizada");
	// @formatter:on

	public static final String NOME = "DominioSituacaoPendencia";
	public static final String METHOD = "getCharCod";

	@Getter
	private String desc;

	@Getter
	private String charCod;

	private DominioSituacaoPendencia(String charCod, String desc) {
		this.charCod = charCod;
		this.desc = desc;
	}

	/**
	 * Número de elementos do dominio
	 * 
	 * @return
	 */
	public static Integer getSize() {
		return values().length;
	}

	public String getName() {
		return this.name();
	}

	public static List<SelectItem> getItems() {
		List<SelectItem> itens = new ArrayList<SelectItem>();
		for (DominioSituacaoPendencia dominio : values()) {
			SelectItem item = new SelectItem(dominio.getName(), dominio.getDesc());
			itens.add(item);
		}
		return itens;
	}
}
