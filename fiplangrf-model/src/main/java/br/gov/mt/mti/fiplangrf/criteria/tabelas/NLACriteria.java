package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.NLA;
import java.math.BigDecimal;
import java.util.Date;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoDoc;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoNLA;

public class NLACriteria extends DynamicSearchCriteria<NLA> {

	@FilterFieldOption(label = "Flag Tipo Credor ", property = "flagTipoCredor", type = FieldType.ENUM)
	private DominioTipoNLA flagTipoCredor;

	@FilterFieldOption(label = "Numero NLA ", property = "numeroNLA", type = FieldType.LONG)
	private Long numeroNLA;

	@FilterFieldOption(label = "Valor NLA ", property = "valorNLA", type = FieldType.BIG_DECIMAL)
	private BigDecimal valorNLA;

	@FilterFieldOption(label = "Data NLA ", property = "dataNLA", type = FieldType.TEXT)
	private Date dataNLA;

	@FilterFieldOption(label = "Descricao Motivo Erro ", property = "descricaoMotivoErro", type = FieldType.TEXT)
	private String descricaoMotivoErro;

	@FilterFieldOption(label = "Numero NLA Estorno ", property = "numeroNLAEstorno", type = FieldType.LONG)
	private Long numeroNLAEstorno;

	@FilterFieldOption(label = "Data NLA Estorno ", property = "dataNLAEstorno", type = FieldType.TEXT)
	private Date dataNLAEstorno;

	@FilterFieldOption(label = "Flag Situacao NLA ", property = "flagSituacaoNLA", type = FieldType.ENUM)
	private DominioSituacaoDoc flagSituacaoNLA;

	public NLACriteria() {
		super(NLA.class);
	}

	public DominioTipoNLA getFlagTipoCredor() {
		return flagTipoCredor;
	}

	public void setFlagTipoCredor(DominioTipoNLA flagTipoCredor) {
		this.flagTipoCredor = flagTipoCredor;
	}

	public Long getNumeroNLA() {
		return numeroNLA;
	}

	public void setNumeroNLA(Long numeroNLA) {
		this.numeroNLA = numeroNLA;
	}

	public BigDecimal getValorNLA() {
		return valorNLA;
	}

	public void setValorNLA(BigDecimal valorNLA) {
		this.valorNLA = valorNLA;
	}

	public Date getDataNLA() {
		return dataNLA;
	}

	public void setDataNLA(Date dataNLA) {
		this.dataNLA = dataNLA;
	}

	public String getDescricaoMotivoErro() {
		return descricaoMotivoErro;
	}

	public void setDescricaoMotivoErro(String descricaoMotivoErro) {
		this.descricaoMotivoErro = descricaoMotivoErro;
	}

	public Long getNumeroNLAEstorno() {
		return numeroNLAEstorno;
	}

	public void setNumeroNLAEstorno(Long numeroNLAEstorno) {
		this.numeroNLAEstorno = numeroNLAEstorno;
	}

	public Date getDataNLAEstorno() {
		return dataNLAEstorno;
	}

	public void setDataNLAEstorno(Date dataNLAEstorno) {
		this.dataNLAEstorno = dataNLAEstorno;
	}

	public DominioSituacaoDoc getFlagSituacaoNLA() {
		return flagSituacaoNLA;
	}

	public void setFlagSituacaoNLA(DominioSituacaoDoc flagSituacaoNLA) {
		this.flagSituacaoNLA = flagSituacaoNLA;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("flagTipoCredor", flagTipoCredor)
			.append("numeroNLA", numeroNLA)
			.append("valorNLA", valorNLA)
			.append("dataNLA", dataNLA)
			.append("descricaoMotivoErro", descricaoMotivoErro)
			.append("numeroNLAEstorno", numeroNLAEstorno)
			.append("dataNLAEstorno", dataNLAEstorno)
			.append("flagSituacaoNLA", flagSituacaoNLA)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
