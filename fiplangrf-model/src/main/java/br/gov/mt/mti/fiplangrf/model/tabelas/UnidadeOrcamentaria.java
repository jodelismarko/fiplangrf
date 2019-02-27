package br.gov.mt.mti.fiplangrf.model.tabelas;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Embeddable
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class UnidadeOrcamentaria implements Comparable<UnidadeOrcamentaria> {

	@Column(name = "CODG_UNIDADE_ORCAMENTARIA", length = 5)
	private Long codigoUnidadeOrcamentaria;

	@Column(name = "DESC_UNIDADE_ORCAMENTARIA", length = 100)
	private String descricaoUnidadeOrcamentaria;

	@Column(name = "SIGLA_UNIDADE_ORCAMENTARIA", length = 100)
	private String siglaUnidadeOrcamentaria;

	@Column(name = "NUMR_EXER_UND_ORCAMENTARIA", length = 4)
	private Integer exercicioUnidadeOrcamentaria;
	
	public String getDescricaoFormatado() {
		if (this.codigoUnidadeOrcamentaria == null || this.siglaUnidadeOrcamentaria == null) {
			return "";
		}
		return this.codigoUnidadeOrcamentaria + " - " + this.siglaUnidadeOrcamentaria;
	}

	@Override
	public int compareTo(UnidadeOrcamentaria other) {
		if (this.descricaoUnidadeOrcamentaria == null && other.descricaoUnidadeOrcamentaria != null) {
			return -1;
		} else if (this.descricaoUnidadeOrcamentaria != null && other.descricaoUnidadeOrcamentaria == null) {
			return 1;
		} else if (this.descricaoUnidadeOrcamentaria != null && other.descricaoUnidadeOrcamentaria != null) {
			int result = this.descricaoUnidadeOrcamentaria.compareToIgnoreCase(other.descricaoUnidadeOrcamentaria);
			if (result != 0) {
				return result;
			}
		}

		if (this.descricaoUnidadeOrcamentaria == null) {
			return other.descricaoUnidadeOrcamentaria == null ? 0 : -1;
		}
		if (other.descricaoUnidadeOrcamentaria == null) {
			return 1;
		}

		return this.descricaoUnidadeOrcamentaria.compareToIgnoreCase(other.descricaoUnidadeOrcamentaria);
	}
}
