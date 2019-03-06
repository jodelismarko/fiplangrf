package br.gov.mt.mti.fiplangrf.service.tabelas;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.mti.fiplangrf.model.tabelas.FonteRecurso;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FonteRecursoService extends PaginableService<FonteRecurso, Long> {

	protected FonteRecursoService() {
		super(FonteRecurso.class);
	}

}
