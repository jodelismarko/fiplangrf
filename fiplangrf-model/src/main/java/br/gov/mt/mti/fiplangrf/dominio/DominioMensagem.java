package br.gov.mt.mti.fiplangrf.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.faces.model.SelectItem;

public enum DominioMensagem {

	// @formatter:off
	
	/*Mensagens de Validação*/
	MSG_LOGIN(10001,"Nome de usuário incorreto ou senha incorreta."),
	MSG_CPF_JA_CADASTRADO(10012,"O CPF informado já está cadastrado no sistema."),
	MSG_CPF_NAO_CADASTRADO_NO_FIPLAN(10022, "O CPF informado não está cadastrado no FIPLAN."),
	MSG_REGISTRO_JA_CADASTRADO(10021,"O Registro informado já está cadastrado no sistema."),
	MSG_NENHUM_REGISTRO_ENCONTRADO(10010, "Nenhum registro encontrado."),
	MSG_DATA_MAIOR_QUE_ATUAL(10023, "A Data Informada é maior que a Atual."),
	MSG_CNPJ_INVALIDO(10004, "O CNPJ informado não é válido."),
	MSG_DATA_MENOR_QUE_ABERTURA(10024, "A Data Informada é Menor que a Data de Abertura da Unidade Administrativa."),
	
	/*Mensagens Genericas*/
	MSG_INCLUIR_SUCESSO(30001,"Registro incluído com sucesso."),
	MSG_ALTERADA_SUCESSO(30002,"Registro alterado com sucesso."),
	MSG_EXCLUIR_SUCESSO(30003,"Registro excluído com sucesso."), 
	MSG_TITULO_EXCLUSAO(1, "Exclusão de Registro Permanente"),
	MSG_CONFIRMAR_EXCLUSAO(2, "Deseja realmente excluir esse registro?");

	// @formatter:on

	public static final String NOME = "DominioMensagem";
	public static final String METHOD = "getCod";

	private int cod;
	private String desc;

	private DominioMensagem(Integer cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	/**
	 * Verifica se o código informado existe no domínio
	 * 
	 * @param codigo
	 * @return
	 */
	public static boolean isValid(String codigo) {
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

	public String toString() {
		return this.getDesc();
	}

	public static List<SelectItem> getItems() {
		List<SelectItem> itens = new ArrayList<SelectItem>();
		List<DominioMensagem> itensDom = Arrays.asList(values());
		Collections.sort(itensDom);
		for (DominioMensagem dominio : itensDom) {
			SelectItem item = new SelectItem(dominio.getCod(), dominio.getDesc());
			itens.add(item);
		}

		return itens;
	}

}
