package br.gov.mt.mti.fiplangrf.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public enum DominioTipoPermissao {

	// @formatter:off
	GATILHO("GATILHO", "Gatilho"),
	ACAO("ACAO", "Ação"),
	PAPEL("PAPEL", "Papel"); //Exemplo: SAGCSAGEF
	// @formatter:on

	public static final String NOME = "DominioTipoPermissao";
	public static final String METHOD = "getCharCod";

	private String desc;
	private String charCod;

	private DominioTipoPermissao(String charCod, String desc) {
		this.desc = desc;
		this.charCod = charCod;
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
		for (DominioTipoPermissao dominio : values()) {
			SelectItem item = new SelectItem(dominio.getName(), dominio.getDesc());
			itens.add(item);
		}
		return itens;
	}

}