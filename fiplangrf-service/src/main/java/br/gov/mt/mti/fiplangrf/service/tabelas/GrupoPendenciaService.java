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
import br.gov.mt.mti.fiplangrf.model.tabelas.GrupoPendencia;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GrupoPendenciaService extends PaginableService<GrupoPendencia, Long> {

	protected GrupoPendenciaService() {
		super(GrupoPendencia.class);
	}

	@CheckBusinessAccess(permission = "save")
	public GrupoPendencia checkAndSave(GrupoPendencia grupoPendencia) throws BusinessException {
		Criteria criteria = getSession().createCriteria(GrupoPendencia.class);

		configCriteriaFind(criteria);
		
		criteria.add(Restrictions.and(Restrictions.ilike("descricao", grupoPendencia.getDescricao()),
				Restrictions.eq("tipoOcorrencia", grupoPendencia.getTipoOcorrencia())));

		GrupoPendencia consulta = (GrupoPendencia) criteria.uniqueResult();

		boolean invalido = false;

		if (consulta != null) {
			if (grupoPendencia.getId() == null) {
				invalido = true;
			}

			getSession().evict(consulta);

			if (!consulta.getId().equals(grupoPendencia.getId())) {
				invalido = true;
			}
		}

		if (invalido) {
			throw new BusinessException(DominioMensagem.MSG_REGISTRO_JA_CADASTRADO.getDesc());
		}

		return (GrupoPendencia) getSession().merge(grupoPendencia);

	}

	@SuppressWarnings("unchecked")
	@CheckBusinessAccess(check = false)
	public List<GrupoPendencia> findAllCombo(Order... orders) {
		Criteria criteria = getSession().createCriteria(getType());
		if (orders != null)
			for (Order order : orders) {
				criteria.addOrder(order);
			}
		return (List<GrupoPendencia>) criteria.list();
	}

	@Override
	@CheckBusinessAccess(permission = "find")
	public GrupoPendencia findByIdFetchAll(Long id) {
		Criteria criteria = getSession().createCriteria(getType());

		configCriteriaFind(criteria);

		criteria.add(Restrictions.eq("id", id));
		
		return (GrupoPendencia) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@CheckBusinessAccess(check = false)
	public List<GrupoPendencia> findByName(String findIt) {
		Criteria criteria = getSession().createCriteria(GrupoPendencia.class);

		configCriteriaFind(criteria);
		
		criteria.add(Restrictions.and(Restrictions.like("descricao", findIt, MatchMode.ANYWHERE).ignoreCase(),
				Restrictions.eq("situacao", DominioSituacao.ATIVO)));

		criteria.addOrder(Order.asc("descricao"));
		
		return (List<GrupoPendencia>) criteria.list();
	}

	private void configCriteriaFind(Criteria criteria) {
		criteria.createAlias("tipoOcorrencia", "tipoOcorrencia", JoinType.LEFT_OUTER_JOIN);
		criteria.setFetchMode("tipoOcorrencia", FetchMode.JOIN);
	}

}
