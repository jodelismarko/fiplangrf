package br.gov.mt.mti.fiplangrf.model.tabelas;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Type;

import br.gov.mt.cepromat.ceprofw.core.model.BaseEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioMes;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoCadastroRF;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "DHUVW001_RECURSO_FINANCEIRO")
public class RecursoFinanceiroView extends BaseEntity<Long> {

	private static final long serialVersionUID = -8325804407712429675L;

	@Id
	@Column(name = "IDEN_RECURSO_FINC", length = 8)
	private Long id;

	@Column(name = "EXER_RECURSO_FINC", insertable = false, updatable = false)
	private Integer execRecursoFinanceiro;

	@Column(name = "CD_UNIDADE_ORCAMENTARIA", insertable = false, updatable = false)
	private Integer codigo;

	@Column(name = "MES_RECURSO_FINANC", insertable = false, updatable = false)
	@Type(type = DominioMes.NOME)
	private DominioMes mesRecursoFinanceiro;

	@Column(name = "VALR_PROGRAMADO", insertable = false, updatable = false)
	private BigDecimal valorProgramado;

	@Column(name = "VALOR_TOTAL_COMPLEMENTAR", insertable = false, updatable = false)
	private BigDecimal valorComplementar;

	@Column(name = "VALOR_TOTAL_PREVISTO", insertable = false, updatable = false)
	private BigDecimal valorTotal;

	@Column(name = "IDEN_USUARIO", insertable = false, updatable = false)
	private Integer idUsuario;

	@Column(name = "FLAG_SITUACAO_TRAMITE")
	@Type(type = DominioSituacaoCadastroRF.NOME)
	private DominioSituacaoCadastroRF flagStatus = DominioSituacaoCadastroRF.EM_ELABORACAO;

	@Column(name = "FLAG_SITUACAO")
	@Type(type = DominioSituacaoRegistro.NOME)
	private DominioSituacaoRegistro flagSituacao = DominioSituacaoRegistro.ATIVO;

	@Column(name = "VALOR_TOTAL_REPASSADO", insertable = false, updatable = false)
	private BigDecimal valorTotalRepassado;

	public String getCodigo() {
		return StringUtils.leftPad(String.valueOf(codigo), 5, '0');
	}

}
