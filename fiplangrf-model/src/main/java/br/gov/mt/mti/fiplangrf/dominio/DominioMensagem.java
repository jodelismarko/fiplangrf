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
	MSG_CONFIRMAR_EXCLUSAO(2, "Deseja realmente excluir esse registro?"),
	
	/*Regras de Negócio*/
	MSG_INFORMAR_CNAE_UNIDADE_ADMINISTRATIVA(20001,"É obrigatório informar pelo menos um CNAE principal."),
	MSG_INFORMAR_ACAO_COORDENADORIA(20002,"É obrigatório informar pelo menos uma Ação Coordenadoria."),
	MSG_ENVIAR_PARA_REGULARIZACAO(20003, "Deseja realmente enviar a ocorrência para regularização?"),
	MSG_OCORRENCIA_ENVIADA_PARA_ELABORACAO(20004, "A Ocorrência Foi Encaminhada para Elaboração!"),
	MSG_OCORRENCIA_ENVIADA_PARA_REGULARIZACAO(20005, "A Ocorrência Foi Encaminhada para Regularização!"),
	MSG_OCORRENCIA_ENVIADA_PARA_ANALISE(30021, "Ocorrência Encaminhada para Análise com Sucesso"),
	MSG_FINALIZAR_OCORRENCIA(20006, "Deseja Realmente Finalizar a Ocorrência? Após Finalização da mesma não será possível realizar mais nenhuma ação!"),
	MSG_ENVIAR_PARA_ANALISE(20007, "Deseja Realmente enviar a ocorrência para análise?"),
	MSG_OCORRENCIA_SUSPENSA(20008, "A Ocorrência Foi Suspensa!"),
	MSG_OCORRENCIA_REATIVADA(20009, "A Ocorrência Foi Reativada!"),
	MSG_ENCERRAMENTO_PENDENCIA(20010, "É Obrigatório informar pelo menos um Encerramento de Pendência."),
	MSG_INFORMAR_UO_UC(20011, "É obrigatório informar pelo menos uma Unidade Orçamentária Permitida ou uma Unidade Conveniada Permitida."),
	MSG_ATUALIZAR_REVISAO(20012, "Deseja Realmente Atualizar a Revisão da Ocorrência?"),
	MSG_OCORRENCIA_REGULARIZADA(20013, "A Ocorrência Foi Regularizada!"),
	MSG_UO_INVALIDA(20014, "É Obrigatório Informar uma Unidade Orçamentária Ativa, a selecionada não foi encontrada!");
	;
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
