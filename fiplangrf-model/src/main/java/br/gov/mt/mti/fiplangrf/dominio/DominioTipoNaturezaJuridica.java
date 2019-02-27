package br.gov.mt.mti.fiplangrf.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public enum DominioTipoNaturezaJuridica {

	// @formatter:off
	ADMINISTRACAO_PUBLICA("ADMINISTRACAO_PUBLICA", "Administração Pública"),
	ENTIDADES_EMPRESARIAIS("ENTIDADES_EMPRESARIAIS", "Entidades Empresariais"),
	ENTIDADES_SEM_FINS_LUCRATIVOS("ENTIDADES_SEM_FINS_LUCRATIVOS", "Entidades sem fins Lucrativos"),
	PESSOAS_FISICAS("PESSOAS_FISICAS", "Pessoas Físicas"),
	ORGANIZACOES_INTERNACIONAIS("ORGANIZACOES_INTERNACIONAIS", "Organizações Internacionais e outras Instituições Extraterritoriais");
	// @formatter:on

	public static final String NOME = "DominioTipoNaturezaJuridica";

	public static final String METHOD = "getCharCod";

	private String desc;
	private String charCod;

	private DominioTipoNaturezaJuridica(String charCod, String desc) {
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
		for (DominioTipoNaturezaJuridica dominio : values()) {
			SelectItem item = new SelectItem(dominio.getName(), dominio.getDesc());
			itens.add(item);
		}
		return itens;
	}
}
