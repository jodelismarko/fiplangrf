package br.gov.mt.mti.fiplangrf.service.tabelas;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.tabelas.CNAE;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CNAEService extends PaginableService<CNAE, Long> {

	protected CNAEService() {
		super(CNAE.class);
	}

	@CheckBusinessAccess(permission = "save")
	public CNAE checkAndSave(CNAE cnae) throws BusinessException {
		Criteria criteria = getSession().createCriteria(CNAE.class);

		criteria.add(Restrictions.eq("numeroCNAE", cnae.getNumeroCNAE()));

		CNAE consulta = (CNAE) criteria.uniqueResult();

		boolean invalido = false;

		if (consulta != null) {
			if (cnae.getId() == null) {
				invalido = true;
			}

			getSession().evict(consulta);

			if (!consulta.getId().equals(cnae.getId())) {
				invalido = true;
			}
		}

		if (invalido) {
			throw new BusinessException(DominioMensagem.MSG_REGISTRO_JA_CADASTRADO.getDesc());
		}

		return (CNAE) getSession().merge(cnae);
	}

	@SuppressWarnings("unchecked")
	@CheckBusinessAccess(check = false)
	public List<CNAE> findByName(String findIt) {
		Criteria criteria = getSession().createCriteria(getType());

		criteria.add(Restrictions.and(Restrictions.like("descricao", findIt, MatchMode.ANYWHERE).ignoreCase(),
				Restrictions.eq("situacao", DominioSituacao.ATIVO)));

		criteria.addOrder(Order.asc("descricao"));
		return (List<CNAE>) criteria.list();
	}

	@CheckBusinessAccess(check = false)
	public List<CNAE> findByCodSugest(String cod) {

		StringBuilder sql = new StringBuilder();

		sql.append(" from CNAE e").append(" where 1=1 ");
		if (cod != null && !cod.isEmpty()) {
			if (Character.isDigit(cod.charAt(0))) {
				sql.append(" and e.numeroCNAE like :cod ");
			} else {
				sql.append(" and upper(e.descricao) like :desc");
			}
		}
		sql.append("and situacao = :situacao");
		sql.append(" order by e.descricao");

		Query query = getSession().createQuery(sql.toString());

		if (cod != null && !cod.isEmpty()) {
			if (Character.isDigit(cod.charAt(0))) {
				query.setString("cod", "%" + cod + "%");
			} else {
				query.setString("desc", "%" + cod.toUpperCase() + "%");
			}
		}
		query.setParameter("situacao", DominioSituacao.ATIVO);
		@SuppressWarnings("unchecked")
		List<CNAE> list = query.list();

		return list;
	}
}
