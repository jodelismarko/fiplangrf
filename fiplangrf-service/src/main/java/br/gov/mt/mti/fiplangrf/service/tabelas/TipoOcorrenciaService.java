package br.gov.mt.mti.fiplangrf.service.tabelas;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.gov.mt.cepromat.ceprofw.core.service.PaginableService;
import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.dominio.DominioMensagem;
import br.gov.mt.mti.fiplangrf.dominio.DominioSituacao;
import br.gov.mt.mti.fiplangrf.model.tabelas.TipoOcorrencia;
import br.gov.mt.mti.fiplangrf.service.security.annotation.CheckBusinessAccess;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TipoOcorrenciaService extends PaginableService<TipoOcorrencia, Long> {

	protected TipoOcorrenciaService() {
		super(TipoOcorrencia.class);
	}

	@CheckBusinessAccess(permission = "save")
	public TipoOcorrencia checkAndSave(TipoOcorrencia tipoOcorrencia) throws BusinessException {
		Criteria criteria = getSession().createCriteria(TipoOcorrencia.class);

		criteria.add(Restrictions.ilike("descricao", tipoOcorrencia.getDescricao()));

		TipoOcorrencia consulta = (TipoOcorrencia) criteria.uniqueResult();

		boolean invalido = false;

		if (consulta != null) {
			if (tipoOcorrencia.getId() == null) {
				invalido = true;
			}

			getSession().evict(consulta);

			if (!consulta.getId().equals(tipoOcorrencia.getId())) {
				invalido = true;
			}
		}

		if (invalido) {
			throw new BusinessException(DominioMensagem.MSG_REGISTRO_JA_CADASTRADO.getDesc());
		}

		return (TipoOcorrencia) getSession().merge(tipoOcorrencia);
	}

	@SuppressWarnings("unchecked")
	@CheckBusinessAccess(check = false)
	public List<TipoOcorrencia> findAllActive() {

		Criteria criteria = getSession().createCriteria(TipoOcorrencia.class);
		criteria.add(Restrictions.like("situacao", DominioSituacao.ATIVO));

		return (List<TipoOcorrencia>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@CheckBusinessAccess(check = false)
	public List<TipoOcorrencia> findAllCombo(Order... orders) {
		Criteria criteria = getSession().createCriteria(getType());
		if(orders != null)
			for (Order order : orders) {
				criteria.addOrder(order);
			}
		return (List<TipoOcorrencia>)criteria.list();
	}

	@SuppressWarnings("unchecked")
	@CheckBusinessAccess(check = false)
	public List<TipoOcorrencia> findByName(String findIt) {

		Criteria criteria = getSession().createCriteria(getType());

		criteria.add(Restrictions.and(Restrictions.like("descricao", findIt, MatchMode.ANYWHERE).ignoreCase(),
				Restrictions.eq("situacao", DominioSituacao.ATIVO)));

		criteria.addOrder(Order.asc("descricao"));
		return (List<TipoOcorrencia>) criteria.list();
	}

}
