package br.gov.mt.mti.fiplangrf.criteria.tabelas;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.gov.mt.cepromat.ceprofw.common.dynamicsearch.*;
import br.gov.mt.cepromat.ceprofw.core.jpa.DynamicSearchCriteria;
import br.gov.mt.mti.fiplangrf.model.tabelas.DocumentoFinanceiro;
import java.math.BigDecimal;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoDocumentoRF;
import java.util.Date;
import br.gov.mt.mti.fiplangrf.model.tabelas.Detalhamento;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacaoDoc;
import java.math.BigInteger;
import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;

public class DocumentoFinanceiroCriteria extends DynamicSearchCriteria<DocumentoFinanceiro> {

	@FilterFieldOption(label = "Tipo Documento ", property = "tipoDocumento", type = FieldType.ENUM)
	private DominioTipoDocumentoRF tipoDocumento;

	@FilterFieldOption(label = "Numero Documento ", property = "numeroDocumento", type = FieldType.TEXT)
	private BigInteger numeroDocumento;

	@FilterFieldOption(label = "Valor Documento ", property = "valorDocumento", type = FieldType.BIG_DECIMAL)
	private BigDecimal valorDocumento;

	@FilterFieldOption(label = "Data Documento ", property = "dataDocumento", type = FieldType.TEXT)
	private Date dataDocumento;

	@FilterFieldOption(label = "Flag Situacao Documento ", property = "flagSituacaoDocumento", type = FieldType.ENUM)
	private DominioSituacaoDoc flagSituacaoDocumento;

	@FilterFieldOption(label = "Usuario ", property = "usuario", type = FieldType.TEXT)
	private Usuario usuario;

	@FilterFieldOption(label = "Detalhamento ", property = "detalhamento", type = FieldType.LIST)
	private Detalhamento detalhamento;

	public DocumentoFinanceiroCriteria() {
		super(DocumentoFinanceiro.class);
	}

	public DominioTipoDocumentoRF getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(DominioTipoDocumentoRF tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public BigInteger getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(BigInteger numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	public Date getDataDocumento() {
		return dataDocumento;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public DominioSituacaoDoc getFlagSituacaoDocumento() {
		return flagSituacaoDocumento;
	}

	public void setFlagSituacaoDocumento(DominioSituacaoDoc flagSituacaoDocumento) {
		this.flagSituacaoDocumento = flagSituacaoDocumento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Detalhamento getDetalhamento() {
		return detalhamento;
	}

	public void setDetalhamento(Detalhamento detalhamento) {
		this.detalhamento = detalhamento;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("tipoDocumento", tipoDocumento)
			.append("numeroDocumento", numeroDocumento)
			.append("valorDocumento", valorDocumento)
			.append("dataDocumento", dataDocumento)
			.append("flagSituacaoDocumento", flagSituacaoDocumento)
			.append("usuario", usuario)
			.append("detalhamento", detalhamento)
			.toString();
	}

	@Override
	public boolean isUseCriteria() {
		return true;
	}

}
