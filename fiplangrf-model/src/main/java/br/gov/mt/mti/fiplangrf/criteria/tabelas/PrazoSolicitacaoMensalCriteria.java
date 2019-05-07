package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldType;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FieldVisibility;
import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.FilterFieldOption;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.criteria.formatter.DateFormatter;
import br.gov.mt.mti.fiplangrf.dominio.DominioMes;
import br.gov.mt.mti.fiplangrf.model.tabelas.PlanejamentoAnualPrazos;
import br.gov.mt.mti.fiplangrf.model.tabelas.PrazoSolicitacaoMensal;

public class PrazoSolicitacaoMensalCriteria extends DynamicSearchCriteria<PrazoSolicitacaoMensal> {

	@FilterFieldOption(label = "Mês ", property = "flagMes", type = FieldType.ENUM)
	private DominioMes flagMes;

	@FilterFieldOption(label = "Início Solicitação ", property = "dataInicioSolic", type = FieldType.DATE , formatter= DateFormatter.class)
	private Date dataInicioSolic;

	@FilterFieldOption(label = "Fim Solicitação ", property = "dataFimSolic", type = FieldType.DATE , formatter= DateFormatter.class)
	private Date dataFimSolic;

	@FilterFieldOption(label = "Planejamento Anual de Prazo", property = "planAnualPrazo.numeroExercicio", type = FieldType.INTEGER, fieldVisibility = FieldVisibility.FILTER)
	private Integer planAnualPrazoFiltro;
	
	@FilterFieldOption(label = "Planejamento Anual de Prazo", property = "planAnualPrazo.numeroExercicio", type = FieldType.LIST, fieldVisibility = FieldVisibility.GRID)
	private PlanejamentoAnualPrazos planAnualPrazo;

	public PrazoSolicitacaoMensalCriteria() {
		super(PrazoSolicitacaoMensal.class);
	}

	public DominioMes getFlagMes() {
		return flagMes;
	}

	public void setFlagMes(DominioMes flagMes) {
		this.flagMes = flagMes;
	}

	public Date getDataInicioSolic() {
		return dataInicioSolic;
	}

	public void setDataInicioSolic(Date dataInicioSolic) {
		this.dataInicioSolic = dataInicioSolic;
	}

	public Date getDataFimSolic() {
		return dataFimSolic;
	}

	public void setDataFimSolic(Date dataFimSolic) {
		this.dataFimSolic = dataFimSolic;
	}

	public PlanejamentoAnualPrazos getPlanAnualPrazo() {
		return planAnualPrazo;
	}

	public void setPlanAnualPrazo(PlanejamentoAnualPrazos planAnualPrazo) {
		this.planAnualPrazo = planAnualPrazo;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("flagMes", flagMes)
			.append("dataInicioSolic", dataInicioSolic)
			.append("dataFimSolic", dataFimSolic)
			.append("planAnualPrazo", planAnualPrazo)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
