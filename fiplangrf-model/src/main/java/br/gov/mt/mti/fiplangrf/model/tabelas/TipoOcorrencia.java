package br.gov.mt.mti.fiplangrf.model.tabelas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorEntityOptions;
import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseVersionedEntity;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Audited
@Table(name = "DHWTB001_TIPO_OCORRENCIA")
@EqualsAndHashCode(callSuper = false, of = { "id" })
@GeneratorEntityOptions(defaultLabel = "Tipo Ocorrencia", descriptionProperty = "descricao")
public class TipoOcorrencia extends BaseVersionedEntity<Long> implements Comparable<TipoOcorrencia>{

	private static final long serialVersionUID = -4538596703046528105L;

	@Id
	@Column(name = "IDEN_TIPO_OCORRENCIA")
	@SequenceGenerator(name = "TIPO_OCORRENCIA_SEQ", sequenceName = "DHWSQ001_TIPO_OCORRENCIA", allocationSize = 1, initialValue = 1)
	@GeneratorFieldOptions(defaultLabel = "Código", filterable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPO_OCORRENCIA_SEQ")
	private Long id;

	@Column(name = "DESC_TIPO_OCORRENCIA", length = 100, unique = true)
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	private String descricao;
	
	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	@Column(name = "FLAG_SITUACAO", columnDefinition = "VARCHAR(7) DEFAULT 'ATIVO' ")
	@Type(type = DominioSituacao.NOME)
	private DominioSituacao situacao = DominioSituacao.ATIVO;
	
	@Override
	public int compareTo(TipoOcorrencia o) {
		return this.getDescricao().compareToIgnoreCase(o.getDescricao());
	}
	
}
