package br.gov.mt.mti.fiplangrf.service.tabelas;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.mti.fiplangrf.model.tabelas.ItemDespesa;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ItemDespesaService extends PaginableService<ItemDespesa, Long> {

	protected ItemDespesaService() {
		super(ItemDespesa.class);
	}

}
