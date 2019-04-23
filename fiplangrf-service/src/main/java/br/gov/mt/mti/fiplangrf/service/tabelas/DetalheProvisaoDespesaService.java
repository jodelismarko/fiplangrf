package br.gov.mt.mti.fiplangrf.service.tabelas;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.mti.fiplangrf.model.tabelas.DetalheProvisaoDespesa;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DetalheProvisaoDespesaService extends PaginableService<DetalheProvisaoDespesa, Long> {

	protected DetalheProvisaoDespesaService() {
		super(DetalheProvisaoDespesa.class);
	}

}
