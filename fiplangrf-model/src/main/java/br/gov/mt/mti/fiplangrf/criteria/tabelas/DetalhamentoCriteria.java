package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.Detalhamento;
import java.math.BigDecimal;
import br.gov.mt.mti.fiplangrf.dominio.DominioSimNao;
import br.gov.mt.mti.fiplangrf.dominio.DominioGrupoControleTF;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoOperacaoNLA;
import br.gov.mt.mti.fiplangrf.model.tabelas.RecursoFinanceiro;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.model.tabelas.FonteRecurso;

public class DetalhamentoCriteria extends DynamicSearchCriteria<Detalhamento> {

	@FilterFieldOption(label = "Valor Disponivel ", property = "valorDisponivel", type = FieldType.BIG_DECIMAL)
	private BigDecimal valorDisponivel;

	@FilterFieldOption(label = "Valor Detalhado ", property = "valorDetalhado", type = FieldType.BIG_DECIMAL)
	private BigDecimal valorDetalhado;

	@FilterFieldOption(label = "Valor NLA ", property = "valorNLA", type = FieldType.BIG_DECIMAL)
	private BigDecimal valorNLA;

	@FilterFieldOption(label = "Flag Limite Emprestimo ", property = "flagLimiteEmprestimo", type = FieldType.ENUM)
	private DominioSimNao flagLimiteEmprestimo;

	@FilterFieldOption(label = "Numero NLA ", property = "numeroNLA", type = FieldType.LONG)
	private Long numeroNLA;

	@FilterFieldOption(label = "Flag Resto Pagar ", property = "flagRestoPagar", type = FieldType.ENUM)
	private DominioSimNao flagRestoPagar;

	@FilterFieldOption(label = "Flag Grupo Controle Teto ", property = "flagGrupoControleTeto", type = FieldType.ENUM)
	private DominioGrupoControleTF flagGrupoControleTeto;

	@FilterFieldOption(label = "Valor Repassar ", property = "valorRepassar", type = FieldType.BIG_DECIMAL)
	private BigDecimal valorRepassar;

	@FilterFieldOption(label = "Flag Operacao NLA ", property = "flagOperacaoNLA", type = FieldType.ENUM)
	private DominioTipoOperacaoNLA flagOperacaoNLA;

	@FilterFieldOption(label = "Flag NLA ", property = "flagNLA", type = FieldType.ENUM)
	private DominioSimNao flagNLA;

	@FilterFieldOption(label = "Flag ARR ", property = "flagARR", type = FieldType.ENUM)
	private DominioSimNao flagARR;

	@FilterFieldOption(label = "Flag ARR Devolucao ", property = "flagARRDevolucao", type = FieldType.ENUM)
	private DominioSimNao flagARRDevolucao;

	@FilterFieldOption(label = "Flag Repasse Recebido ", property = "flagRepasseRecebido", type = FieldType.ENUM)
	private DominioSimNao flagRepasseRecebido;

	@FilterFieldOption(label = "Flag Repasse Concedido ", property = "flagRepasseConcedido", type = FieldType.ENUM)
	private DominioSimNao flagRepasseConcedido;

	@FilterFieldOption(label = "Usuario ", property = "usuario", type = FieldType.TEXT)
	private Usuario usuario;

	@FilterFieldOption(label = "Flag Situacao ", property = "flagSituacao", type = FieldType.ENUM)
	private DominioSituacaoRegistro flagSituacao;

	@FilterFieldOption(label = "Recurso Financeiro ", property = "recursoFinanceiro", type = FieldType.LIST)
	private RecursoFinanceiro recursoFinanceiro;

	@FilterFieldOption(label = "Fonte Recurso ", property = "fonteRecurso", type = FieldType.LIST)
	private FonteRecurso fonteRecurso;

	public DetalhamentoCriteria() {
		super(Detalhamento.class);
	}

	public BigDecimal getValorDisponivel() {
		return valorDisponivel;
	}

	public void setValorDisponivel(BigDecimal valorDisponivel) {
		this.valorDisponivel = valorDisponivel;
	}

	public BigDecimal getValorDetalhado() {
		return valorDetalhado;
	}

	public void setValorDetalhado(BigDecimal valorDetalhado) {
		this.valorDetalhado = valorDetalhado;
	}

	public BigDecimal getValorNLA() {
		return valorNLA;
	}

	public void setValorNLA(BigDecimal valorNLA) {
		this.valorNLA = valorNLA;
	}

	public DominioSimNao getFlagLimiteEmprestimo() {
		return flagLimiteEmprestimo;
	}

	public void setFlagLimiteEmprestimo(DominioSimNao flagLimiteEmprestimo) {
		this.flagLimiteEmprestimo = flagLimiteEmprestimo;
	}

	public Long getNumeroNLA() {
		return numeroNLA;
	}

	public void setNumeroNLA(Long numeroNLA) {
		this.numeroNLA = numeroNLA;
	}

	public DominioSimNao getFlagRestoPagar() {
		return flagRestoPagar;
	}

	public void setFlagRestoPagar(DominioSimNao flagRestoPagar) {
		this.flagRestoPagar = flagRestoPagar;
	}

	public DominioGrupoControleTF getFlagGrupoControleTeto() {
		return flagGrupoControleTeto;
	}

	public void setFlagGrupoControleTeto(DominioGrupoControleTF flagGrupoControleTeto) {
		this.flagGrupoControleTeto = flagGrupoControleTeto;
	}

	public BigDecimal getValorRepassar() {
		return valorRepassar;
	}

	public void setValorRepassar(BigDecimal valorRepassar) {
		this.valorRepassar = valorRepassar;
	}

	public DominioTipoOperacaoNLA getFlagOperacaoNLA() {
		return flagOperacaoNLA;
	}

	public void setFlagOperacaoNLA(DominioTipoOperacaoNLA flagOperacaoNLA) {
		this.flagOperacaoNLA = flagOperacaoNLA;
	}

	public DominioSimNao getFlagNLA() {
		return flagNLA;
	}

	public void setFlagNLA(DominioSimNao flagNLA) {
		this.flagNLA = flagNLA;
	}

	public DominioSimNao getFlagARR() {
		return flagARR;
	}

	public void setFlagARR(DominioSimNao flagARR) {
		this.flagARR = flagARR;
	}

	public DominioSimNao getFlagARRDevolucao() {
		return flagARRDevolucao;
	}

	public void setFlagARRDevolucao(DominioSimNao flagARRDevolucao) {
		this.flagARRDevolucao = flagARRDevolucao;
	}

	public DominioSimNao getFlagRepasseRecebido() {
		return flagRepasseRecebido;
	}

	public void setFlagRepasseRecebido(DominioSimNao flagRepasseRecebido) {
		this.flagRepasseRecebido = flagRepasseRecebido;
	}

	public DominioSimNao getFlagRepasseConcedido() {
		return flagRepasseConcedido;
	}

	public void setFlagRepasseConcedido(DominioSimNao flagRepasseConcedido) {
		this.flagRepasseConcedido = flagRepasseConcedido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DominioSituacaoRegistro getFlagSituacao() {
		return flagSituacao;
	}

	public void setFlagSituacao(DominioSituacaoRegistro flagSituacao) {
		this.flagSituacao = flagSituacao;
	}

	public RecursoFinanceiro getRecursoFinanceiro() {
		return recursoFinanceiro;
	}

	public void setRecursoFinanceiro(RecursoFinanceiro recursoFinanceiro) {
		this.recursoFinanceiro = recursoFinanceiro;
	}

	public FonteRecurso getFonteRecurso() {
		return fonteRecurso;
	}

	public void setFonteRecurso(FonteRecurso fonteRecurso) {
		this.fonteRecurso = fonteRecurso;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("valorDisponivel", valorDisponivel)
			.append("valorDetalhado", valorDetalhado)
			.append("valorNLA", valorNLA)
			.append("flagLimiteEmprestimo", flagLimiteEmprestimo)
			.append("numeroNLA", numeroNLA)
			.append("flagRestoPagar", flagRestoPagar)
			.append("flagGrupoControleTeto", flagGrupoControleTeto)
			.append("valorRepassar", valorRepassar)
			.append("flagOperacaoNLA", flagOperacaoNLA)
			.append("flagNLA", flagNLA)
			.append("flagARR", flagARR)
			.append("flagARRDevolucao", flagARRDevolucao)
			.append("flagRepasseRecebido", flagRepasseRecebido)
			.append("flagRepasseConcedido", flagRepasseConcedido)
			.append("usuario", usuario)
			.append("flagSituacao", flagSituacao)
			.append("recursoFinanceiro", recursoFinanceiro)
			.append("fonteRecurso", fonteRecurso)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
