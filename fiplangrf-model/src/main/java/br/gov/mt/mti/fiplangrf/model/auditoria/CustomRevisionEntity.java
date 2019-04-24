package br.gov.mt.mti.fiplangrf.model.auditoria;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.ModifiedEntityNames;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import br.gov.mt.cepromat.ceprofw.common.model.BaseModel;
import br.gov.mt.mti.fiplangrf.common.util.Sanityzer;
import br.gov.mt.mti.fiplangrf.common.util.StringUtils;
import br.gov.mt.mti.fiplangrf.listener.CustomEnversListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "DHRTB999_AUDITORIA")
@Data
@EqualsAndHashCode(callSuper = false, of = {"dataOperacao"})
@RevisionEntity(CustomEnversListener.class)
public class CustomRevisionEntity implements BaseModel<Long>, Serializable {

	private static final long serialVersionUID = -8549038340976687573L;

	@Id
	@SequenceGenerator(name = "AUDITORIA_SEQ", sequenceName = "DHWSQ999_AUDITORIA", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUDITORIA_SEQ")
	@Column(name = "IDEN_AUDITORIA", precision = 10, scale = 0, nullable = false)
	@RevisionNumber
	private Long id;

	@Column(name = "NOME_USUARIO_RESPONSAVEL", nullable = false, length = 255)
	private String nomeUsuario;

	@Column(name = "NUMR_CPF_USUARIO_RESPONSAVEL", nullable = false, length = 11)
	private String numeroCPFUsuario;

	@Column(name = "ENDR_IP", nullable = false, length = 39)
	private String enderecoIP;

	@Column(name = "CODG_USUARIO_CEPRO_SECURITY", nullable = false, precision = 19, scale = 0)
	private Long codigoUsuarioCeproSecurity;

	@RevisionTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_OPERACAO")
	private Date dataOperacao;

	@ElementCollection
	@JoinTable(name = "DHWTB999_AUDITORIA_TABELAS", joinColumns = @JoinColumn(name = "IDEN_AUDITORIA"))
	@Column(name = "ENTITY_NAME")
	@ModifiedEntityNames
	private Set<String> modifiedEntityNames;

	public String getCpf() {
		if (StringUtils.isNotBlank(numeroCPFUsuario))
			return StringUtils.formataCpf(new Long(numeroCPFUsuario));
		return "";
	}

	public void setCpf(String cpf) {
		this.numeroCPFUsuario = Sanityzer.sanityzeNumericString(cpf);
	}
	
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

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}
}
