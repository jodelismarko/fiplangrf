package br.gov.mt.mti.fiplangrf.service.tabelas;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.mti.fiplangrf.model.tabelas.ArquivoAnexo;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ArquivoAnexoService extends PaginableService<ArquivoAnexo, Long> {

	protected ArquivoAnexoService() {
		super(ArquivoAnexo.class);
	}

}
