package br.gov.mt.mti.fiplangrf.model.tabelas;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorEntityOptions;
import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseVersionedEntity;
import br.gov.mt.mti.fiplangrf.common.util.StringUtils;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoAtividade;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Audited
@AuditTable("DHWTB009_CNAE_UND_ADMIN_AUD")
@EqualsAndHashCode(callSuper = false, of = { "numeroCNAE" })
@ToString(callSuper = false, of = { "id", "descricaoAtividade", "numeroCNAE", "situacao", "flagTipoAtividade" })
@Table(name = "DHWTB009_CNAE_UND_ADMINISTRATI")
@GeneratorEntityOptions(defaultLabel = "CNAE Unidade Administrativa", descriptionProperty = "descricao")
public class CNAEUnidadeAdministrativa extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = 7495627347312367799L;

	@Id
	@Column(name = "IDEN_CNAE_UND_ADMINSTRATIVA")
	@SequenceGenerator(name = "CNAE_UND_ADMINISTRATIVA_SEQ", sequenceName = "DHWSQ009_CNAE_UND_ADMINISTRATI", allocationSize = 1, initialValue = 1)
	@GeneratorFieldOptions(defaultLabel = "Código", filterable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CNAE_UND_ADMINISTRATIVA_SEQ")
	private Long id;

	@Column(name = "NUMR_CNAE", length = 7)
	@GeneratorFieldOptions(defaultLabel = "Número CNAE", filterable = true)
	private Long numeroCNAE;

	@Column(name = "DESC_ATIVIDADE")
	@GeneratorFieldOptions(defaultLabel = "Descrição Atividade", filterable = true)
	private String descricaoAtividade;

	@GeneratorFieldOptions(defaultLabel = "Atividade", filterable = true)
	@Column(name = "FLAG_TIPO_ATIVIDADE")
	@Type(type = DominioTipoAtividade.NOME)
	private DominioTipoAtividade flagTipoAtividade = DominioTipoAtividade.PRINCIPAL;

	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	@Column(name = "FLAG_SITUACAO")
	@Type(type = DominioSituacao.NOME)
	private DominioSituacao situacao = DominioSituacao.ATIVO;

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false)
	@JoinColumn(name = "IDEN_UNIDADE_ADMINISTRATIVA", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT, name = "DHWFK009_DHWTB008_UND_ADMINIST"))
	private UnidadeAdministrativa unidadeAdministrativa;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "IDEN_CNAE", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT, name = "DHWFK009_DHWTB007_CNAE"))
	private CNAE cnae = new CNAE();

	public void setCnae(CNAE cnae) {
		this.cnae = cnae;
		if (cnae != null) {
			this.setNumeroCNAE(cnae.getNumeroCNAE());
			this.setDescricaoAtividade(cnae.getDescricao());
		}
	}

	public boolean isTipoAtividadePrincipal() {
		return flagTipoAtividade.equals(DominioTipoAtividade.PRINCIPAL);
	}

	public String getNumeroCNAEFormatado() {
		return StringUtils.formataCNAE(numeroCNAE);
	}

}
