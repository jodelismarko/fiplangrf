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

import br.gov.mt.cepromat.ceprofw.core.model.BaseVersionedEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Audited
@AuditTable(value = "DHRTB008_FONTE_RECURSO_AUD")
@Table(name = "DHRTB008_FONTE_RECURSO")
@EqualsAndHashCode(callSuper = false, of = { "id", "codgFonteRecurso" })
@ToString(callSuper = false, of = { "id", "codgFonteRecurso", "descricaoFonteRecurso", "flagSituacao" })
public class FonteRecurso extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = 1628826645600804917L;

	@Id
	@Column(name = "IDEN_FONTE_RECURSO", length = 8)
	@SequenceGenerator(name = "FONTE_RECURSO_SEQ", sequenceName = "DHUSQ008_FONTE_RECURSO", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FONTE_RECURSO_SEQ")
	private Long id;

	@Column(name = "CODG_FONTE_RECURSO", length = 3, nullable = false)
	private Integer codgFonteRecurso;

	@Column(name = "DESC_DESCRICAO", length = 100, nullable = false)
	private String descricaoFonteRecurso;

	@Column(name = "FLAG_SITUACAO", length = 7, nullable = false, columnDefinition = "VARCHAR2(7)")
	@Type(type = DominioSituacaoRegistro.NOME)
	private DominioSituacaoRegistro flagSituacao = DominioSituacaoRegistro.ATIVO;

}
