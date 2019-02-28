package br.gov.mt.mti.fiplangrf.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;

import br.gov.mt.cepromat.ceprofw.common.model.DominioInterface;

public enum DominioSituacaoCadastroRF implements DominioInterface<DominioSituacaoCadastroRF> {

	// @formatter:off
	 EM_ELABORACAO("EM_ELABORACAO", "Em Elaboração"),
	 AGUARDANDO_DETALHAMENTO("AGUARDANDO_DETALHAMENTO", "Aguardando Detalhamento"),
	 AGUARDANDO_DOCUMENTOS("AGUARDANDO_DOCUMENTOS", "Aguardando Documentos"),
	 COM_EXECUCAO("COM_EXECUCAO", "Com Execução"),
	 ENCERRADO("ENCERRADO", "Encerrado");
	// @formatter:on

	public static final String NOME = "DominioSituacaoCadastroRF";
	public static final String METHOD = "getCharCod";

	private int cod;
	private String desc;
	private String longDesc;
	private String charCod;

	private DominioSituacaoCadastroRF(String charCod, String desc) {
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
	public static DominioSituacaoCadastroRF valueOf(int codigo) {
		for (DominioSituacaoCadastroRF valor : values()) {
			if (valor.getCod() == codigo) {
				return valor;
			}
		}
		return null;
	}
	
	public static DominioSituacaoCadastroRF valueOf(Character charCod) {
		for (DominioSituacaoCadastroRF valor : values()) {
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
		for (DominioSituacaoCadastroRF dominio : values()) {
			SelectItem item = new SelectItem(dominio.getName(), dominio.getDesc());
			itens.add(item);
		}
		return itens;
	}

}
