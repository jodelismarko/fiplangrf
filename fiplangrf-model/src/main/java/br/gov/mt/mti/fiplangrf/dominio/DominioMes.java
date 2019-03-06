package br.gov.mt.mti.fiplangrf.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;

import br.gov.mt.cepromat.ceprofw.common.model.DominioInterface;


public enum DominioMes implements DominioInterface<DominioMes> {

	//@formatter:off
    JANEIRO(1,"Janeiro"),
    FEVEREIRO(2,"Fevereiro"),
    MARCO(3,"Março"),
    ABRIL(4,"Abril"),
    MAIO(5,"Maio"),
    JUNHO(6,"Junho"),
    JULHO(7,"Julho"),
    AGOSTO(8,"Agosto"),
    SETEMBRO(9,"Setembro"),
    OUTUBRO(10,"Outubro"),
    NOVEMBRO(11,"Novembro"),
    DEZEMBRO(12,"Dezembro");
    //@formatter:on

	public static final String NOME = "DominioMes";
	public static final String METHOD = "getCod";

	private int cod;
	private String desc;
	private String longDesc;

	private DominioMes(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
		this.longDesc = desc;
	}

	private DominioMes(int cod, String desc, String longDesc) {
		this.cod = cod;
		this.desc = desc;
		this.longDesc = longDesc;
	}

	/**
	 * Retorna a instância do domínio a partir de seu código
	 * 
	 * @param codigo
	 * @return
	 */
	public static DominioMes valueOf(int codigo) {
		for (DominioMes valor : values()) {
			if (valor.getCod() == codigo) {
				return valor;
			}
		}
		return null;

	}

	/**
	 * Verifica se o código informado existe no domínio
	 * 
	 * @param codigo
	 * @return
	 */
	public static boolean isValid(Integer codigo) {
		return valueOf(codigo) != null;
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

	public int getCod() {
		return this.cod;
	}

	public String getDesc() {
		return this.desc;
	}

	@Override
	public String getLongDesc() {
		return (StringUtils.isBlank(this.longDesc)) ? this.desc : this.longDesc;
	}

	public String toString() {
		return this.getDesc();
	}

	public static List<SelectItem> getItems() {
		List<SelectItem> itens = new ArrayList<SelectItem>();
		for (DominioMes dominio : values()) {
			SelectItem item = new SelectItem(dominio.getName(), dominio.getDesc());
			itens.add(item);
		}
		return itens;
	}


}
