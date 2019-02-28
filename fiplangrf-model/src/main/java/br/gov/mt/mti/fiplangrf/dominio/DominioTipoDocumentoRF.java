package br.gov.mt.mti.fiplangrf.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;

import br.gov.mt.cepromat.ceprofw.common.model.DominioInterface;

public enum DominioTipoDocumentoRF implements DominioInterface<DominioTipoDocumentoRF> {

	// @formatter:off
	 ARR("ARR", "ARR"),
	 ARR_DEVOLUCAO("ARR_DEVOLUCAO", "ARR Devolução"),
	 REPASSE_ONUS_RECEBIBO("REPASSE_ONUS_RECEBIBO", "Repasse com Ônus Recebido (NLA 13095)"),
	 REPASSE_ONUS_CONCEDIDO("REPASSE_ONUS_CONCEDIDO", "Repasse com Ônus Concedido (NLA 13094)"),
	 INCORPORACAO_OUTROS_FATOS_CONTABEIS("INCORPORACAO_OUTROS_FATOS_CONTABEIS", "Incorporação de Outros Fatos Contábeis (NLA 17001)"),
	 DESINCORPORACAO_OUTROS_FATOS_CONTABEIS("DESINCORPORACAO_OUTROS_FATOS_CONTABEIS", "Desincorporação de Outros Fatos Contábeis (NLA 37001)");
	// @formatter:on

	public static final String NOME = "DominioTipoDocumentoRF";
	public static final String METHOD = "getCharCod";

	private int cod;
	private String desc;
	private String longDesc;
	private String charCod;

	private DominioTipoDocumentoRF(String charCod, String desc) {
		this.charCod = charCod;
		this.desc = desc;
		this.longDesc = desc;
	}
	

	/**
	 * Retorna a instância do domínio a partir de seu código
	 * 
	 * @param codigo
	 * @return
	 */
	public static DominioTipoDocumentoRF valueOf(int codigo) {
		for (DominioTipoDocumentoRF valor : values()) {
			if (valor.getCod() == codigo) {
				return valor;
			}
		}
		return null;
	}
	
	public static DominioTipoDocumentoRF valueOf(Character charCod) {
		for (DominioTipoDocumentoRF valor : values()) {
			if (valor.getCharCod().equals(charCod)) {
				return valor;
			}
		}
		return null;
	}
	


	public String getCharCod() {
		return charCod;
	}

	public void setCharCod(String charCod) {
		this.charCod = charCod;
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
		for (DominioTipoDocumentoRF dominio : values()) {
			SelectItem item = new SelectItem(dominio.getName(), dominio.getDesc());
			itens.add(item);
		}
		return itens;
	}

}
