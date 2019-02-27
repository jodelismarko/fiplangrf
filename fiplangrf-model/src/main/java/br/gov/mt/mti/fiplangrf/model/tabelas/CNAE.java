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
import br.gov.mt.mti.fiplangrf.common.util.StringUtils;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Audited
@Data
@Table(name = "DHWTB007_CNAE")
@EqualsAndHashCode(callSuper = false, of = { "id", "numeroCNAE", "descricao", "situacao" })
@ToString
@GeneratorEntityOptions(defaultLabel = "CNAE", descriptionProperty = "descricao")
public class CNAE extends BaseVersionedEntity<Long> {

	private static final long serialVersionUID = -2510749179212936962L;

	@Id
	@Column(name = "IDEN_CNAE")
	@SequenceGenerator(name = "CNAE_SEQ", sequenceName = "DHWSQ007_CNAE", allocationSize = 1, initialValue = 1)
	@GeneratorFieldOptions(defaultLabel = "Código", filterable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CNAE_SEQ")
	private Long id;

	@Column(name = "NUMR_CNAE", length = 7)
	@GeneratorFieldOptions(defaultLabel = "Número CNAE", filterable = true)
	private Long numeroCNAE;

	@Column(name = "DESC_CNAE")
	@GeneratorFieldOptions(defaultLabel = "Descrição", filterable = true)
	private String descricao;

	@GeneratorFieldOptions(defaultLabel = "Situação", filterable = true)
	@Column(name = "FLAG_SITUACAO", columnDefinition = "VARCHAR(7) DEFAULT 'ATIVO' ")
	@Type(type = DominioSituacao.NOME)
	private DominioSituacao situacao = DominioSituacao.ATIVO;

	public void setNumeroCNAEFormatado(String numeroCNAE) {
		this.numeroCNAE = StringUtils.sanityzeNumericString(numeroCNAE);
	}

	public String getNumeroCNAEFormatado() {
		return StringUtils.formataCNAE(numeroCNAE);
	}
}
