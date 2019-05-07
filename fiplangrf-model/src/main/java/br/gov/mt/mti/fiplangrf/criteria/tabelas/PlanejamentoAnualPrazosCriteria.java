package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.criteria.formatter.DateFormatter;
import br.gov.mt.mti.fiplangrf.model.tabelas.PlanejamentoAnualPrazos;

public class PlanejamentoAnualPrazosCriteria extends DynamicSearchCriteria<PlanejamentoAnualPrazos> {

	@FilterFieldOption(label = "Numero Exercício ", property = "numeroExercicio", type = FieldType.INTEGER)
	private Integer numeroExercicio;

	@FilterFieldOption(label = "Início Estimativa ", property = "dataInicioEstimativa", type = FieldType.DATE , formatter= DateFormatter.class)
	private Date dataInicioEstimativa;

	@FilterFieldOption(label = "Fim Estimativa ", property = "dataFimEstimativa", type = FieldType.DATE , formatter= DateFormatter.class)
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
			.append("dataInicioEstimativa", dataInicioEstimativa)
			.append("dataFimEstimativa", dataFimEstimativa)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
