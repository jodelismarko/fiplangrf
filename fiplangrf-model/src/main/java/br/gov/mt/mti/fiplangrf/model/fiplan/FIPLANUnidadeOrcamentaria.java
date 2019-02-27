package br.gov.mt.mti.fiplangrf.model.fiplan;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import br.gov.mt.cepromat.ceprofw.common.model.BaseModel;
import lombok.Data;

@Data
@Entity
@Table(name = "ACWVW0455")
@Where(clause = "CD_EXERCICIO = EXTRACT(year FROM sysdate)")
public class FIPLANUnidadeOrcamentaria implements BaseModel<Long>, Comparable<FIPLANUnidadeOrcamentaria> {

	private static final long serialVersionUID = 7550263526375348639L;

	@Id
	@Column(name = "CD_UNIDADE_ORCAMENTARIA", insertable = false, updatable = false)
	private Long codigoUnidadeOrcamentaria;

	@Column(name = "DS_UNIDADE_ORCAMENTARIA", insertable = false, updatable = false)
	private String descricaoUnidadeOrcamentaria;

	@Column(name = "DS_SIGLA", insertable = false, updatable = false)
	private String siglaUnidadeOrcamentaria;

	@Column(name = "CD_EXERCICIO", length = 110, nullable = false, insertable = false, updatable = false)
	private Integer exercicioUnidadeOrcamentaria;

	@Override
	public Long getId() {
		return this.codigoUnidadeOrcamentaria;
	}

	@Override
	public void setId(Long id) {
		this.codigoUnidadeOrcamentaria = id;
	}

	public String getDescricaoFormatado() {
		if (this.codigoUnidadeOrcamentaria == null || this.siglaUnidadeOrcamentaria == null) {
			return "";
		}
		return this.codigoUnidadeOrcamentaria + " - " + this.siglaUnidadeOrcamentaria;
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
	public int compareTo(FIPLANUnidadeOrcamentaria other) {
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
