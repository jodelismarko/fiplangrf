package br.gov.mt.mti.fiplangrf.service.tabelas;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.mti.fiplangrf.model.tabelas.Revisao;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RevisaoService extends PaginableService<Revisao, Long> {

	protected RevisaoService() {
		super(Revisao.class);
	}

	@CheckBusinessAccess(check = false)
	public Revisao findUltimaRevisao() {
		Criteria criteria = createCriteria();

		criteria.addOrder(Order.desc("dataRevisao"));

		return (Revisao) criteria.setMaxResults(1).uniqueResult();

	}
}
