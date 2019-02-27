package br.gov.mt.mti.fiplangrf.service.integracao.fiplan;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.mti.fiplangrf.model.fiplan.FIPLANUnidadeOrcamentaria;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FIPLANUnidadeOrcamentariaService extends PaginableService<FIPLANUnidadeOrcamentaria, Long> {

	protected FIPLANUnidadeOrcamentariaService() {
		super(FIPLANUnidadeOrcamentaria.class);
	}

	@SuppressWarnings("unchecked")
	@CheckBusinessAccess(check = false)
	public List<FIPLANUnidadeOrcamentaria> findAll() {
		return (List<FIPLANUnidadeOrcamentaria>) createCriteria().list();
	}

	@SuppressWarnings("unchecked")
	@CheckBusinessAccess(check = false)
	public List<FIPLANUnidadeOrcamentaria> findByName(String findIt) {

		return (List<FIPLANUnidadeOrcamentaria>) createCriteria()
				.add(Restrictions.or(Restrictions.like("descricaoUnidadeOrcamentaria", findIt, MatchMode.ANYWHERE).ignoreCase(),
						Restrictions.like("siglaUnidadeOrcamentaria", findIt, MatchMode.ANYWHERE).ignoreCase()))
				.list();
	}
	
	@CheckBusinessAccess(check = false)
	public List<FIPLANUnidadeOrcamentaria> findByCodSugest(String cod) {

		StringBuilder sql = new StringBuilder();

		sql.append(" from FIPLANUnidadeOrcamentaria e").append(" where 1=1 ");
		if (cod != null && !cod.isEmpty()) {
			if (Character.isDigit(cod.charAt(0))) {
				sql.append(" and e.codigoUnidadeOrcamentaria like :cod ");
			} else {
				sql.append(" and (upper(e.descricaoUnidadeOrcamentaria) like :desc");
				sql.append(" or upper(e.siglaUnidadeOrcamentaria) like :desc)");
			}
		}
		sql.append(" order by e.descricaoUnidadeOrcamentaria");

		Query query = getSession().createQuery(sql.toString());

		if (cod != null && !cod.isEmpty()) {
			if (Character.isDigit(cod.charAt(0))) {
				query.setString("cod", "%" + cod + "%");
			} else {
				query.setString("desc", "%" + cod.toUpperCase() + "%");
			}
		}
		@SuppressWarnings("unchecked")
		List<FIPLANUnidadeOrcamentaria> list = query.list();

		return list;
	}
}