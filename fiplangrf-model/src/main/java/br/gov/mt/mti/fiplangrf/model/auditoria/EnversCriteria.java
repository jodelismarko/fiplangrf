package br.gov.mt.mti.fiplangrf.model.auditoria;

import java.util.Date;

import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.common.msg.BusinessMsg;
import br.gov.mt.mti.fiplangrf.dominio.DominioTipoOperacao;
import lombok.Getter;
import lombok.Setter;

public class EnversCriteria {

	@Getter
	@Setter
	private String nome;

	@Getter
	@Setter
	private Date dataInicial;

	@Getter
	@Setter
	private Date dataFinal;

	@Getter
	@Setter
	private DominioTipoOperacao tipoOperacao;

	public boolean isValid() throws BusinessException {
		if (getDataInicial() != null && getDataFinal() == null) {
			throw new BusinessException(BusinessMsg.BUNDLE_NAME, BusinessMsg.MSG_CAMPO_DEVE_SER_INFORMADO,
					"Data Final");
		}

		if (getDataInicial() == null && getDataFinal() != null) {
			throw new BusinessException(BusinessMsg.BUNDLE_NAME, BusinessMsg.MSG_CAMPO_DEVE_SER_INFORMADO,
					"Data Inicial");
		}

		if (getDataInicial() != null && getDataFinal() != null) {
			if (getDataInicial().after(getDataFinal())) {
				throw new BusinessException(BusinessMsg.BUNDLE_NAME,
						BusinessMsg.MSG_ERRO_DATA_FINAL_ANTERIOR_DATA_EXISTENTE);
			}
		}

		return true;
	}

}
