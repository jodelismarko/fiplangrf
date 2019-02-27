package br.gov.mt.mti.fiplangrf.model.tabelas;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorEntityOptions;
import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseVersionedEntity;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Audited
@AuditTable("DHWTB016_REV_OCORRENCIA_AUD")
@Table(name = "DHWTB016_REVISAO_OCORRENCIA")
@EqualsAndHashCode(callSuper = false, of = {"id", "dataRevisao", "enderecoIP"})
@GeneratorEntityOptions(defaultLabel = "Revisão Ocorrência")
public class Revisao extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = -2529342557965337086L;

	@Id
	@Column(name = "IDEN_REVISAO_OCORRENCIA", length = 8)
	@SequenceGenerator(name = "REVISAO_OCORRENCIA_SEQ", sequenceName = "DHWSQ016_REVISAO_OCORRENCIA", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVISAO_OCORRENCIA_SEQ")
	private Long id;

	@GeneratorFieldOptions(defaultLabel = "Data Revisão", filterable = true)
	@Column(name = "DATA_REVISAO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRevisao;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "IDEN_USUARIO", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.PROVIDER_DEFAULT, name = "DHWFK016_DHWTB0018_USUARIO"))
	private Usuario usuario;
	
	@Column(name = "NUMR_IP", nullable = false, length = 39)
	private String enderecoIP;

	@PrePersist
	public void prePersist() {
		this.dataRevisao = new Date();
	}
	
	public String getDataRevisaoFormatado() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyy - HH:mm:ss");
		
		return format.format(dataRevisao);
	}
	
	//Metodos não utilizados
	@Override
	public Date getDataCriacao() {
		return null;
	}

	@Override
	public void setDataCriacao(Date dataCriacao) {
		
	}

	@Override
	public Date getDataAtualizacao() {
		return null;
	}

	@Override
	public void setDataAtualizacao(Date dataAtualizacao) {
	}
}
