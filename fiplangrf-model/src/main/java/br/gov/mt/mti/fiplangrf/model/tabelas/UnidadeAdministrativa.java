package br.gov.mt.mti.fiplangrf.model.tabelas;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorEntityOptions;
import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseVersionedEntity;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.common.util.StringUtils;
import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;
import br.gov.mt.mti.fiplangrf.dominio.DominioSimNao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoCadastralCnpj;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoNaturezaJuridica;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoPoder;
import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUnidadeOrcamentaria;
import br.gov.mt.mti.fiplangrf.util.converter.FiplanConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Audited
@AuditTable("DHWTB008_UND_ADMINISTRATIV_AUD")
@Table(name = "DHWTB008_UNIDADE_ADMINISTRATIV")
@EqualsAndHashCode(callSuper = false, of = { "id", "numrCNPJ", "nomeEmpresarial", "sigla" })
@GeneratorEntityOptions(defaultLabel = "Unidade Administrativa", descriptionProperty = "descricao")
public class UnidadeAdministrativa extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = 8998898287046067674L;

	@Id
	@Column(name = "IDEN_UNIDADE_ADMINISTRATIVA")
	@SequenceGenerator(name = "UNIDADE_ADMINISTRATIVA_SEQ", sequenceName = "DHWSQ008_UNIDADE_ADMINISTRATIV", allocationSize = 1, initialValue = 1)
	@GeneratorFieldOptions(defaultLabel = "Código", filterable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UNIDADE_ADMINISTRATIVA_SEQ")
	private Long id;

	@Column(name = "NUMR_CNPJ", length = 14, unique = true)
	@GeneratorFieldOptions(defaultLabel = "Número CNPJ", filterable = true)
	private Long numrCNPJ;

	@Column(name = "NOME_EMPRESARIAL", length = 200, nullable = false)
	@GeneratorFieldOptions(defaultLabel = "Nome Empresarial", filterable = true)
	private String nomeEmpresarial;

	@Column(name = "SIGLA", length = 20)
	@GeneratorFieldOptions(defaultLabel = "Sigla", filterable = true)
	private String sigla;

	@GeneratorFieldOptions(defaultLabel = "Situação CNPJ", filterable = true)
	@Column(name = "FLAG_SITUACAO_CADASTRAL_CNPJ", columnDefinition = "VARCHAR(10)")
	@Type(type = DominioSituacaoCadastralCnpj.NOME)
	private DominioSituacaoCadastralCnpj situacaoCnpj;

	@GeneratorFieldOptions(defaultLabel = "Tipo de Poder", filterable = true)
	@Column(name = "FLAG_TIPO_PODER", columnDefinition = "VARCHAR(50)")
	@Type(type = DominioTipoPoder.NOME)
	private DominioTipoPoder tipoPoder;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "IDEN_TIPO_ADMINISTRACAO", nullable = false, foreignKey = @ForeignKey(name = "DHWFK008_DHWTB005_TIPO_ADMINIS"))
	private TipoAdministracao tipoAdministracao;

	@GeneratorFieldOptions(defaultLabel = "Tipo de Poder", filterable = true)
	@Column(name = "FLAG_UND_ORCAMEN_CADAST_FIPLAN", columnDefinition = "VARCHAR(3)")
	@Type(type = DominioSimNao.NOME)
	private DominioSimNao unidadeCadastraFiplan = DominioSimNao.NAO;

	@Embedded
	private UnidadeOrcamentaria unidadeOrcamentaria;

	@Column(name = "DATA_ABERTURA", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAbertura;

	@Column(name = "DATA_TERMINO", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataTermino;

	@GeneratorFieldOptions(defaultLabel = "Tipo Natureza Juridica", filterable = true)
	@Column(name = "FLAG_TIPO_NATUREZA_JURIDICA", columnDefinition = "VARCHAR(100)")
	@Type(type = DominioTipoNaturezaJuridica.NOME)
	private DominioTipoNaturezaJuridica tipoNaturezaJuridica;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "IDEN_NATUREZA_JURIDICA", nullable = false, foreignKey = @ForeignKey(name = "DHWFK008_DHWTB006_NATURE_JURID"))
	private NaturezaJuridica naturezaJuridica;

	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	@Column(name = "FLAG_SITUACAO", columnDefinition = "VARCHAR(7) DEFAULT 'ATIVO' ")
	@Type(type = DominioSituacao.NOME)
	private DominioSituacao situacao = DominioSituacao.ATIVO;

	@OrderBy("situacao ASC")
	@OneToMany(mappedBy = "unidadeAdministrativa", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<CNAEUnidadeAdministrativa> cnaes = new HashSet<>();
	
		@OrderBy("situacao ASC")
	@OneToMany(mappedBy = "unidadeAdministrativa", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<LegislacaoUnidadeAdministrativa> legislacaoUAs = new HashSet<>();

	@Transient
	private FIPLANUnidadeOrcamentaria fiplanUnidadeOrcamentaria;

	/*
	 * Getters and Setters
	 */
	public void setNumrCNPJFormatado(String numrCNPJ) throws BusinessException {
		try {
			this.numrCNPJ = StringUtils.sanityzeNumericString(numrCNPJ);
		} catch (NumberFormatException e) {
			throw new BusinessException(DominioMensagem.MSG_CNPJ_INVALIDO.getDesc());
		}
	}

	public String getNumrCNPJFormatado() {
		return StringUtils.formataCnpj(numrCNPJ);
	}

	public String getUnidadeOrcamentariaFormatado() {
		if (this.unidadeOrcamentaria != null) {
			return this.unidadeOrcamentaria.getCodigoUnidadeOrcamentaria() + " - "
					+ this.unidadeOrcamentaria.getSiglaUnidadeOrcamentaria();
		}
		return "";
	}

	public String getUnidadeOrcamentariaDescricao() {
		if (this.unidadeOrcamentaria == null) {
			return "";
		}
		return this.unidadeOrcamentaria.getDescricaoUnidadeOrcamentaria();
	}

	public boolean existeCnaePrincipal() {
		for (CNAEUnidadeAdministrativa cnaeUA : cnaes) {
			if (cnaeUA.isTipoAtividadePrincipal() && cnaeUA.getSituacao().equals(DominioSituacao.ATIVO)) {
				return true;
			}
		}
		return false;
	}

	public boolean isCNAECadastrato(CNAEUnidadeAdministrativa cnaeUnidadeAdministrativa) {
		for (CNAEUnidadeAdministrativa cnaeUA : cnaes) {
			if (cnaeUA.getNumeroCNAE().equals(cnaeUnidadeAdministrativa.getNumeroCNAE())
					&& cnaeUA.getSituacao().equals(DominioSituacao.ATIVO)) {
				return (cnaeUA.getId() != null ^ cnaeUnidadeAdministrativa.getId() != null)
						|| (cnaeUA.getId() != null && cnaeUnidadeAdministrativa.getId() != null
								&& !cnaeUA.getId().equals(cnaeUnidadeAdministrativa.getId()));
			}
		}
		return false;
	}

	public void setFiplanUnidadeOrcamentaria(FIPLANUnidadeOrcamentaria fiplanUnidadeOrcamentaria) {
		this.fiplanUnidadeOrcamentaria = fiplanUnidadeOrcamentaria;
		if (fiplanUnidadeOrcamentaria != null) {
			this.unidadeOrcamentaria = FiplanConverter.getUnidadeOrcamentariaFromFiplan(fiplanUnidadeOrcamentaria);
		}
	}
	
	public FIPLANUnidadeOrcamentaria getFiplanUnidadeOrcamentaria() {
		if (unidadeOrcamentaria != null) {
			return FiplanConverter.getFiplanFromUnidadeOrcamentaria(unidadeOrcamentaria);
		}
		return fiplanUnidadeOrcamentaria;
	}

	public void setUnidadeOrcamentaria(UnidadeOrcamentaria unidadeOrcamentaria) {
		this.unidadeOrcamentaria = unidadeOrcamentaria;
		if (unidadeOrcamentaria != null) {
			this.fiplanUnidadeOrcamentaria = FiplanConverter.getFiplanFromUnidadeOrcamentaria(unidadeOrcamentaria);
		}
	}

	public boolean isSituacaoAtivo() {
		return this.situacaoCnpj != null && this.situacaoCnpj.equals(DominioSituacaoCadastralCnpj.ATIVO);
	}

	@Override
	public String toString() {
		return "UnidadeAdministrativa [id=" + id + ", numrCNPJ=" + numrCNPJ + ", nomeEmpresarial=" + nomeEmpresarial
				+ ", sigla=" + sigla + ", situacaoCnpj=" + situacaoCnpj + ", tipoPoder=" + tipoPoder
				+ ", tipoAdministracao=" + tipoAdministracao + ", dataAbertura=" + dataAbertura + ", dataTermino="
				+ dataTermino + ", tipoNaturezaJuridica=" + tipoNaturezaJuridica + ", situacao=" + situacao + "]";
	}
}
