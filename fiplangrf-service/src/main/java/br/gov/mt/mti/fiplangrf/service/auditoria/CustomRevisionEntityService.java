package br.gov.mt.mti.fiplangrf.service.auditoria;


import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.mti.fiplangrf.model.auditoria.CustomRevisionEntity;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CustomRevisionEntityService extends PaginableService<CustomRevisionEntity, Long> {
	protected CustomRevisionEntityService(){
		super(CustomRevisionEntity.class);
	}
}
