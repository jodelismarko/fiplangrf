package br.gov.mt.mti.fiplangrf.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public enum DominioSituacao {

	// @formatter:off
	ATIVO("ATIVO","Ativo"),
	INATIVO("INATIVO","Inativo");
	// @formatter:on

	public static final String NOME = "DominioSituacao";

	public static final String METHOD = "getCharCod";

	private String desc;
	private String charCod;

	private DominioSituacao(String charCod, String desc) {
		this.charCod = charCod;
		this.desc = desc;
	}
	
	public String getCharCod() {
		return charCod;
	}

	public void setCharCod(String charCod) {
		this.charCod = charCod;
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
		for (DominioSituacao dominio : values()) {
			SelectItem item = new SelectItem(dominio.getName(), dominio.getDesc());
			itens.add(item);
		}
		return itens;
	}
}
