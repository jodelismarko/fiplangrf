package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.PrazoSolicitacaoMensal;
import br.gov.mt.mti.fiplangrf.dominio.DominioMes;
import java.util.Date;
import br.gov.mt.mti.fiplangrf.model.tabelas.PlanejamentoAnualPrazos;

public class PrazoSolicitacaoMensalCriteria extends DynamicSearchCriteria<PrazoSolicitacaoMensal> {

	@FilterFieldOption(label = "Flag Mes ", property = "flagMes", type = FieldType.ENUM)
	private DominioMes flagMes;

	@FilterFieldOption(label = "Data Inicio Solic ", property = "dataInicioSolic", type = FieldType.TEXT)
	private Date dataInicioSolic;

	@FilterFieldOption(label = "Data Fim Solic ", property = "dataFimSolic", type = FieldType.TEXT)
	private Date dataFimSolic;

	@FilterFieldOption(label = "Plan Anual Prazo ", property = "planAnualPrazo", type = FieldType.LIST)
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
