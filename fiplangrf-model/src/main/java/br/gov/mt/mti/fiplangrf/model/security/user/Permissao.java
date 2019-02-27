package br.gov.mt.mti.fiplangrf.model.security.user;

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
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoPermissao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Audited
@Data
@Table(name = "DHWTB028_PERMISSAO")
@ToString(callSuper = false, of = { "id", "nome", "action", "tipoPermissao"})
@EqualsAndHashCode(callSuper = false, of = { "id", "nome", "action", "tipoPermissao"} )
@GeneratorEntityOptions(defaultLabel = "Permissão", descriptionProperty = "nome")
public class Permissao extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = -3255164887046820023L;

	@Id
	@Column(name = "IDEN_PERMISSAO", length = 10)
	@SequenceGenerator(name = "PERMISSAO_SEQUENCE", sequenceName = "DHWSQ028_PERMISSAO", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISSAO_SEQUENCE")
	private Long id;

	@GeneratorFieldOptions(defaultLabel = "nome", filterable = true)
	@Column(name = "NOME", length = 200, nullable = false)
	private String nome;

	@GeneratorFieldOptions(defaultLabel = "Ação", filterable = true)
	@Column(name = "ACTION", length = 4000, nullable = false, unique = true)
	private String action;

	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	@Column(name = "DESCRICAO", length = 400, nullable = true)
	private String descricao;

	@GeneratorFieldOptions(defaultLabel = "Tipo de permissão", filterable = true)
	@Type(type = DominioTipoPermissao.NOME)
	@Column(name = "TIPO_PERMISSAO")
	private DominioTipoPermissao tipoPermissao;

	public Permissao() {
		super();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

}
