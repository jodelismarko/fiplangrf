package br.gov.mt.mti.fiplangrf.model.tabelas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorEntityOptions;
import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseVersionedEntity;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoPendencia;
import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUnidadeOrcamentaria;
import br.gov.mt.mti.fiplangrf.util.converter.FiplanConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Audited
@Table(name = "DHWTB011_OCORRENCIA")
@EqualsAndHashCode(callSuper = false, of = { "id", "situacaoPendencia", "dataInscricao", "dataRegularizacao" })
@ToString(of = { "id" })
@GeneratorEntityOptions(defaultLabel = "Ocorrencia")
public class Ocorrencia extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = -3915497883229981026L;

	@Id
	@Column(name = "IDEN_OCORRENCIA")
	@SequenceGenerator(name = "OCORRENCIA_SEQ", sequenceName = "DHWSQ011_OCORRENCIA", allocationSize = 1, initialValue = 1)
	@GeneratorFieldOptions(defaultLabel = "Código", filterable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OCORRENCIA_SEQ")
	private Long id;

	@ManyToOne(optional = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "IDEN_UNIDADE_ADMINISTRATIVA", nullable = false, foreignKey = @ForeignKey(name = "DHWFK011_DHWTB008_UND_ADMINIST"))
	private UnidadeAdministrativa unidadeAdministrativa;

	@Embedded
	UnidadeOrcamentaria unidadeOrcamentaria;

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false)
	@JoinColumn(name = "IDEN_TIPO_OCORRENCIA", nullable = false, foreignKey = @ForeignKey(name = "DHWFK011_DHWTB001_TIPO_OCORREN"))
	private TipoOcorrencia tipoOcorrencia;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "IDEN_GRUPO_PENDENCIA", foreignKey = @ForeignKey(name = "DHWFK011_DHWTB002_GRUPO_PENDEN"))
	private GrupoPendencia grupoPendencia;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "IDEN_SUBGRUPO_PENDENCIA", foreignKey = @ForeignKey(name = "DHWFK011_DHWTB003_SUBGRU_PENDE"))
	private SubGrupoPendencia subGrupoPendencia;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "IDEN_TIPO_PENDENCIA", foreignKey = @ForeignKey(name = "DHWFK011_DHWTB004_TIPO_PENDENC"))
	private TipoPendencia tipoPendencia;

	@Column(name = "DATA_INSCRICAO", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInscricao;

	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	@Column(name = "FLAG_SITUACAO_PENDENCIA")
	@Type(type = DominioSituacaoPendencia.NOME)
	DominioSituacaoPendencia situacaoPendencia = DominioSituacaoPendencia.PENDENTE;

	@Column(name = "DATA_REGULARIZACAO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRegularizacao;

	@NotAudited
	@OneToMany(mappedBy = "ocorrencia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AcaoCoordenadoria> listaAcaoCoordenadoria = new HashSet<>();

	@NotAudited
	@OneToMany(mappedBy = "ocorrencia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MedidaIntermediaria> listaMedidaIntermediaria = new HashSet<>();

	@NotAudited
	@OneToMany(mappedBy = "ocorrencia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<EncerramentoPendencia> listaEncerramentoPendencia = new HashSet<>();
	
	@OrderBy("dataTramiteOcorrencia")
	@NotAudited
	@OneToMany(mappedBy = "ocorrencia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TramiteOcorrencia> tramites = new ArrayList<TramiteOcorrencia>();
	
	@Transient
	private String justificativaTramite;
	
	@Transient
	private FIPLANUnidadeOrcamentaria fiplanUnidadeOrcamentaria;

	@PrePersist
	private void prePersist() throws BusinessException {
		this.setUnidadeOrcamentaria(unidadeAdministrativa.getUnidadeOrcamentaria());
	}
	
	public List<TramiteOcorrencia> getTramites() {
		return Collections.unmodifiableList(tramites);
	}
	
	public String getUnidadeOrcamentariaFormatado() {
		if (this.unidadeOrcamentaria != null) {
			return this.unidadeOrcamentaria.getCodigoUnidadeOrcamentaria() + " - "
					+ this.unidadeOrcamentaria.getSiglaUnidadeOrcamentaria();
		}
		return null;
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
	
	public boolean isSituacaoPendente() {
		return this.situacaoPendencia.equals(DominioSituacaoPendencia.PENDENTE);
	}

	public boolean isSituacaoEmRegularizacao() {
		return this.situacaoPendencia.equals(DominioSituacaoPendencia.EM_REGULARIZACAO);
	}

	public boolean isSituacaoEmAnalise() {
		return this.situacaoPendencia.equals(DominioSituacaoPendencia.EM_ANALISE);
	}
	
	public boolean isSituacaoSuspensa() {
		return this.situacaoPendencia.equals(DominioSituacaoPendencia.SUSPENSA);
	}
	
	public boolean isSituacaoRegularizada() {
		return this.situacaoPendencia.equals(DominioSituacaoPendencia.REGULARIZADA);
	}
}
