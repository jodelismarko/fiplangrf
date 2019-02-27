package br.gov.mt.mti.fiplangrf.model.tabelas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorEntityOptions;
import br.gov.mt.cepromat.ceprofw.common.gerador.suporte.GeneratorFieldOptions;
import br.gov.mt.cepromat.ceprofw.core.model.BaseVersionedEntity;
import br.gov.mt.mti.fiplangrf.common.util.StringUtils;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoNaturezaJuridica;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Audited
@Table(name = "DHWTB006_NATUREZA_JURIDICA", uniqueConstraints = @UniqueConstraint(columnNames = "NUMR_NATUREZA_JURIDICA", name = "DHWUK006_NATUREZA_JURIDICA"))
@EqualsAndHashCode(callSuper = false, of = { "id" })
@GeneratorEntityOptions(defaultLabel = "Natureza Juridica", descriptionProperty = "descricao")
public class NaturezaJuridica extends BaseVersionedEntity<Long> implements Comparable<NaturezaJuridica> {

	private static final long serialVersionUID = -3127222861864847902L;

	@Id
	@Column(name = "IDEN_NATUREZA_JURIDICA")
	@SequenceGenerator(name = "NATUREZA_JURIDICA_SEQ", sequenceName = "DHWSQ006_NATUREZA_JURIDICA", allocationSize = 1, initialValue = 1)
	@GeneratorFieldOptions(defaultLabel = "Código", filterable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NATUREZA_JURIDICA_SEQ")
	private Long id;

	@Column(name = "NUMR_NATUREZA_JURIDICA", length = 5)
	@GeneratorFieldOptions(defaultLabel = "Número Natureza Juridica", filterable = true)
	private Long numrNaturezaJuridica;

	@Column(name = "DESC_NATUREZA_JURIDICA", length = 200)
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	private String descricao;

	@Column(name = "FLAG_TIPO_NATUREZA_JURIDICA", columnDefinition = "VARCHAR(100) DEFAULT 'ADMINISTRACAO_PUBLICA'")
	@Type(type = DominioTipoNaturezaJuridica.NOME)
	@GeneratorFieldOptions(defaultLabel = "Tipo Natureza Juridica", filterable = true)
	private DominioTipoNaturezaJuridica tipoNaturezaJuridica = DominioTipoNaturezaJuridica.ADMINISTRACAO_PUBLICA;

	@Column(name = "FLAG_SITUACAO", columnDefinition = "VARCHAR(7) DEFAULT 'ATIVO' ")
	@Type(type = DominioSituacao.NOME)
	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	private DominioSituacao situacao = DominioSituacao.ATIVO;

	public void setNumrNaturezaJuridicaFormatado(String numrNaturezaJuridica) {
		this.numrNaturezaJuridica = StringUtils.sanityzeNumericString(numrNaturezaJuridica);
	}

	public String getNumrNaturezaJuridicaFormatado() {
		return StringUtils.formataNumrNaturezaJuridica(numrNaturezaJuridica);
	}

	public String getDescricaoFormatado() {
		return StringUtils.formataNumrNaturezaJuridica(numrNaturezaJuridica) + " - " + this.descricao;
	}

	@Override
	public int compareTo(NaturezaJuridica o2) {
		return this.getDescricao().compareToIgnoreCase(o2.getDescricao());
	}
}
