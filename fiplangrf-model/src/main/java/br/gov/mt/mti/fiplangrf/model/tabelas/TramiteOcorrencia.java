package br.gov.mt.mti.fiplangrf.model.tabelas;

import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorEntityOptions;
import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseVersionedEntity;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoPendencia;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "DHWTB015_TRAMITE_OCORRENCIA")
@EqualsAndHashCode(callSuper = false, of = { "id", "dataTramiteOcorrencia", "situacaoPendencia" })
@ToString(callSuper = false, of = { "id", "ocorrencia" })
@GeneratorEntityOptions(defaultLabel = "Tramite Ocorrencia", descriptionProperty = "descricao")
public class TramiteOcorrencia extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = 5726285586036133908L;

	@Id
	@Column(name = "IDEN_TRAMITE_OCORRENCIA")
	@SequenceGenerator(name = "TRAMITE_OCORRENCIA_SEQ", sequenceName = "DHWSQ015_TRAMITE_OCORRENCIA", allocationSize = 1, initialValue = 1)
	@GeneratorFieldOptions(defaultLabel = "Código", filterable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRAMITE_OCORRENCIA_SEQ")
	private Long id;

	@Column(name = "DATA_HORA")
	@GeneratorFieldOptions(defaultLabel = "Data Ocorrencia", filterable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataTramiteOcorrencia;

	@GeneratorFieldOptions(defaultLabel = "Situação Pendencia", filterable = true)
	@Column(name = "FLAG_SITUACAO_PENDENCIA", columnDefinition = "VARCHAR(20)")
	@Type(type = DominioSituacaoPendencia.NOME)
	private DominioSituacaoPendencia situacaoPendencia;

	@Column(name = "DESC_JUSTIFICATIVA")
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	private String justificativa;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "IDEN_OCORRENCIA", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT, name = "DHWFK015_DHWTB011_OCORRENCIA"))
	private Ocorrencia ocorrencia;

	@ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "IDEN_USUARIO", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT, name = "DHWFK015_DHWTB018_USUARIO"))
	private Usuario usuarioAcao;

	public TramiteOcorrencia() {
	}

	public static TramiteOcorrencia registrado(Ocorrencia ocorrencia, Usuario usuarioAcao) throws BusinessException {
		return new TramiteOcorrencia(ocorrencia, usuarioAcao, DominioSituacaoPendencia.PENDENTE);
	}

	public TramiteOcorrencia(Ocorrencia ocorrencia, Usuario usuarioAcao, DominioSituacaoPendencia situacaoPendencia) throws BusinessException {
		ocorrencia.setSituacaoPendencia(situacaoPendencia);
		this.situacaoPendencia = situacaoPendencia;
		this.usuarioAcao = usuarioAcao;
		this.ocorrencia = ocorrencia;
		this.dataTramiteOcorrencia = new Date(System.currentTimeMillis());
	}
}
