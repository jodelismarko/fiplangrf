package br.gov.mt.mti.fiplangrf.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public enum DominioTipoPoder {

	// @formatter:off
	PODER_EXECUTIVO("PODER_EXECUTIVO", "Poder Executivo"),
	PODER_LEGISLATIVO("PODER_LEGISLATIVO", "Poder Legislativo"),
	PODER_JUDICIARIO("PODER_JUDICIARIO", "Poder Judiciário"),
	ORGAO_AUTONOMO("ORGAO_AUTONOMO", "Órgão Autônomo"); //Exemplo: SAGCSAGEF
	// @formatter:on

	public static final String NOME = "DominioTipoPoder";
	public static final String METHOD = "getCharCod";

	private String desc;
	private String charCod;

	private DominioTipoPoder(String charCod, String desc) {
		this.charCod = charCod;
		this.desc = desc;
	}

	public String getCharCod() {
		return charCod;
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

	public String getDesc() {
		return this.desc;
	}

	public String toString() {
		return this.getDesc();
	}

	public static List<SelectItem> getItems() {
		List<SelectItem> itens = new ArrayList<SelectItem>();
		for (DominioTipoPoder dominio : values()) {
			SelectItem item = new SelectItem(dominio.getName(), dominio.getDesc());
			itens.add(item);
		}
		return itens;
	}
}
