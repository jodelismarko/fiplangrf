package br.gov.mt.mti.fiplangrf.model.tabelas;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoLegislacao;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Audited
@Data
@AuditTable("DHWTB010_LEG_UND_ADM_AUD")
@Table(name = "DHWTB010_LEGISLACAO_UND_ADMINI")
@EqualsAndHashCode(callSuper = false, of = { "tipoLegislacao", "descricao" })
@GeneratorEntityOptions(defaultLabel = "Legislação Unidade Administrativa", descriptionProperty = "descricao")
public class LegislacaoUnidadeAdministrativa extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = -293683039226184682L;

	@Id
	@Column(name = "IDEN_LEGISLACAO_UND_ADM")
	@SequenceGenerator(name = "LEGISLACAO_UND_ADM_SEQ", sequenceName = "DHWSQ010_LEGISLACAO_UND_ADMINI", allocationSize = 1, initialValue = 1)
	@GeneratorFieldOptions(defaultLabel = "Código", filterable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LEGISLACAO_UND_ADM_SEQ")
	private Long id;

	@GeneratorFieldOptions(defaultLabel = "Tipo Legislação", filterable = true)
	@Column(name = "TIPO_LEGISLACAO", columnDefinition = "VARCHAR(20)")
	@Type(type = DominioTipoLegislacao.NOME)
	private DominioTipoLegislacao tipoLegislacao = DominioTipoLegislacao.CRIACAO;

	@Column(name = "DESC_LEI", length = 200)
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	private String descricao;

	@Column(name = "FLAG_SITUACAO")
	@Type(type = DominioSituacao.NOME)
	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	private DominioSituacao situacao = DominioSituacao.ATIVO;

	@ManyToOne(cascade = CascadeType.REFRESH, optional = false)
	@JoinColumn(name = "IDEN_UNIDADE_ADMINISTRATIVA", nullable = false, foreignKey = @ForeignKey(name = "DHWFK010_DHWTB008_UND_ADMINIST"))
	private UnidadeAdministrativa unidadeAdministrativa;

}