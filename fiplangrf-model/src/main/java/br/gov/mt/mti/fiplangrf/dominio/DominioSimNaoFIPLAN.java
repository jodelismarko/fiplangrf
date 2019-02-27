package br.gov.mt.mti.fiplangrf.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public enum DominioSimNaoFIPLAN {

	// @formatter:off
	N("N", "Não"),
	S("S", "Sim"); 
	// @formatter:on

	public static final String NOME = "DominioSimNaoFIPLAN";
	public static final String METHOD = "getCharCod";

	private String desc;
	private String charCod;

	private DominioSimNaoFIPLAN(String charCod, String desc) {
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
		for (DominioSimNaoFIPLAN dominio : values()) {
			SelectItem item = new SelectItem(dominio.getCharCod(), dominio.getDesc());
			itens.add(item);
		}
		return itens;
	}

}
