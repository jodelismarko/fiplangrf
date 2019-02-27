package br.gov.mt.mti.fiplangrf.service.tabelas;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.tabelas.SubGrupoPendencia;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SubGrupoPendenciaService extends PaginableService<SubGrupoPendencia, Long> {

	protected SubGrupoPendenciaService() {
		super(SubGrupoPendencia.class);
	}

	@CheckBusinessAccess(permission = "save")
	public SubGrupoPendencia checkAndSave(SubGrupoPendencia subGrupoPendencia) throws BusinessException {
		Criteria criteria = getSession().createCriteria(SubGrupoPendencia.class);

		criteria.add(Restrictions.and(Restrictions.ilike("descricao", subGrupoPendencia.getDescricao()),
				Restrictions.eq("grupoPendencia", subGrupoPendencia.getGrupoPendencia())));

		SubGrupoPendencia consulta = (SubGrupoPendencia) criteria.uniqueResult();

		boolean invalido = false;

		if (consulta != null) {
			if (subGrupoPendencia.getId() == null) {
				invalido = true;
			}

			getSession().evict(consulta);

			if (!consulta.getId().equals(subGrupoPendencia.getId())) {
				invalido = true;
			}
		}

		if (invalido) {
			throw new BusinessException(DominioMensagem.MSG_REGISTRO_JA_CADASTRADO.getDesc());
		}

		return (SubGrupoPendencia) getSession().merge(subGrupoPendencia);

	}

	@Override
	@CheckBusinessAccess(permission = "find")
	public SubGrupoPendencia findByIdFetchAll(Long id) {
		Criteria criteria = getSession().createCriteria(getType());

		configCriteriaFind(criteria);

		criteria.add(Restrictions.eq("id", id));

		return (SubGrupoPendencia) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@CheckBusinessAccess(check = false)
	public List<SubGrupoPendencia> findByName(String findIt) {

		Criteria criteria = getSession().createCriteria(SubGrupoPendencia.class);

		configCriteriaFind(criteria);
		
		criteria.add(Restrictions.and(Restrictions.like("descricao", findIt, MatchMode.ANYWHERE).ignoreCase(),
				Restrictions.eq("situacao", DominioSituacao.ATIVO)));

		criteria.addOrder(Order.asc("descricao"));
		return (List<SubGrupoPendencia>) criteria.list();
	}

	private void configCriteriaFind(Criteria criteria) {
		criteria.createAlias("grupoPendencia", "grupoPendencia", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("grupoPendencia", FetchMode.JOIN);
	}
}
