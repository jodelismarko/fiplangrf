package br.gov.mt.mti.fiplangrf.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;

import br.gov.mt.cepromat.ceprofw.common.model.DominioInterface;

public enum DominioTipoOperacao implements DominioInterface<DominioTipoOperacao> {


	// @formatter:off
	ADD(0, "Inclusão"), 
	MOD(1,"Alteração"),
	DEL(2,"Exclusão");
	// @formatter:on
	
	public static final String NOME = "DominioTipoOperacao";

	private int cod;
	private String desc;
	private String longDesc;

	private DominioTipoOperacao(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
		this.longDesc = desc;
	}

	private DominioTipoOperacao(int cod, String desc, String longDesc) {
		this.cod = cod;
		this.desc = desc;
		this.longDesc = longDesc;
	}

	/**
	 * Retorna a inst�ncia do dom�nio a partir de seu c�digo
	 * 
	 * @param codigo
	 * @return
	 */
	public static DominioTipoOperacao valueOf(int codigo) {
		for (DominioTipoOperacao valor : values()) {
			if (valor.getCod() == codigo) {
				return valor;
			}
		}
		return null;

	}

	/**
	 * Verifica se o c�digo informado existe no dom�nio
	 * 
	 * @param codigo
	 * @return
	 */
	public static boolean isValid(Integer codigo) {
		return valueOf(codigo) != null;
	}

	/**
	 * Numero de elementos do dominio
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
		for (DominioTipoOperacao dominio : values()) {
			SelectItem item = new SelectItem(dominio.getName(), dominio.getDesc());
			itens.add(item);
		}
		return itens;
	}

}
