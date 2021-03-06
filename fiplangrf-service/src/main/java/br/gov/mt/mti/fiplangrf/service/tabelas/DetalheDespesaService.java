package br.gov.mt.mti.fiplangrf.service.tabelas;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.mti.fiplangrf.model.tabelas.DetalhamentoDespesa;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DetalheDespesaService extends PaginableService<DetalhamentoDespesa, Long> {

	protected DetalheDespesaService() {
		super(DetalhamentoDespesa.class);
	}

}
