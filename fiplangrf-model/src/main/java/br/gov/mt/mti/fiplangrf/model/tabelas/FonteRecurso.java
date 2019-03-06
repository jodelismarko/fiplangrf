package br.gov.mt.mti.fiplangrf.model.tabelas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import br.gov.mt.cepromat.ceprofw.core.model.BaseEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Audited
@AuditTable(value = "DHUTB021_FONTE_RECURSO_AUD")
@Table(name = "DHUTB021_FONTE_RECURSO")
@EqualsAndHashCode(callSuper = false)
public class FonteRecurso extends BaseEntity<Long> {

	private static final long serialVersionUID = 1628826645600804917L;

	@Id
	@Column(name = "IDEN_FONTE_RECURSO", length = 8)
	@SequenceGenerator(name = "FONTE_RECURSO_SEQ", sequenceName = "DHUSQ021_FONTE_RECURSO", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "FONTE_RECURSO_SEQ")
	private Long id;

	@Column(name = "CODG_FONTE_RECURSO", length = 4, nullable = false)
	private Integer codgFonteRecurso;

//	@Column(name = "NOME_FONTE_RECURSO", length = 100, nullable = false)
//	private String nomeFonteRecurso;

	@Column(name = "DESC_DESCRICAO", length = 150, nullable = false)
	private String nomeResumidoFonteRecurso;
	
	@Column(name = "FLAG_SITUACAO", length = 7, columnDefinition = "VARCHAR2(7)")
	@Type(type = DominioSituacaoRegistro.NOME)
	private DominioSituacaoRegistro flagSituacao = DominioSituacaoRegistro.ATIVO;

	@Override
	public String toString() {
		return  codgFonteRecurso + " - " + nomeResumidoFonteRecurso ;
	}
	
	
	
}
