package br.gov.mt.mti.fiplangrf.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public enum DominioIndicativoProvisao {

	// @formatter:off
	SIM("SIM", "Sim"),
	NAO("NAO", "Não");
	// @formatter:on

	public static final String NOME = "DominioIndicativoProvisao";
	public static final String METHOD = "getCharCod";

	private String desc;
	private String charCod;

	private DominioIndicativoProvisao(String charCod, String desc) {
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
		for (DominioIndicativoProvisao dominio : values()) {
			SelectItem item = new SelectItem(dominio.getCharCod(), dominio.getDesc());
			itens.add(item);
		}
		return itens;
	}

}
