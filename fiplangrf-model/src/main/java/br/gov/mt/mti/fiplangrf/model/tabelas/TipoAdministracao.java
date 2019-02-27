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

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorEntityOptions;
import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseVersionedEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoPoder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Audited
@AuditTable("DHWTB005_TIPO_ADM_AUD")
@Table(name = "DHWTB005_TIPO_ADMINISTRACAO")
@EqualsAndHashCode(callSuper = false, of = { "id" })
@GeneratorEntityOptions(defaultLabel = "Tipo Administração", descriptionProperty = "descricao")
public class TipoAdministracao extends BaseVersionedEntity<Long> implements Comparable<TipoAdministracao>{

	private static final long serialVersionUID = 3661035675607719126L;
	
	@Id
	@Column(name = "IDEN_TIPO_ADMINISTRACAO")
	@SequenceGenerator(name = "TIPO_ADMINISTRACAO_SEQ", sequenceName = "DHWSQ005_TIPO_ADMINISTRACAO", allocationSize = 1, initialValue = 1)
	@GeneratorFieldOptions(defaultLabel = "Código", filterable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPO_ADMINISTRACAO_SEQ")
	private Long id;
	
	@Column(name="DESC_TIPO_ADMINISTRACAO", length = 200)
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	private String descricao;
	
	@Column(name = "FLAG_TIPO_PODER", columnDefinition = "VARCHAR(50)")
	@Type(type = DominioTipoPoder.NOME)
	@GeneratorFieldOptions(defaultLabel = "Tipo Poder", filterable = true)
	private DominioTipoPoder tipoPoder;
	
	@Column(name = "FLAG_SITUACAO", columnDefinition = "VARCHAR(7) DEFAULT 'ATIVO' ")
	@Type(type = DominioSituacao.NOME)
	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	private DominioSituacao situacao = DominioSituacao.ATIVO;
	
	@Override
	public int compareTo(TipoAdministracao o2) {
		return this.getDescricao().compareToIgnoreCase(o2.getDescricao());
	}
}
