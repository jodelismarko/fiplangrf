package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.PlanejamentoAnualPrazos;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoRegistro;
import java.util.Date;

public class PlanejamentoAnualPrazosCriteria extends DynamicSearchCriteria<PlanejamentoAnualPrazos> {

	@FilterFieldOption(label = "Numero Exercicio ", property = "numeroExercicio", type = FieldType.INTEGER)
	private Integer numeroExercicio;

	@FilterFieldOption(label = "Flag Situacao ", property = "flagSituacao", type = FieldType.ENUM)
	private DominioSituacaoRegistro flagSituacao;

	@FilterFieldOption(label = "Data Inicio Estimativa ", property = "dataInicioEstimativa", type = FieldType.TEXT)
	private Date dataInicioEstimativa;

	@FilterFieldOption(label = "Data Fim Estimativa ", property = "dataFimEstimativa", type = FieldType.TEXT)
	private Date dataFimEstimativa;

	public PlanejamentoAnualPrazosCriteria() {
		super(PlanejamentoAnualPrazos.class);
	}

	public Integer getNumeroExercicio() {
		return numeroExercicio;
	}

	public void setNumeroExercicio(Integer numeroExercicio) {
		this.numeroExercicio = numeroExercicio;
	}

	public DominioSituacaoRegistro getFlagSituacao() {
		return flagSituacao;
	}

	public void setFlagSituacao(DominioSituacaoRegistro flagSituacao) {
		this.flagSituacao = flagSituacao;
	}

	public Date getDataInicioEstimativa() {
		return dataInicioEstimativa;
	}

	public void setDataInicioEstimativa(Date dataInicioEstimativa) {
		this.dataInicioEstimativa = dataInicioEstimativa;
	}

	public Date getDataFimEstimativa() {
		return dataFimEstimativa;
	}

	public void setDataFimEstimativa(Date dataFimEstimativa) {
		this.dataFimEstimativa = dataFimEstimativa;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("numeroExercicio", numeroExercicio)
			.append("flagSituacao", flagSituacao)
			.append("dataInicioEstimativa", dataInicioEstimativa)
			.append("dataFimEstimativa", dataFimEstimativa)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
