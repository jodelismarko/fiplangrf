package br.gov.mt.mti.fiplangrf.dominio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;

import br.gov.mt.cepromat.ceprofw.common.model.DominioInterface;

public enum DominioGrupoControleTF implements DominioInterface<DominioGrupoControleTF> {

	// @formatter:off
	 FOLHA_PAGAMENTO(1, "1 - Folha de Pagamento "),
	 JUROS_MULTAS_DIVIDA(2,"2 - Juros e Multas da Dívida "),
	 AMORTIZACAO_DIVIDA(6,"6 - Amortização da Dívida "),
	 LIMITE_EMPRESTIMO(7,"7 - Limite de Empréstimo"),
	 PAGAMENTO_RESTO_PAGAR(8,"8 - Pagamento de Restos a Pagar "),
	 PAGAMENTO_GRUPO_DESPESA(9,"9 - Pagamento de Grupo de Despesas: 3, 4 e 5 ");
	// @formatter:on

	public static final String NOME = "DominioGrupoControleTF";
	public static final String METHOD = "getCod";

	private int cod;
	private String desc;
	private String longDesc;
	private String charCod;

	private DominioGrupoControleTF(Integer cod, String desc) {
		this.cod = cod;
		this.desc = desc;
		this.longDesc = desc;
	}
	

	/**
	 * Retorna a instância do domínio a partir de seu código
	 * 
	 * @param codigo
	 * @return
	 */
	public static DominioGrupoControleTF valueOf(int codigo) {
		for (DominioGrupoControleTF valor : values()) {
			if (valor.getCod() == codigo) {
				return valor;
			}
		}
		return null;
	}
	
	public static DominioGrupoControleTF valueOf(Character charCod) {
		for (DominioGrupoControleTF valor : values()) {
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
		for (DominioGrupoControleTF dominio : values()) {
			SelectItem item = new SelectItem(dominio.getName(), dominio.getDesc());
			itens.add(item);
		}
		return itens;
	}

}
